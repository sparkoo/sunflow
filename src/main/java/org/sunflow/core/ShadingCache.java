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

public class ShadingCache {
    private Sample first;
    private int depth;
    // stats
    long hits;
    long misses;
    long sumDepth;
    long numCaches;

    private static class Sample {
        Instance i;
        Shader s;
        float nx, ny, nz;
        float dx, dy, dz;
        Color c;
        Sample next; // linked list
    }

    public ShadingCache() {
        reset();
        hits = 0;
        misses = 0;
    }

    public void reset() {
        sumDepth += depth;
        if (depth > 0)
            numCaches++;
        first = null;
        depth = 0;
    }

    public Color lookup(ShadingState state, Shader shader) {
        if (state.getNormal() == null)
            return null;
        // search further
        for (Sample s = first; s != null; s = s.next) {
            if (s.i != state.getInstance())
                continue;
            if (s.s != shader)
                continue;
            if (state.getRay().dot(s.dx, s.dy, s.dz) < 0.999f)
                continue;
            if (state.getNormal().dot(s.nx, s.ny, s.nz) < 0.99f)
                continue;
            // we have a match
            hits++;
            return s.c;
        }
        misses++;
        return null;
    }

    public void add(ShadingState state, Shader shader, Color c) {
        if (state.getNormal() == null)
            return;
        depth++;
        Sample s = new Sample();
        s.i = state.getInstance();
        s.s = shader;
        s.c = c;
        s.dx = state.getRay().dx;
        s.dy = state.getRay().dy;
        s.dz = state.getRay().dz;
        s.nx = state.getNormal().x;
        s.ny = state.getNormal().y;
        s.nz = state.getNormal().z;
        s.next = first;
        first = s;
    }
}