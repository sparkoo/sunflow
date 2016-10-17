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

import org.sunflow.math.BoundingBox;
import org.sunflow.math.Matrix4;

/**
 * This class represents an object made up of many primitives.
 */
public interface PrimitiveList extends RenderObject {
    /**
     * Compute a bounding box of this object in world space, using the specified
     * object-to-world transformation matrix. The bounds should be as exact as
     * possible, if they are difficult or expensive to compute exactly, you may
     * use {@link Matrix4#transform(BoundingBox)}. If the matrix is
     * <code>null</code> no transformation is needed, and object space is
     * equivalent to world space.
     * 
     * @param o2w object to world transformation matrix
     * @return object bounding box in world space
     */
    public BoundingBox getWorldBounds(Matrix4 o2w);

    /**
     * Returns the number of individual primtives in this aggregate object.
     * 
     * @return number of primitives
     */
    public int getNumPrimitives();

    /**
     * Retrieve the bounding box component of a particular primitive in object
     * space. Even indexes get minimum values, while odd indexes get the maximum
     * values for each axis.
     * 
     * @param primID primitive index
     * @param i bounding box side index
     * @return value of the request bound
     */
    public float getPrimitiveBound(int primID, int i);

    /**
     * Intersect the specified primitive in local space.
     * 
     * @param r ray in the object's local space
     * @param primID primitive index to intersect
     * @param state intersection state
     * @see Ray#setMax(float)
     * @see IntersectionState#setIntersection(int, float, float)
     */
    public void intersectPrimitive(Ray r, int primID, IntersectionState state);

    /**
     * Prepare the specified {@link ShadingState} by setting all of its internal
     * parameters.
     * 
     * @param state shading state to fill in
     */
    public void prepareShadingState(ShadingState state);

    /**
     * Create a new {@link PrimitiveList} object suitable for baking lightmaps.
     * This means a set of primitives laid out in the unit square UV space. This
     * method is optional, objects which do not support it should simply return
     * <code>null</code>.
     * 
     * @return a list of baking primitives
     */
    public PrimitiveList getBakingPrimitives();
}