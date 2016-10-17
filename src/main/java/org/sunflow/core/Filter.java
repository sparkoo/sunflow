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

package org.sunflow.core;

/**
 * Represents a multi-pixel image filter kernel.
 */
public interface Filter {
    /**
     * Width in pixels of the filter extents. The filter will be applied to the
     * range of pixels within a box of <code>+/- getSize() / 2</code> around
     * the center of the pixel.
     * 
     * @return width in pixels
     */
    public float getSize();

    /**
     * Get value of the filter at offset (x, y). The filter should never be
     * called with values beyond its extents but should return 0 in those cases
     * anyway.
     * 
     * @param x x offset in pixels
     * @param y y offset in pixels
     * @return value of the filter at the specified location
     */
    public float get(float x, float y);
}