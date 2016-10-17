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
 * Creates an array of coordinates that iterate over the tiled screen. Classes
 * which implement this interface are responsible for guarenteeing the entire
 * screen is tiled. No attempt is made to check for duplicates or incomplete
 * coverage.
 */
public interface BucketOrder {
    /**
     * Computes the order in which each coordinate on the screen should be
     * visited.
     * 
     * @param nbw number of buckets in the X direction
     * @param nbh number of buckets in the Y direction
     * @return array of coordinates with interleaved X, Y of the positions of
     *         buckets to be rendered.
     */
    int[] getBucketSequence(int nbw, int nbh);
}