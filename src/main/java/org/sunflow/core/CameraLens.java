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

/**
 * Represents a mapping from the 3D scene onto the final image. A camera lens is
 * responsible for determining what ray to cast through each pixel.
 */
public interface CameraLens extends RenderObject {
    /**
     * Create a new {@link Ray ray}to be cast through pixel (x,y) on the image
     * plane. Two sampling parameters are provided for lens sampling. They are
     * guarenteed to be in the interval [0,1). They can be used to perturb the
     * position of the source of the ray on the lens of the camera for DOF
     * effects. A third sampling parameter is provided for motion blur effects.
     * Note that the {@link Camera} class already handles camera movement motion
     * blur. Rays should be generated in camera space - that is, with the eye at
     * the origin, looking down the -Z axis, with +Y pointing up.
     * 
     * @param x x coordinate of the (sub)pixel
     * @param y y coordinate of the (sub)pixel
     * @param imageWidth image width in pixels
     * @param imageHeight image height in pixels
     * @param lensX x lens sampling parameter
     * @param lensY y lens sampling parameter
     * @param time time sampling parameter
     * @return a new ray passing through the given pixel
     */
    public Ray getRay(float x, float y, int imageWidth, int imageHeight, double lensX, double lensY, double time);
}