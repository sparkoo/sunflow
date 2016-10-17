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
 * This class is used to store ray/object intersections. It also provides
 * additional data to assist {@link AccelerationStructure} objects with
 * traversal.
 */
public final class IntersectionState {
    private static final int MAX_STACK_SIZE = 64;
    float time;
    float u, v, w;
    Instance instance;
    int id;
    private final StackNode[][] stacks = new StackNode[2][MAX_STACK_SIZE];
    Instance current;
    long numEyeRays;
    long numShadowRays;
    long numReflectionRays;
    long numGlossyRays;
    long numRefractionRays;
    long numRays;

    /**
     * Traversal stack node, helps with tree-based {@link AccelerationStructure}
     * traversal.
     */
    public static final class StackNode {
        public int node;
        public float near;
        public float far;
    }

    /**
     * Initializes all traversal stacks.
     */
    public IntersectionState() {
        for (int i = 0; i < stacks.length; i++)
            for (int j = 0; j < stacks[i].length; j++)
                stacks[i][j] = new StackNode();
    }

    /**
     * Returns the time at which the intersection should be calculated. This
     * will be constant for a given ray-tree. This value is guarenteed to be
     * between the camera's shutter open and shutter close time.
     * 
     * @return time value
     */
    public float getTime() {
        return time;
    }

    /**
     * Get stack object for tree based {@link AccelerationStructure}s.
     * 
     * @return array of stack nodes
     */
    public final StackNode[] getStack() {
        return current == null ? stacks[0] : stacks[1];
    }

    /**
     * Checks to see if a hit has been recorded.
     * 
     * @return <code>true</code> if a hit has been recorded,
     *         <code>false</code> otherwise
     */
    public final boolean hit() {
        return instance != null;
    }

    /**
     * Record an intersection with the specified primitive id. The parent object
     * is assumed to be the current instance. The u and v parameters are used to
     * pinpoint the location on the surface if needed.
     * 
     * @param id primitive id of the intersected object
     */
    public final void setIntersection(int id) {
        instance = current;
        this.id = id;
    }

    /**
     * Record an intersection with the specified primitive id. The parent object
     * is assumed to be the current instance. The u and v parameters are used to
     * pinpoint the location on the surface if needed.
     * 
     * @param id primitive id of the intersected object
     * @param u u surface paramater of the intersection point
     * @param v v surface parameter of the intersection point
     */
    public final void setIntersection(int id, float u, float v) {
        instance = current;
        this.id = id;
        this.u = u;
        this.v = v;
    }

    /**
     * Record an intersection with the specified primitive id. The parent object
     * is assumed to be the current instance. The u and v parameters are used to
     * pinpoint the location on the surface if needed.
     * 
     * @param id primitive id of the intersected object
     * @param u u surface paramater of the intersection point
     * @param v v surface parameter of the intersection point
     */
    public final void setIntersection(int id, float u, float v, float w) {
        instance = current;
        this.id = id;
        this.u = u;
        this.v = v;
        this.w = w;
    }
}