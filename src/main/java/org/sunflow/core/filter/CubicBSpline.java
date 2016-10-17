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

public class CubicBSpline implements Filter {
    public float get(float x, float y) {
        return B3(x) * B3(y);
    }

    public float getSize() {
        return 4;
    }

    private float B3(float t) {
        t = Math.abs(t);
        if (t <= 1)
            return b1(1 - t);
        return b0(2 - t);
    }

    private float b0(float t) {
        return t * t * t * (1.0f / 6);
    }

    private float b1(float t) {
        return (1.0f / 6) * (-3 * t * t * t + 3 * t * t + 3 * t + 1);
    }
}