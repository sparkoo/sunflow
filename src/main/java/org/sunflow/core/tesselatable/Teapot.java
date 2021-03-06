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

package org.sunflow.core.tesselatable;

public class Teapot extends BezierMesh {
    // teapot data, from: http://www.cs.ucsb.edu/~cs280/winter2004/hw2/
    private static final float[][] PATCHES = {
            { -80.00f, 0.00f, 30.00f, -80.00f, -44.80f, 30.00f, -44.80f,
                    -80.00f, 30.00f, 0.00f, -80.00f, 30.00f, -80.00f, 0.00f,
                    12.00f, -80.00f, -44.80f, 12.00f, -44.80f, -80.00f, 12.00f,
                    0.00f, -80.00f, 12.00f, -60.00f, 0.00f, 3.00f, -60.00f,
                    -33.60f, 3.00f, -33.60f, -60.00f, 3.00f, 0.00f, -60.00f,
                    3.00f, -60.00f, 0.00f, 0.00f, -60.00f, -33.60f, 0.00f,
                    -33.60f, -60.00f, 0.00f, 0.00f, -60.00f, 0.00f, },
            { 0.00f, -80.00f, 30.00f, 44.80f, -80.00f, 30.00f, 80.00f, -44.80f,
                    30.00f, 80.00f, 0.00f, 30.00f, 0.00f, -80.00f, 12.00f,
                    44.80f, -80.00f, 12.00f, 80.00f, -44.80f, 12.00f, 80.00f,
                    0.00f, 12.00f, 0.00f, -60.00f, 3.00f, 33.60f, -60.00f,
                    3.00f, 60.00f, -33.60f, 3.00f, 60.00f, 0.00f, 3.00f, 0.00f,
                    -60.00f, 0.00f, 33.60f, -60.00f, 0.00f, 60.00f, -33.60f,
                    0.00f, 60.00f, 0.00f, 0.00f, },
            { -60.00f, 0.00f, 90.00f, -60.00f, -33.60f, 90.00f, -33.60f,
                    -60.00f, 90.00f, 0.00f, -60.00f, 90.00f, -70.00f, 0.00f,
                    69.00f, -70.00f, -39.20f, 69.00f, -39.20f, -70.00f, 69.00f,
                    0.00f, -70.00f, 69.00f, -80.00f, 0.00f, 48.00f, -80.00f,
                    -44.80f, 48.00f, -44.80f, -80.00f, 48.00f, 0.00f, -80.00f,
                    48.00f, -80.00f, 0.00f, 30.00f, -80.00f, -44.80f, 30.00f,
                    -44.80f, -80.00f, 30.00f, 0.00f, -80.00f, 30.00f, },
            { 0.00f, -60.00f, 90.00f, 33.60f, -60.00f, 90.00f, 60.00f, -33.60f,
                    90.00f, 60.00f, 0.00f, 90.00f, 0.00f, -70.00f, 69.00f,
                    39.20f, -70.00f, 69.00f, 70.00f, -39.20f, 69.00f, 70.00f,
                    0.00f, 69.00f, 0.00f, -80.00f, 48.00f, 44.80f, -80.00f,
                    48.00f, 80.00f, -44.80f, 48.00f, 80.00f, 0.00f, 48.00f,
                    0.00f, -80.00f, 30.00f, 44.80f, -80.00f, 30.00f, 80.00f,
                    -44.80f, 30.00f, 80.00f, 0.00f, 30.00f, },
            { -56.00f, 0.00f, 90.00f, -56.00f, -31.36f, 90.00f, -31.36f,
                    -56.00f, 90.00f, 0.00f, -56.00f, 90.00f, -53.50f, 0.00f,
                    95.25f, -53.50f, -29.96f, 95.25f, -29.96f, -53.50f, 95.25f,
                    0.00f, -53.50f, 95.25f, -57.50f, 0.00f, 95.25f, -57.50f,
                    -32.20f, 95.25f, -32.20f, -57.50f, 95.25f, 0.00f, -57.50f,
                    95.25f, -60.00f, 0.00f, 90.00f, -60.00f, -33.60f, 90.00f,
                    -33.60f, -60.00f, 90.00f, 0.00f, -60.00f, 90.00f, },
            { 0.00f, -56.00f, 90.00f, 31.36f, -56.00f, 90.00f, 56.00f, -31.36f,
                    90.00f, 56.00f, 0.00f, 90.00f, 0.00f, -53.50f, 95.25f,
                    29.96f, -53.50f, 95.25f, 53.50f, -29.96f, 95.25f, 53.50f,
                    0.00f, 95.25f, 0.00f, -57.50f, 95.25f, 32.20f, -57.50f,
                    95.25f, 57.50f, -32.20f, 95.25f, 57.50f, 0.00f, 95.25f,
                    0.00f, -60.00f, 90.00f, 33.60f, -60.00f, 90.00f, 60.00f,
                    -33.60f, 90.00f, 60.00f, 0.00f, 90.00f, },
            { 80.00f, 0.00f, 30.00f, 80.00f, 44.80f, 30.00f, 44.80f, 80.00f,
                    30.00f, 0.00f, 80.00f, 30.00f, 80.00f, 0.00f, 12.00f,
                    80.00f, 44.80f, 12.00f, 44.80f, 80.00f, 12.00f, 0.00f,
                    80.00f, 12.00f, 60.00f, 0.00f, 3.00f, 60.00f, 33.60f,
                    3.00f, 33.60f, 60.00f, 3.00f, 0.00f, 60.00f, 3.00f, 60.00f,
                    0.00f, 0.00f, 60.00f, 33.60f, 0.00f, 33.60f, 60.00f, 0.00f,
                    0.00f, 60.00f, 0.00f, },
            { 0.00f, 80.00f, 30.00f, -44.80f, 80.00f, 30.00f, -80.00f, 44.80f,
                    30.00f, -80.00f, 0.00f, 30.00f, 0.00f, 80.00f, 12.00f,
                    -44.80f, 80.00f, 12.00f, -80.00f, 44.80f, 12.00f, -80.00f,
                    0.00f, 12.00f, 0.00f, 60.00f, 3.00f, -33.60f, 60.00f,
                    3.00f, -60.00f, 33.60f, 3.00f, -60.00f, 0.00f, 3.00f,
                    0.00f, 60.00f, 0.00f, -33.60f, 60.00f, 0.00f, -60.00f,
                    33.60f, 0.00f, -60.00f, 0.00f, 0.00f, },
            { 60.00f, 0.00f, 90.00f, 60.00f, 33.60f, 90.00f, 33.60f, 60.00f,
                    90.00f, 0.00f, 60.00f, 90.00f, 70.00f, 0.00f, 69.00f,
                    70.00f, 39.20f, 69.00f, 39.20f, 70.00f, 69.00f, 0.00f,
                    70.00f, 69.00f, 80.00f, 0.00f, 48.00f, 80.00f, 44.80f,
                    48.00f, 44.80f, 80.00f, 48.00f, 0.00f, 80.00f, 48.00f,
                    80.00f, 0.00f, 30.00f, 80.00f, 44.80f, 30.00f, 44.80f,
                    80.00f, 30.00f, 0.00f, 80.00f, 30.00f, },
            { 0.00f, 60.00f, 90.00f, -33.60f, 60.00f, 90.00f, -60.00f, 33.60f,
                    90.00f, -60.00f, 0.00f, 90.00f, 0.00f, 70.00f, 69.00f,
                    -39.20f, 70.00f, 69.00f, -70.00f, 39.20f, 69.00f, -70.00f,
                    0.00f, 69.00f, 0.00f, 80.00f, 48.00f, -44.80f, 80.00f,
                    48.00f, -80.00f, 44.80f, 48.00f, -80.00f, 0.00f, 48.00f,
                    0.00f, 80.00f, 30.00f, -44.80f, 80.00f, 30.00f, -80.00f,
                    44.80f, 30.00f, -80.00f, 0.00f, 30.00f, },
            { 56.00f, 0.00f, 90.00f, 56.00f, 31.36f, 90.00f, 31.36f, 56.00f,
                    90.00f, 0.00f, 56.00f, 90.00f, 53.50f, 0.00f, 95.25f,
                    53.50f, 29.96f, 95.25f, 29.96f, 53.50f, 95.25f, 0.00f,
                    53.50f, 95.25f, 57.50f, 0.00f, 95.25f, 57.50f, 32.20f,
                    95.25f, 32.20f, 57.50f, 95.25f, 0.00f, 57.50f, 95.25f,
                    60.00f, 0.00f, 90.00f, 60.00f, 33.60f, 90.00f, 33.60f,
                    60.00f, 90.00f, 0.00f, 60.00f, 90.00f, },
            { 0.00f, 56.00f, 90.00f, -31.36f, 56.00f, 90.00f, -56.00f, 31.36f,
                    90.00f, -56.00f, 0.00f, 90.00f, 0.00f, 53.50f, 95.25f,
                    -29.96f, 53.50f, 95.25f, -53.50f, 29.96f, 95.25f, -53.50f,
                    0.00f, 95.25f, 0.00f, 57.50f, 95.25f, -32.20f, 57.50f,
                    95.25f, -57.50f, 32.20f, 95.25f, -57.50f, 0.00f, 95.25f,
                    0.00f, 60.00f, 90.00f, -33.60f, 60.00f, 90.00f, -60.00f,
                    33.60f, 90.00f, -60.00f, 0.00f, 90.00f, },
            { -64.00f, 0.00f, 75.00f, -64.00f, 12.00f, 75.00f, -60.00f, 12.00f,
                    84.00f, -60.00f, 0.00f, 84.00f, -92.00f, 0.00f, 75.00f,
                    -92.00f, 12.00f, 75.00f, -100.00f, 12.00f, 84.00f,
                    -100.00f, 0.00f, 84.00f, -108.00f, 0.00f, 75.00f, -108.00f,
                    12.00f, 75.00f, -120.00f, 12.00f, 84.00f, -120.00f, 0.00f,
                    84.00f, -108.00f, 0.00f, 66.00f, -108.00f, 12.00f, 66.00f,
                    -120.00f, 12.00f, 66.00f, -120.00f, 0.00f, 66.00f, },
            { -60.00f, 0.00f, 84.00f, -60.00f, -12.00f, 84.00f, -64.00f,
                    -12.00f, 75.00f, -64.00f, 0.00f, 75.00f, -100.00f, 0.00f,
                    84.00f, -100.00f, -12.00f, 84.00f, -92.00f, -12.00f,
                    75.00f, -92.00f, 0.00f, 75.00f, -120.00f, 0.00f, 84.00f,
                    -120.00f, -12.00f, 84.00f, -108.00f, -12.00f, 75.00f,
                    -108.00f, 0.00f, 75.00f, -120.00f, 0.00f, 66.00f, -120.00f,
                    -12.00f, 66.00f, -108.00f, -12.00f, 66.00f, -108.00f,
                    0.00f, 66.00f, },
            { -108.00f, 0.00f, 66.00f, -108.00f, 12.00f, 66.00f, -120.00f,
                    12.00f, 66.00f, -120.00f, 0.00f, 66.00f, -108.00f, 0.00f,
                    57.00f, -108.00f, 12.00f, 57.00f, -120.00f, 12.00f, 48.00f,
                    -120.00f, 0.00f, 48.00f, -100.00f, 0.00f, 39.00f, -100.00f,
                    12.00f, 39.00f, -106.00f, 12.00f, 31.50f, -106.00f, 0.00f,
                    31.50f, -80.00f, 0.00f, 30.00f, -80.00f, 12.00f, 30.00f,
                    -76.00f, 12.00f, 18.00f, -76.00f, 0.00f, 18.00f, },
            { -120.00f, 0.00f, 66.00f, -120.00f, -12.00f, 66.00f, -108.00f,
                    -12.00f, 66.00f, -108.00f, 0.00f, 66.00f, -120.00f, 0.00f,
                    48.00f, -120.00f, -12.00f, 48.00f, -108.00f, -12.00f,
                    57.00f, -108.00f, 0.00f, 57.00f, -106.00f, 0.00f, 31.50f,
                    -106.00f, -12.00f, 31.50f, -100.00f, -12.00f, 39.00f,
                    -100.00f, 0.00f, 39.00f, -76.00f, 0.00f, 18.00f, -76.00f,
                    -12.00f, 18.00f, -80.00f, -12.00f, 30.00f, -80.00f, 0.00f,
                    30.00f, },
            { 68.00f, 0.00f, 51.00f, 68.00f, 26.40f, 51.00f, 68.00f, 26.40f,
                    18.00f, 68.00f, 0.00f, 18.00f, 104.00f, 0.00f, 51.00f,
                    104.00f, 26.40f, 51.00f, 124.00f, 26.40f, 27.00f, 124.00f,
                    0.00f, 27.00f, 92.00f, 0.00f, 78.00f, 92.00f, 10.00f,
                    78.00f, 96.00f, 10.00f, 75.00f, 96.00f, 0.00f, 75.00f,
                    108.00f, 0.00f, 90.00f, 108.00f, 10.00f, 90.00f, 132.00f,
                    10.00f, 90.00f, 132.00f, 0.00f, 90.00f, },
            { 68.00f, 0.00f, 18.00f, 68.00f, -26.40f, 18.00f, 68.00f, -26.40f,
                    51.00f, 68.00f, 0.00f, 51.00f, 124.00f, 0.00f, 27.00f,
                    124.00f, -26.40f, 27.00f, 104.00f, -26.40f, 51.00f,
                    104.00f, 0.00f, 51.00f, 96.00f, 0.00f, 75.00f, 96.00f,
                    -10.00f, 75.00f, 92.00f, -10.00f, 78.00f, 92.00f, 0.00f,
                    78.00f, 132.00f, 0.00f, 90.00f, 132.00f, -10.00f, 90.00f,
                    108.00f, -10.00f, 90.00f, 108.00f, 0.00f, 90.00f, },
            { 108.00f, 0.00f, 90.00f, 108.00f, 10.00f, 90.00f, 132.00f, 10.00f,
                    90.00f, 132.00f, 0.00f, 90.00f, 112.00f, 0.00f, 93.00f,
                    112.00f, 10.00f, 93.00f, 141.00f, 10.00f, 93.75f, 141.00f,
                    0.00f, 93.75f, 116.00f, 0.00f, 93.00f, 116.00f, 6.00f,
                    93.00f, 138.00f, 6.00f, 94.50f, 138.00f, 0.00f, 94.50f,
                    112.00f, 0.00f, 90.00f, 112.00f, 6.00f, 90.00f, 128.00f,
                    6.00f, 90.00f, 128.00f, 0.00f, 90.00f, },
            { 132.00f, 0.00f, 90.00f, 132.00f, -10.00f, 90.00f, 108.00f,
                    -10.00f, 90.00f, 108.00f, 0.00f, 90.00f, 141.00f, 0.00f,
                    93.75f, 141.00f, -10.00f, 93.75f, 112.00f, -10.00f, 93.00f,
                    112.00f, 0.00f, 93.00f, 138.00f, 0.00f, 94.50f, 138.00f,
                    -6.00f, 94.50f, 116.00f, -6.00f, 93.00f, 116.00f, 0.00f,
                    93.00f, 128.00f, 0.00f, 90.00f, 128.00f, -6.00f, 90.00f,
                    112.00f, -6.00f, 90.00f, 112.00f, 0.00f, 90.00f, },
            { 50.00f, 0.00f, 90.00f, 50.00f, 28.00f, 90.00f, 28.00f, 50.00f,
                    90.00f, 0.00f, 50.00f, 90.00f, 52.00f, 0.00f, 90.00f,
                    52.00f, 29.12f, 90.00f, 29.12f, 52.00f, 90.00f, 0.00f,
                    52.00f, 90.00f, 54.00f, 0.00f, 90.00f, 54.00f, 30.24f,
                    90.00f, 30.24f, 54.00f, 90.00f, 0.00f, 54.00f, 90.00f,
                    56.00f, 0.00f, 90.00f, 56.00f, 31.36f, 90.00f, 31.36f,
                    56.00f, 90.00f, 0.00f, 56.00f, 90.00f, },
            { 0.00f, 50.00f, 90.00f, -28.00f, 50.00f, 90.00f, -50.00f, 28.00f,
                    90.00f, -50.00f, 0.00f, 90.00f, 0.00f, 52.00f, 90.00f,
                    -29.12f, 52.00f, 90.00f, -52.00f, 29.12f, 90.00f, -52.00f,
                    0.00f, 90.00f, 0.00f, 54.00f, 90.00f, -30.24f, 54.00f,
                    90.00f, -54.00f, 30.24f, 90.00f, -54.00f, 0.00f, 90.00f,
                    0.00f, 56.00f, 90.00f, -31.36f, 56.00f, 90.00f, -56.00f,
                    31.36f, 90.00f, -56.00f, 0.00f, 90.00f, },
            { -50.00f, 0.00f, 90.00f, -50.00f, -28.00f, 90.00f, -28.00f,
                    -50.00f, 90.00f, 0.00f, -50.00f, 90.00f, -52.00f, 0.00f,
                    90.00f, -52.00f, -29.12f, 90.00f, -29.12f, -52.00f, 90.00f,
                    0.00f, -52.00f, 90.00f, -54.00f, 0.00f, 90.00f, -54.00f,
                    -30.24f, 90.00f, -30.24f, -54.00f, 90.00f, 0.00f, -54.00f,
                    90.00f, -56.00f, 0.00f, 90.00f, -56.00f, -31.36f, 90.00f,
                    -31.36f, -56.00f, 90.00f, 0.00f, -56.00f, 90.00f, },
            { 0.00f, -50.00f, 90.00f, 28.00f, -50.00f, 90.00f, 50.00f, -28.00f,
                    90.00f, 50.00f, 0.00f, 90.00f, 0.00f, -52.00f, 90.00f,
                    29.12f, -52.00f, 90.00f, 52.00f, -29.12f, 90.00f, 52.00f,
                    0.00f, 90.00f, 0.00f, -54.00f, 90.00f, 30.24f, -54.00f,
                    90.00f, 54.00f, -30.24f, 90.00f, 54.00f, 0.00f, 90.00f,
                    0.00f, -56.00f, 90.00f, 31.36f, -56.00f, 90.00f, 56.00f,
                    -31.36f, 90.00f, 56.00f, 0.00f, 90.00f, },
            { 8.00f, 0.00f, 102.00f, 8.00f, 4.48f, 102.00f, 4.48f, 8.00f,
                    102.00f, 0.00f, 8.00f, 102.00f, 16.00f, 0.00f, 96.00f,
                    16.00f, 8.96f, 96.00f, 8.96f, 16.00f, 96.00f, 0.00f,
                    16.00f, 96.00f, 52.00f, 0.00f, 96.00f, 52.00f, 29.12f,
                    96.00f, 29.12f, 52.00f, 96.00f, 0.00f, 52.00f, 96.00f,
                    52.00f, 0.00f, 90.00f, 52.00f, 29.12f, 90.00f, 29.12f,
                    52.00f, 90.00f, 0.00f, 52.00f, 90.00f, },
            { 0.00f, 8.00f, 102.00f, -4.48f, 8.00f, 102.00f, -8.00f, 4.48f,
                    102.00f, -8.00f, 0.00f, 102.00f, 0.00f, 16.00f, 96.00f,
                    -8.96f, 16.00f, 96.00f, -16.00f, 8.96f, 96.00f, -16.00f,
                    0.00f, 96.00f, 0.00f, 52.00f, 96.00f, -29.12f, 52.00f,
                    96.00f, -52.00f, 29.12f, 96.00f, -52.00f, 0.00f, 96.00f,
                    0.00f, 52.00f, 90.00f, -29.12f, 52.00f, 90.00f, -52.00f,
                    29.12f, 90.00f, -52.00f, 0.00f, 90.00f, },
            { -8.00f, 0.00f, 102.00f, -8.00f, -4.48f, 102.00f, -4.48f, -8.00f,
                    102.00f, 0.00f, -8.00f, 102.00f, -16.00f, 0.00f, 96.00f,
                    -16.00f, -8.96f, 96.00f, -8.96f, -16.00f, 96.00f, 0.00f,
                    -16.00f, 96.00f, -52.00f, 0.00f, 96.00f, -52.00f, -29.12f,
                    96.00f, -29.12f, -52.00f, 96.00f, 0.00f, -52.00f, 96.00f,
                    -52.00f, 0.00f, 90.00f, -52.00f, -29.12f, 90.00f, -29.12f,
                    -52.00f, 90.00f, 0.00f, -52.00f, 90.00f, },
            { 0.00f, -8.00f, 102.00f, 4.48f, -8.00f, 102.00f, 8.00f, -4.48f,
                    102.00f, 8.00f, 0.00f, 102.00f, 0.00f, -16.00f, 96.00f,
                    8.96f, -16.00f, 96.00f, 16.00f, -8.96f, 96.00f, 16.00f,
                    0.00f, 96.00f, 0.00f, -52.00f, 96.00f, 29.12f, -52.00f,
                    96.00f, 52.00f, -29.12f, 96.00f, 52.00f, 0.00f, 96.00f,
                    0.00f, -52.00f, 90.00f, 29.12f, -52.00f, 90.00f, 52.00f,
                    -29.12f, 90.00f, 52.00f, 0.00f, 90.00f, },
            { 0.00f, 0.00f, 120.00f, 0.00f, 0.00f, 120.00f, 0.00f, 0.00f,
                    120.00f, 0.00f, 0.00f, 120.00f, 32.00f, 0.00f, 120.00f,
                    32.00f, 18.00f, 120.00f, 18.00f, 32.00f, 120.00f, 0.00f,
                    32.00f, 120.00f, 0.00f, 0.00f, 108.00f, 0.00f, 0.00f,
                    108.00f, 0.00f, 0.00f, 108.00f, 0.00f, 0.00f, 108.00f,
                    8.00f, 0.00f, 102.00f, 8.00f, 4.48f, 102.00f, 4.48f, 8.00f,
                    102.00f, 0.00f, 8.00f, 102.00f, },
            { 0.00f, 0.00f, 120.00f, 0.00f, 0.00f, 120.00f, 0.00f, 0.00f,
                    120.00f, 0.00f, 0.00f, 120.00f, 0.00f, 32.00f, 120.00f,
                    -18.00f, 32.00f, 120.00f, -32.00f, 18.00f, 120.00f,
                    -32.00f, 0.00f, 120.00f, 0.00f, 0.00f, 108.00f, 0.00f,
                    0.00f, 108.00f, 0.00f, 0.00f, 108.00f, 0.00f, 0.00f,
                    108.00f, 0.00f, 8.00f, 102.00f, -4.48f, 8.00f, 102.00f,
                    -8.00f, 4.48f, 102.00f, -8.00f, 0.00f, 102.00f, },
            { 0.00f, 0.00f, 120.00f, 0.00f, 0.00f, 120.00f, 0.00f, 0.00f,
                    120.00f, 0.00f, 0.00f, 120.00f, -32.00f, 0.00f, 120.00f,
                    -32.00f, -18.00f, 120.00f, -18.00f, -32.00f, 120.00f,
                    0.00f, -32.00f, 120.00f, 0.00f, 0.00f, 108.00f, 0.00f,
                    0.00f, 108.00f, 0.00f, 0.00f, 108.00f, 0.00f, 0.00f,
                    108.00f, -8.00f, 0.00f, 102.00f, -8.00f, -4.48f, 102.00f,
                    -4.48f, -8.00f, 102.00f, 0.00f, -8.00f, 102.00f, },
            { 0.00f, 0.00f, 120.00f, 0.00f, 0.00f, 120.00f, 0.00f, 0.00f,
                    120.00f, 0.00f, 0.00f, 120.00f, 0.00f, -32.00f, 120.00f,
                    18.00f, -32.00f, 120.00f, 32.00f, -18.00f, 120.00f, 32.00f,
                    0.00f, 120.00f, 0.00f, 0.00f, 108.00f, 0.00f, 0.00f,
                    108.00f, 0.00f, 0.00f, 108.00f, 0.00f, 0.00f, 108.00f,
                    0.00f, -8.00f, 102.00f, 4.48f, -8.00f, 102.00f, 8.00f,
                    -4.48f, 102.00f, 8.00f, 0.00f, 102.00f, } };

    public Teapot() {
        super(PATCHES);
    }
}