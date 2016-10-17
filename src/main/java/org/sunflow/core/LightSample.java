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
import org.sunflow.math.Vector3;

/**
 * Represents a sample taken from a light source that faces a point being
 * shaded.
 */
public class LightSample {
    private Ray shadowRay; // ray to be used to evaluate if the point is in
    // shadow
    private Color ldiff;
    private Color lspec;
    LightSample next; // pointer to next item in a linked list of samples

    /**
     * Creates a new light sample object (invalid by default).
     */
    public LightSample() {
        ldiff = lspec = null;
        shadowRay = null;
        next = null;
    }

    boolean isValid() {
        return ldiff != null && lspec != null && shadowRay != null;
    }

    /**
     * Set the current shadow ray. The ray's direction is used as the sample's
     * orientation.
     * 
     * @param shadowRay shadow ray from the point being shaded towards the light
     */
    public void setShadowRay(Ray shadowRay) {
        this.shadowRay = shadowRay;
    }

    /**
     * Trace the shadow ray, attenuating the sample's color by the opacity of
     * intersected objects.
     * 
     * @param state shading state representing the point to be shaded
     */
    public final void traceShadow(ShadingState state) {
        Color opacity = state.traceShadow(shadowRay);
        Color.blend(ldiff, Color.BLACK, opacity, ldiff);
        Color.blend(lspec, Color.BLACK, opacity, lspec);
    }

    /**
     * Get the sample's shadow ray.
     * 
     * @return shadow ray
     */
    public Ray getShadowRay() {
        return shadowRay;
    }

    /**
     * Get diffuse radiance.
     * 
     * @return diffuse radiance
     */
    public Color getDiffuseRadiance() {
        return ldiff;
    }

    /**
     * Get specular radiance.
     * 
     * @return specular radiance
     */
    public Color getSpecularRadiance() {
        return lspec;
    }

    /**
     * Set the diffuse and specular radiance emitted by the current light
     * source. These should usually be the same, but are distinguished to allow
     * for non-physical light setups or light source types which compute diffuse
     * and specular responses seperately.
     * 
     * @param d diffuse radiance
     * @param s specular radiance
     */
    public void setRadiance(Color d, Color s) {
        ldiff = d.copy();
        lspec = s.copy();
    }

    /**
     * Compute a dot product between the current shadow ray direction and the
     * specified vector.
     * 
     * @param v direction vector
     * @return dot product of the vector with the shadow ray direction
     */
    public float dot(Vector3 v) {
        return shadowRay.dot(v);
    }
}