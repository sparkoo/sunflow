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

package org.sunflow.core.shader;

import org.sunflow.SunflowAPI;
import org.sunflow.core.ParameterList;
import org.sunflow.core.Shader;
import org.sunflow.core.ShadingState;
import org.sunflow.image.Color;

public class AmbientOcclusionShader implements Shader {
    private Color bright;
    private Color dark;
    private int samples;
    private float maxDist;

    public AmbientOcclusionShader() {
        bright = Color.WHITE;
        dark = Color.BLACK;
        samples = 32;
        maxDist = Float.POSITIVE_INFINITY;
    }

    public AmbientOcclusionShader(Color c, float d) {
        this();
        bright = c;
        maxDist = d;
    }

    public boolean update(ParameterList pl, SunflowAPI api) {
        bright = pl.getColor("bright", bright);
        dark = pl.getColor("dark", dark);
        samples = pl.getInt("samples", samples);
        maxDist = pl.getFloat("maxdist", maxDist);
        if (maxDist <= 0)
            maxDist = Float.POSITIVE_INFINITY;
        return true;
    }

    public Color getBrightColor(ShadingState state) {
        return bright;
    }

    public Color getRadiance(ShadingState state) {
        return state.occlusion(samples, maxDist, getBrightColor(state), dark);
    }

    public void scatterPhoton(ShadingState state, Color power) {
    }
}