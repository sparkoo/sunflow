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
import org.sunflow.core.Ray;
import org.sunflow.core.Scene;
import org.sunflow.core.ShadingState;
import org.sunflow.image.Color;
import org.sunflow.math.OrthoNormalBasis;
import org.sunflow.math.Vector3;

public class AmbientOcclusionGIEngine implements GIEngine {
    private Color bright;
    private Color dark;
    private int samples;
    private float maxDist;

    public Color getGlobalRadiance(ShadingState state) {
        return Color.BLACK;
    }

    public boolean init(Options options, Scene scene) {
        bright = options.getColor("gi.ambocc.bright", Color.WHITE);
        dark = options.getColor("gi.ambocc.dark", Color.BLACK);
        samples = options.getInt("gi.ambocc.samples", 32);
        maxDist = options.getFloat("gi.ambocc.maxdist", 0);
        maxDist = (maxDist <= 0) ? Float.POSITIVE_INFINITY : maxDist;
        return true;
    }

    public Color getIrradiance(ShadingState state, Color diffuseReflectance) {
        OrthoNormalBasis onb = state.getBasis();
        Vector3 w = new Vector3();
        Color result = Color.black();
        for (int i = 0; i < samples; i++) {
            float xi = (float) state.getRandom(i, 0, samples);
            float xj = (float) state.getRandom(i, 1, samples);
            float phi = (float) (2 * Math.PI * xi);
            float cosPhi = (float) Math.cos(phi);
            float sinPhi = (float) Math.sin(phi);
            float sinTheta = (float) Math.sqrt(xj);
            float cosTheta = (float) Math.sqrt(1.0f - xj);
            w.x = cosPhi * sinTheta;
            w.y = sinPhi * sinTheta;
            w.z = cosTheta;
            onb.transform(w);
            Ray r = new Ray(state.getPoint(), w);
            r.setMax(maxDist);
            result.add(Color.blend(bright, dark, state.traceShadow(r)));
        }
        return result.mul((float) Math.PI / samples);
    }
}
