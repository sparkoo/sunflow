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

package org.sunflow.image.formats;

import org.sunflow.image.Bitmap;
import org.sunflow.image.Color;

public class BitmapRGBE extends Bitmap {
    private int w, h;
    private int[] data;
    private static final float[] EXPONENT = new float[256];

    static {
        EXPONENT[0] = 0;
        for (int i = 1; i < 256; i++) {
            float f = 1.0f;
            int e = i - (128 + 8);
            if (e > 0)
                for (int j = 0; j < e; j++)
                    f *= 2.0f;
            else
                for (int j = 0; j < -e; j++)
                    f *= 0.5f;
            EXPONENT[i] = f;
        }
    }

    public BitmapRGBE(int w, int h, int[] data) {
        this.w = w;
        this.h = h;
        this.data = data;
    }

    @Override
    public int getWidth() {
        return w;
    }

    @Override
    public int getHeight() {
        return h;
    }

    @Override
    public Color readColor(int x, int y) {
        int rgbe = data[x + y * w];
        float f = EXPONENT[rgbe & 0xFF];
        float r = f * ((rgbe >>> 24) + 0.5f);
        float g = f * (((rgbe >> 16) & 0xFF) + 0.5f);
        float b = f * (((rgbe >> 8) & 0xFF) + 0.5f);
        return new Color(r, g, b);
    }

    @Override
    public float readAlpha(int x, int y) {
        return 1;
    }
}