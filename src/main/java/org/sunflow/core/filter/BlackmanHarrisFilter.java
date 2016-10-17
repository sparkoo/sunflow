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

package org.sunflow.core.filter;

import org.sunflow.core.Filter;

public class BlackmanHarrisFilter implements Filter {
    public float getSize() {
        return 4;
    }

    public float get(float x, float y) {
        return bh1d(x * 0.5f) * bh1d(y * 0.5f);
    }

    private float bh1d(float x) {
        if (x < -1.0f || x > 1.0f)
            return 0.0f;
        x = (x + 1) * 0.5f;
        final double A0 = 0.35875;
        final double A1 = -0.48829;
        final double A2 = 0.14128;
        final double A3 = -0.01168;
        return (float) (A0 + A1 * Math.cos(2 * Math.PI * x) + A2 * Math.cos(4 * Math.PI * x) + A3 * Math.cos(6 * Math.PI * x));
    }
}