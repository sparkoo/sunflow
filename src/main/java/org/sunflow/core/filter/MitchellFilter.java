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

public class MitchellFilter implements Filter {
    public float getSize() {
        return 4.0f;
    }

    public float get(float x, float y) {
        return mitchell(x) * mitchell(y);
    }

    private float mitchell(float x) {
        final float B = 1 / 3.0f;
        final float C = 1 / 3.0f;
        final float SIXTH = 1 / 6.0f;
        x = Math.abs(x);
        float x2 = x * x;
        if (x > 1.0f)
            return ((-B - 6 * C) * x * x2 + (6 * B + 30 * C) * x2 + (-12 * B - 48 * C) * x + (8 * B + 24 * C)) * SIXTH;
        return ((12 - 9 * B - 6 * C) * x * x2 + (-18 + 12 * B + 6 * C) * x2 + (6 - 2 * B)) * SIXTH;
    }
}
