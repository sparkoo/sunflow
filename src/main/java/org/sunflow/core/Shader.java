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

package org.sunflow.core;

import org.sunflow.image.Color;

/**
 * A shader represents a particular light-surface interaction.
 */
public interface Shader extends RenderObject {
    /**
     * Gets the radiance for a specified rendering state. When this method is
     * called, you can assume that a hit has been registered in the state and
     * that the hit surface information has been computed.
     * 
     * @param state current render state
     * @return color emitted or reflected by the shader
     */
    public Color getRadiance(ShadingState state);

    /**
     * Scatter a photon with the specied power. Incoming photon direction is
     * specified by the ray attached to the current render state. This method
     * can safely do nothing if photon scattering is not supported or relevant
     * for the shader type.
     * 
     * @param state current state
     * @param power power of the incoming photon.
     */
    public void scatterPhoton(ShadingState state, Color power);
}