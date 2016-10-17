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
import org.sunflow.math.BoundingBox;
import org.sunflow.math.Vector3;

/**
 * Describes an object which can store photons.
 */
public interface PhotonStore {
    /**
     * Number of photons to emit from this surface.
     * 
     * @return number of photons
     */
    int numEmit();

    /**
     * Initialize this object for the specified scene size.
     * 
     * @param sceneBounds scene bounding box
     */
    void prepare(Options options, BoundingBox sceneBounds);

    /**
     * Store the specified photon.
     * 
     * @param state shading state
     * @param dir photon direction
     * @param power photon power
     * @param diffuse diffuse color at the hit point
     */
    void store(ShadingState state, Vector3 dir, Color power, Color diffuse);

    /**
     * Initialize the map after all photons have been stored. This can be used
     * to balance a kd-tree based photon map for example.
     */
    void init();

    /**
     * Allow photons reflected diffusely?
     * 
     * @return <code>true</code> if diffuse bounces should be traced
     */
    boolean allowDiffuseBounced();

    /**
     * Allow specularly reflected photons?
     * 
     * @return <code>true</code> if specular reflection bounces should be
     *         traced
     */
    boolean allowReflectionBounced();

    /**
     * Allow refracted photons?
     * 
     * @return <code>true</code> if refracted bounces should be traced
     */
    boolean allowRefractionBounced();
}