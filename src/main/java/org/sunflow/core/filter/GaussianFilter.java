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

public class GaussianFilter implements Filter {
    private float es2;

    public GaussianFilter() {
        es2 = (float) -Math.exp(-getSize() * getSize());
    }

    public float getSize() {
        return 3.0f;
    }

    public float get(float x, float y) {
        float gx = (float) Math.exp(-x * x) + es2;
        float gy = (float) Math.exp(-y * y) + es2;
        return gx * gy;
    }
}