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

package org.sunflow.core.camera;

import org.sunflow.SunflowAPI;
import org.sunflow.core.CameraLens;
import org.sunflow.core.ParameterList;
import org.sunflow.core.Ray;

public class FisheyeLens implements CameraLens {
    public boolean update(ParameterList pl, SunflowAPI api) {
        return true;
    }

    public Ray getRay(float x, float y, int imageWidth, int imageHeight, double lensX, double lensY, double time) {
        float cx = 2.0f * x / imageWidth - 1.0f;
        float cy = 2.0f * y / imageHeight - 1.0f;
        float r2 = cx * cx + cy * cy;
        if (r2 > 1)
            return null; // outside the fisheye
        return new Ray(0, 0, 0, cx, cy, (float) -Math.sqrt(1 - r2));
    }
}