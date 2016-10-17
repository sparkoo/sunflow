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
import org.sunflow.core.accel.NullAccelerator;
import org.sunflow.math.BoundingBox;
import org.sunflow.math.Matrix4;
import org.sunflow.system.UI;
import org.sunflow.system.UI.Module;

/**
 * This class represent a geometric object in its native object space. These
 * object are not rendered directly, they must be instanced via {@link Instance}.
 * This class performs all the bookkeeping needed for on-demand tesselation and
 * acceleration structure building.
 */
public class Geometry implements RenderObject {
    private Tesselatable tesselatable;
    private PrimitiveList primitives;
    private AccelerationStructure accel;
    private int builtAccel;
    private int builtTess;
    private String acceltype;

    /**
     * Create a geometry from the specified tesselatable object. The actual
     * renderable primitives will be generated on demand.
     * 
     * @param tesselatable tesselation object
     */
    public Geometry(Tesselatable tesselatable) {
        this.tesselatable = tesselatable;
        primitives = null;
        accel = null;
        builtAccel = 0;
        builtTess = 0;
        acceltype = null;
    }

    /**
     * Create a geometry from the specified primitive aggregate. The
     * acceleration structure for this object will be built on demand.
     * 
     * @param primitives primitive list object
     */
    public Geometry(PrimitiveList primitives) {
        tesselatable = null;
        this.primitives = primitives;
        accel = null;
        builtAccel = 0;
        builtTess = 1; // already tesselated
    }

    public boolean update(ParameterList pl, SunflowAPI api) {
        acceltype = pl.getString("accel", acceltype);
        // clear up old tesselation if it exists
        if (tesselatable != null) {
            primitives = null;
            builtTess = 0;
        }
        // clear acceleration structure so it will be rebuilt
        accel = null;
        builtAccel = 0;
        if (tesselatable != null)
            return tesselatable.update(pl, api);
        // update primitives
        return primitives.update(pl, api);
    }

    int getNumPrimitives() {
        return primitives == null ? 0 : primitives.getNumPrimitives();
    }

    BoundingBox getWorldBounds(Matrix4 o2w) {
        if (primitives == null) {

            BoundingBox b = tesselatable.getWorldBounds(o2w);
            if (b != null)
                return b;
            if (builtTess == 0)
                tesselate();
            if (primitives == null)
                return null; // failed tesselation, return infinite bounding
            // box
        }
        return primitives.getWorldBounds(o2w);
    }

    void intersect(Ray r, IntersectionState state) {
        if (builtTess == 0)
            tesselate();
        if (builtAccel == 0)
            build();
        accel.intersect(r, state);
    }

    private synchronized void tesselate() {
        // double check flag
        if (builtTess != 0)
            return;
        if (tesselatable != null && primitives == null) {
            UI.printInfo(Module.GEOM, "Tesselating geometry ...");
            primitives = tesselatable.tesselate();
            if (primitives == null)
                UI.printError(Module.GEOM, "Tesselation failed - geometry will be discarded");
            else
                UI.printDetailed(Module.GEOM, "Tesselation produced %d primitives", primitives.getNumPrimitives());
        }
        builtTess = 1;
    }

    private synchronized void build() {
        // double check flag
        if (builtAccel != 0)
            return;
        if (primitives != null) {
            int n = primitives.getNumPrimitives();
            if (n >= 1000)
                UI.printInfo(Module.GEOM, "Building acceleration structure for %d primitives ...", n);
            accel = AccelerationStructureFactory.create(acceltype, n, true);
            accel.build(primitives);
        } else {
            // create an empty accelerator to avoid having to check for null
            // pointers in the intersect method
            accel = new NullAccelerator();
        }
        builtAccel = 1;
    }

    void prepareShadingState(ShadingState state) {
        primitives.prepareShadingState(state);
    }

    PrimitiveList getBakingPrimitives() {
        if (builtTess == 0)
            tesselate();
        if (primitives == null)
            return null;
        return primitives.getBakingPrimitives();
    }

    PrimitiveList getPrimitiveList() {
        return primitives;
    }
}