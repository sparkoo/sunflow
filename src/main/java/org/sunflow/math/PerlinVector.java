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

package org.sunflow.math;

/**
 * Vector versions of the standard noise functions. These are provided to
 * emulate standard renderman calls.This code was adapted mainly from the
 * mrclasses package by Gonzalo Garramuno
 * (http://sourceforge.net/projects/mrclasses/).
 */
public class PerlinVector {
    private static final float P1x = 0.34f;
    private static final float P1y = 0.66f;
    private static final float P1z = 0.237f;
    private static final float P2x = 0.011f;
    private static final float P2y = 0.845f;
    private static final float P2z = 0.037f;
    private static final float P3x = 0.34f;
    private static final float P3y = 0.12f;
    private static final float P3z = 0.9f;

    public static final Vector3 snoise(float x) {
        return new Vector3(PerlinScalar.snoise(x + P1x), PerlinScalar.snoise(x + P2x), PerlinScalar.snoise(x + P3x));
    }

    public static final Vector3 snoise(float x, float y) {
        return new Vector3(PerlinScalar.snoise(x + P1x, y + P1y), PerlinScalar.snoise(x + P2x, y + P2y), PerlinScalar.snoise(x + P3x, y + P3y));
    }

    public static final Vector3 snoise(float x, float y, float z) {
        return new Vector3(PerlinScalar.snoise(x + P1x, y + P1y, z + P1z), PerlinScalar.snoise(x + P2x, y + P2y, z + P2z), PerlinScalar.snoise(x + P3x, y + P3y, z + P3z));
    }

    public static final Vector3 snoise(float x, float y, float z, float t) {
        return new Vector3(PerlinScalar.snoise(x + P1x, y + P1y, z + P1z, t), PerlinScalar.snoise(x + P2x, y + P2y, z + P2z, t), PerlinScalar.snoise(x + P3x, y + P3y, z + P3z, t));
    }

    public static final Vector3 snoise(Point2 p) {
        return snoise(p.x, p.y);
    }

    public static final Vector3 snoise(Point3 p) {
        return snoise(p.x, p.y, p.z);
    }

    public static final Vector3 snoise(Point3 p, float t) {
        return snoise(p.x, p.y, p.z, t);
    }

    public static final Vector3 noise(float x) {
        return new Vector3(PerlinScalar.noise(x + P1x), PerlinScalar.noise(x + P2x), PerlinScalar.noise(x + P3x));
    }

    public static final Vector3 noise(float x, float y) {
        return new Vector3(PerlinScalar.noise(x + P1x, y + P1y), PerlinScalar.noise(x + P2x, y + P2y), PerlinScalar.noise(x + P3x, y + P3y));
    }

    public static final Vector3 noise(float x, float y, float z) {
        return new Vector3(PerlinScalar.noise(x + P1x, y + P1y, z + P1z), PerlinScalar.noise(x + P2x, y + P2y, z + P2z), PerlinScalar.noise(x + P3x, y + P3y, z + P3z));
    }

    public static final Vector3 noise(float x, float y, float z, float t) {
        return new Vector3(PerlinScalar.noise(x + P1x, y + P1y, z + P1z, t), PerlinScalar.noise(x + P2x, y + P2y, z + P2z, t), PerlinScalar.noise(x + P3x, y + P3y, z + P3z, t));
    }

    public static final Vector3 noise(Point2 p) {
        return noise(p.x, p.y);
    }

    public static final Vector3 noise(Point3 p) {
        return noise(p.x, p.y, p.z);
    }

    public static final Vector3 noise(Point3 p, float t) {
        return noise(p.x, p.y, p.z, t);
    }

    public static final Vector3 pnoise(float x, float period) {
        return new Vector3(PerlinScalar.pnoise(x + P1x, period), PerlinScalar.pnoise(x + P2x, period), PerlinScalar.pnoise(x + P3x, period));
    }

    public static final Vector3 pnoise(float x, float y, float w, float h) {
        return new Vector3(PerlinScalar.pnoise(x + P1x, y + P1y, w, h), PerlinScalar.pnoise(x + P2x, y + P2y, w, h), PerlinScalar.pnoise(x + P3x, y + P3y, w, h));
    }

    public static final Vector3 pnoise(float x, float y, float z, float w, float h, float d) {
        return new Vector3(PerlinScalar.pnoise(x + P1x, y + P1y, z + P1z, w, h, d), PerlinScalar.pnoise(x + P2x, y + P2y, z + P2z, w, h, d), PerlinScalar.pnoise(x + P3x, y + P3y, z + P3z, w, h, d));
    }

    public static final Vector3 pnoise(float x, float y, float z, float t, float w, float h, float d, float p) {
        return new Vector3(PerlinScalar.pnoise(x + P1x, y + P1y, z + P1z, t, w, h, d, p), PerlinScalar.pnoise(x + P2x, y + P2y, z + P2z, t, w, h, d, p), PerlinScalar.pnoise(x + P3x, y + P3y, z + P3z, t, w, h, d, p));
    }

    public static final Vector3 pnoise(Point2 p, float periodx, float periody) {
        return pnoise(p.x, p.y, periodx, periody);
    }

    public static final Vector3 pnoise(Point3 p, Vector3 period) {
        return pnoise(p.x, p.y, p.z, period.x, period.y, period.z);
    }

    public static final Vector3 pnoise(Point3 p, float t, Vector3 pperiod, float tperiod) {
        return pnoise(p.x, p.y, p.z, t, pperiod.x, pperiod.y, pperiod.z, tperiod);
    }

    public static final Vector3 spnoise(float x, float period) {
        return new Vector3(PerlinScalar.spnoise(x + P1x, period), PerlinScalar.spnoise(x + P2x, period), PerlinScalar.spnoise(x + P3x, period));
    }

    public static final Vector3 spnoise(float x, float y, float w, float h) {
        return new Vector3(PerlinScalar.spnoise(x + P1x, y + P1y, w, h), PerlinScalar.spnoise(x + P2x, y + P2y, w, h), PerlinScalar.spnoise(x + P3x, y + P3y, w, h));
    }

    public static final Vector3 spnoise(float x, float y, float z, float w, float h, float d) {
        return new Vector3(PerlinScalar.spnoise(x + P1x, y + P1y, z + P1z, w, h, d), PerlinScalar.spnoise(x + P2x, y + P2y, z + P2z, w, h, d), PerlinScalar.spnoise(x + P3x, y + P3y, z + P3z, w, h, d));
    }

    public static final Vector3 spnoise(float x, float y, float z, float t, float w, float h, float d, float p) {
        return new Vector3(PerlinScalar.spnoise(x + P1x, y + P1y, z + P1z, t, w, h, d, p), PerlinScalar.spnoise(x + P2x, y + P2y, z + P2z, t, w, h, d, p), PerlinScalar.spnoise(x + P3x, y + P3y, z + P3z, t, w, h, d, p));
    }

    public static final Vector3 spnoise(Point2 p, float periodx, float periody) {
        return spnoise(p.x, p.y, periodx, periody);
    }

    public static final Vector3 spnoise(Point3 p, Vector3 period) {
        return spnoise(p.x, p.y, p.z, period.x, period.y, period.z);
    }

    public static final Vector3 spnoise(Point3 p, float t, Vector3 pperiod, float tperiod) {
        return spnoise(p.x, p.y, p.z, t, pperiod.x, pperiod.y, pperiod.z, tperiod);
    }
}