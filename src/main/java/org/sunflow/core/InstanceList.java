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

import org.sunflow.SunflowAPI;
import org.sunflow.math.BoundingBox;
import org.sunflow.math.Matrix4;

final class InstanceList implements PrimitiveList {
    private Instance[] instances;
    private Instance[] lights;

    InstanceList() {
        instances = new Instance[0];
        clearLightSources();
    }

    InstanceList(Instance[] instances) {
        this.instances = instances;
        clearLightSources();
    }

    void addLightSourceInstances(Instance[] lights) {
        this.lights = lights;
    }

    void clearLightSources() {
        lights = new Instance[0];
    }

    public final float getPrimitiveBound(int primID, int i) {
        if (primID < instances.length)
            return instances[primID].getBounds().getBound(i);
        else
            return lights[primID - instances.length].getBounds().getBound(i);
    }

    public final BoundingBox getWorldBounds(Matrix4 o2w) {
        BoundingBox bounds = new BoundingBox();
        for (Instance i : instances)
            bounds.include(i.getBounds());
        for (Instance i : lights)
            bounds.include(i.getBounds());
        return bounds;
    }

    public final void intersectPrimitive(Ray r, int primID, IntersectionState state) {
        if (primID < instances.length)
            instances[primID].intersect(r, state);
        else
            lights[primID - instances.length].intersect(r, state);
    }

    public final int getNumPrimitives() {
        return instances.length + lights.length;
    }

    public final int getNumPrimitives(int primID) {
        return primID < instances.length ? instances[primID].getNumPrimitives() : lights[primID - instances.length].getNumPrimitives();
    }

    public final void prepareShadingState(ShadingState state) {
        state.getInstance().prepareShadingState(state);
    }

    public boolean update(ParameterList pl, SunflowAPI api) {
        return true;
    }

    public PrimitiveList getBakingPrimitives() {
        return null;
    }
}