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
 * This represents a global illumination algorithm. It provides an interface to
 * compute indirect diffuse bounces of light and make those results available to
 * shaders.
 */
public interface GIEngine {
    /**
     * This is an optional method for engines that contain a secondary
     * illumination engine which can return an approximation of the global
     * radiance in the scene (like a photon map). Engines can safely return
     * <code>Color.BLACK</code> if they can't or don't wish to support this.
     * 
     * @param state shading state
     * @return color approximating global radiance
     */
    public Color getGlobalRadiance(ShadingState state);

    /**
     * Initialize the engine. This is called before rendering begins.
     * 
     * @return <code>true</code> if the init phase succeeded,
     *         <code>false</code> otherwise
     */
    public boolean init(Options options, Scene scene);

    /**
     * Return the incomming irradiance due to indirect diffuse illumination at
     * the specified surface point.
     * 
     * @param state current render state describing the point to be computed
     * @param diffuseReflectance diffuse albedo of the point being shaded, this
     *            can be used for importance tracking
     * @return irradiance from indirect diffuse illumination at the specified
     *         point
     */
    public Color getIrradiance(ShadingState state, Color diffuseReflectance);
}