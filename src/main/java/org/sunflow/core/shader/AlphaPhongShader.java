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
import org.sunflow.core.AlphaShader;
import org.sunflow.core.ParameterList;
import org.sunflow.core.ShadingState;
import org.sunflow.image.Color;

public class AlphaPhongShader extends PhongShader implements AlphaShader {

    private float transparency;
    private Color transparencyColor;

    public AlphaPhongShader() {
        transparency = 0f;
        transparencyColor = new Color(transparency);
    }

    public boolean update(ParameterList pl, SunflowAPI api) {
        transparency = pl.getFloat("transparency", transparency);
        return super.update(pl,api);
    }

    public Color getRadiance(ShadingState state) {
        Color phong = super.getRadiance(state);
        return Color.blend(phong, state.traceTransparency(), transparency);
    }

    public Color getOpacity(ShadingState state) {
        return transparencyColor;
    }

}
