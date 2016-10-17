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

package org.sunflow.core.gi;

import org.sunflow.core.GIEngine;
import org.sunflow.core.Options;
import org.sunflow.core.Scene;
import org.sunflow.core.ShadingState;
import org.sunflow.image.Color;
import org.sunflow.math.Vector3;

/**
 * This is a quick way to get a bit of ambient lighting into your scene with
 * hardly any overhead. It's based on the formula found here:
 * 
 * @link http://www.cs.utah.edu/~shirley/papers/rtrt/node7.html#SECTION00031100000000000000
 */
public class FakeGIEngine implements GIEngine {
    private Vector3 up;
    private Color sky;
    private Color ground;

    public Color getIrradiance(ShadingState state, Color diffuseReflectance) {
        float cosTheta = Vector3.dot(up, state.getNormal());
        float sin2 = (1 - cosTheta * cosTheta);
        float sine = sin2 > 0 ? (float) Math.sqrt(sin2) * 0.5f : 0;
        if (cosTheta > 0)
            return Color.blend(sky, ground, sine);
        else
            return Color.blend(ground, sky, sine);
    }

    public Color getGlobalRadiance(ShadingState state) {
        return Color.BLACK;
    }

    public boolean init(Options options, Scene scene) {
        up = options.getVector("gi.fake.up", new Vector3(0, 1, 0)).normalize();
        sky = options.getColor("gi.fake.sky", Color.WHITE).copy();
        ground = options.getColor("gi.fake.ground", Color.BLACK).copy();
        sky.mul((float) Math.PI);
        ground.mul((float) Math.PI);
        return true;
    }
}