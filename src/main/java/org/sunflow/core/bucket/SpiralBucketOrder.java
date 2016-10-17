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

public class SpiralBucketOrder implements BucketOrder {
    public int[] getBucketSequence(int nbw, int nbh) {
        int[] coords = new int[2 * nbw * nbh];
        for (int i = 0; i < nbw * nbh; i++) {
            int bx, by;
            int center = (Math.min(nbw, nbh) - 1) / 2;
            int nx = nbw;
            int ny = nbh;
            while (i < (nx * ny)) {
                nx--;
                ny--;
            }
            int nxny = nx * ny;
            int minnxny = Math.min(nx, ny);
            if ((minnxny & 1) == 1) {
                if (i <= (nxny + ny)) {
                    bx = nx - minnxny / 2;
                    by = -minnxny / 2 + i - nxny;
                } else {
                    bx = nx - minnxny / 2 - (i - (nxny + ny));
                    by = ny - minnxny / 2;
                }
            } else {
                if (i <= (nxny + ny)) {
                    bx = -minnxny / 2;
                    by = ny - minnxny / 2 - (i - nxny);
                } else {
                    bx = -minnxny / 2 + (i - (nxny + ny));
                    by = -minnxny / 2;
                }
            }
            coords[2 * i + 0] = bx + center;
            coords[2 * i + 1] = by + center;
        }
        return coords;
    }
}