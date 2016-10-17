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

public class RandomBucketOrder implements BucketOrder {
    public int[] getBucketSequence(int nbw, int nbh) {
        int[] coords = new int[2 * nbw * nbh];
        for (int i = 0; i < nbw * nbh; i++) {
            int by = i / nbw;
            int bx = i % nbw;
            if ((by & 1) == 1)
                bx = nbw - 1 - bx;
            coords[2 * i + 0] = bx;
            coords[2 * i + 1] = by;
        }

        long seed = 2463534242L;
        for (int i = 0; i < coords.length; i++) {
            // pick 2 random indices
            seed = xorshift(seed);
            int src = mod((int) seed, nbw * nbh);
            seed = xorshift(seed);
            int dst = mod((int) seed, nbw * nbh);
            int tmp = coords[2 * src + 0];
            coords[2 * src + 0] = coords[2 * dst + 0];
            coords[2 * dst + 0] = tmp;
            tmp = coords[2 * src + 1];
            coords[2 * src + 1] = coords[2 * dst + 1];
            coords[2 * dst + 1] = tmp;
        }

        return coords;
    }

    private int mod(int a, int b) {
        int m = a % b;
        return (m < 0) ? m + b : m;
    }

    private long xorshift(long y) {
        y = y ^ (y << 13);
        y = y ^ (y >>> 17); // unsigned
        y = y ^ (y << 5);
        return y;
    }
}