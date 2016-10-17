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
import org.sunflow.core.Ray;
import org.sunflow.core.Shader;
import org.sunflow.core.ShadingState;
import org.sunflow.image.Color;
import org.sunflow.math.Vector3;

public class MirrorShader implements Shader {
    private Color color;

    public MirrorShader() {
        color = Color.WHITE;
    }

    public boolean update(ParameterList pl, SunflowAPI api) {
        color = pl.getColor("color", color);
        return true;
    }

    public Color getRadiance(ShadingState state) {
        if (!state.includeSpecular())
            return Color.BLACK;
        state.faceforward();
        float cos = state.getCosND();
        float dn = 2 * cos;
        Vector3 refDir = new Vector3();
        refDir.x = (dn * state.getNormal().x) + state.getRay().getDirection().x;
        refDir.y = (dn * state.getNormal().y) + state.getRay().getDirection().y;
        refDir.z = (dn * state.getNormal().z) + state.getRay().getDirection().z;
        Ray refRay = new Ray(state.getPoint(), refDir);

        // compute Fresnel term
        cos = 1 - cos;
        float cos2 = cos * cos;
        float cos5 = cos2 * cos2 * cos;
        Color ret = Color.white();
        ret.sub(color);
        ret.mul(cos5);
        ret.add(color);
        return ret.mul(state.traceReflection(refRay, 0));
    }

    public void scatterPhoton(ShadingState state, Color power) {
        float avg = color.getAverage();
        double rnd = state.getRandom(0, 0, 1);
        if (rnd >= avg)
            return;
        state.faceforward();
        float cos = state.getCosND();
        power.mul(color).mul(1.0f / avg);
        // photon is reflected
        float dn = 2 * cos;
        Vector3 dir = new Vector3();
        dir.x = (dn * state.getNormal().x) + state.getRay().getDirection().x;
        dir.y = (dn * state.getNormal().y) + state.getRay().getDirection().y;
        dir.z = (dn * state.getNormal().z) + state.getRay().getDirection().z;
        state.traceReflectionPhoton(new Ray(state.getPoint(), dir), power);
    }
}