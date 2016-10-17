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

package org.sunflow.core.bucket;

import org.sunflow.core.BucketOrder;

public class InvertedBucketOrder implements BucketOrder {
    private BucketOrder order;

    public InvertedBucketOrder(BucketOrder order) {
        this.order = order;
    }

    public int[] getBucketSequence(int nbw, int nbh) {
        int[] coords = order.getBucketSequence(nbw, nbh);
        for (int i = 0; i < coords.length / 2; i += 2) {
            int src = i;
            int dst = coords.length - 2 - i;
            int tmp = coords[src + 0];
            coords[src + 0] = coords[dst + 0];
            coords[dst + 0] = tmp;
            tmp = coords[src + 1];
            coords[src + 1] = coords[dst + 1];
            coords[dst + 1] = tmp;
        }
        return coords;
    }
}