/*
 * Copyright (c) 2003-2007 Christopher Kulla
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */

package org.sunflow.core.renderer;

import org.sunflow.core.Display;
import org.sunflow.core.ImageSampler;
import org.sunflow.core.IntersectionState;
import org.sunflow.core.Options;
import org.sunflow.core.Scene;
import org.sunflow.core.ShadingState;
import org.sunflow.image.Color;
import org.sunflow.system.Timer;
import org.sunflow.system.UI;
import org.sunflow.system.UI.Module;

public class SimpleRenderer implements ImageSampler {
    private Scene scene;
    private Display display;
    private int imageWidth, imageHeight;
    private int numBucketsX, numBucketsY;
    private int bucketCounter, numBuckets;

    public boolean prepare(Options options, Scene scene, int w, int h) {
        this.scene = scene;
        imageWidth = w;
        imageHeight = h;
        numBucketsX = (imageWidth + 31) >>> 5;
        numBucketsY = (imageHeight + 31) >>> 5;
        numBuckets = numBucketsX * numBucketsY;
        return true;
    }

    public void render(Display display) {
        this.display = display;
        display.imageBegin(imageWidth, imageHeight, 32);
        // set members variables
        bucketCounter = 0;
        // start task
        Timer timer = new Timer();
        timer.start();
        BucketThread[] renderThreads = new BucketThread[scene.getThreads()];
        for (int i = 0; i < renderThreads.length; i++) {
            renderThreads[i] = new BucketThread();
            renderThreads[i].start();
        }
        for (int i = 0; i < renderThreads.length; i++) {
            try {
                renderThreads[i].join();
            } catch (InterruptedException e) {
                UI.printError(Module.BCKT, "Bucket processing thread %d of %d was interrupted", i + 1, renderThreads.length);
            } finally {
                renderThreads[i].updateStats();
            }
        }
        timer.end();
        UI.printInfo(Module.BCKT, "Render time: %s", timer.toString());
        display.imageEnd();
    }

    private class BucketThread extends Thread {
        private final IntersectionState istate = new IntersectionState();

        @Override
        public void run() {
            while (true) {
                int bx, by;
                synchronized (SimpleRenderer.this) {
                    if (bucketCounter >= numBuckets)
                        return;
                    by = bucketCounter / numBucketsX;
                    bx = bucketCounter % numBucketsX;
                    bucketCounter++;
                }
                renderBucket(bx, by, istate);
            }
        }

        void updateStats() {
            scene.accumulateStats(istate);
        }
    }

    public void renderBucket(int bx, int by, IntersectionState istate) {
        // pixel sized extents
        int x0 = bx * 32;
        int y0 = by * 32;
        int bw = Math.min(32, imageWidth - x0);
        int bh = Math.min(32, imageHeight - y0);

        Color[] bucketRGB = new Color[bw * bh];
        float[] bucketAlpha = new float[bw * bh];

        for (int y = 0, i = 0; y < bh; y++) {
            for (int x = 0; x < bw; x++, i++) {
                ShadingState state = scene.getRadiance(istate, x0 + x, imageHeight - 1 - (y0 + y), 0.0, 0.0, 0.0, 0, 0, null);
                bucketRGB[i] = (state != null) ? state.getResult() : Color.BLACK;
                bucketAlpha[i] = (state != null) ? 1 : 0;
            }
        }
        // update pixels
        display.imageUpdate(x0, y0, bw, bh, bucketRGB, bucketAlpha);
    }
}