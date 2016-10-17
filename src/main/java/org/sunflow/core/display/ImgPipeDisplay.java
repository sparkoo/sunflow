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

package org.sunflow.core.display;

import java.io.IOException;

import javax.swing.JPanel;

import org.sunflow.core.Display;
import org.sunflow.image.Color;

@SuppressWarnings("serial")
public class ImgPipeDisplay extends JPanel implements Display {
    private int ih;

    /**
     * Render to stdout using the imgpipe protocol used in mental image's
     * imf_disp viewer. http://www.lamrug.org/resources/stubtips.html
     */
    public ImgPipeDisplay() {
    }

    public synchronized void imageBegin(int w, int h, int bucketSize) {
        ih = h;
        outputPacket(5, w, h, Float.floatToRawIntBits(1.0f), 0);
        System.out.flush();
    }

    public synchronized void imagePrepare(int x, int y, int w, int h, int id) {
    }

    public synchronized void imageUpdate(int x, int y, int w, int h, Color[] data, float[] alpha) {
        int xl = x;
        int xh = x + w - 1;
        int yl = ih - 1 - (y + h - 1);
        int yh = ih - 1 - y;
        outputPacket(2, xl, xh, yl, yh);
        byte[] rgba = new byte[4 * (yh - yl + 1) * (xh - xl + 1)];
        for (int j = 0, idx = 0; j < h; j++) {
            for (int i = 0; i < w; i++, idx += 4) {
                int rgb = data[(h - j - 1) * w + i].toNonLinear().toRGB();
                int cr = (rgb >> 16) & 0xFF;
                int cg = (rgb >> 8) & 0xFF;
                int cb = rgb & 0xFF;
                rgba[idx + 0] = (byte) (cr & 0xFF);
                rgba[idx + 1] = (byte) (cg & 0xFF);
                rgba[idx + 2] = (byte) (cb & 0xFF);
                rgba[idx + 3] = (byte) (0xFF);
            }
        }
        try {
            System.out.write(rgba);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void imageFill(int x, int y, int w, int h, Color c, float alpha) {
        int xl = x;
        int xh = x + w - 1;
        int yl = ih - 1 - (y + h - 1);
        int yh = ih - 1 - y;
        outputPacket(2, xl, xh, yl, yh);
        int rgb = c.toNonLinear().toRGB();
        int cr = (rgb >> 16) & 0xFF;
        int cg = (rgb >> 8) & 0xFF;
        int cb = rgb & 0xFF;
        byte[] rgba = new byte[4 * (yh - yl + 1) * (xh - xl + 1)];
        for (int j = 0, idx = 0; j < h; j++) {
            for (int i = 0; i < w; i++, idx += 4) {
                rgba[idx + 0] = (byte) (cr & 0xFF);
                rgba[idx + 1] = (byte) (cg & 0xFF);
                rgba[idx + 2] = (byte) (cb & 0xFF);
                rgba[idx + 3] = (byte) (0xFF);
            }
        }
        try {
            System.out.write(rgba);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void imageEnd() {
        outputPacket(4, 0, 0, 0, 0);
        System.out.flush();
    }

    private void outputPacket(int type, int d0, int d1, int d2, int d3) {
        outputInt32(type);
        outputInt32(d0);
        outputInt32(d1);
        outputInt32(d2);
        outputInt32(d3);
    }

    private void outputInt32(int i) {
        System.out.write((i >> 24) & 0xFF);
        System.out.write((i >> 16) & 0xFF);
        System.out.write((i >> 8) & 0xFF);
        System.out.write(i & 0xFF);
    }
}