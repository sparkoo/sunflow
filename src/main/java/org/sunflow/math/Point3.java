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

public final class Point3 {
    public float x, y, z;

    public Point3() {
    }

    public Point3(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Point3(Point3 p) {
        x = p.x;
        y = p.y;
        z = p.z;
    }

    public float get(int i) {
        switch (i) {
            case 0:
                return x;
            case 1:
                return y;
            default:
                return z;
        }
    }

    public final float distanceTo(Point3 p) {
        float dx = x - p.x;
        float dy = y - p.y;
        float dz = z - p.z;
        return (float) Math.sqrt((dx * dx) + (dy * dy) + (dz * dz));
    }

    public final float distanceTo(float px, float py, float pz) {
        float dx = x - px;
        float dy = y - py;
        float dz = z - pz;
        return (float) Math.sqrt((dx * dx) + (dy * dy) + (dz * dz));
    }

    public final float distanceToSquared(Point3 p) {
        float dx = x - p.x;
        float dy = y - p.y;
        float dz = z - p.z;
        return (dx * dx) + (dy * dy) + (dz * dz);
    }

    public final float distanceToSquared(float px, float py, float pz) {
        float dx = x - px;
        float dy = y - py;
        float dz = z - pz;
        return (dx * dx) + (dy * dy) + (dz * dz);
    }

    public final Point3 set(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
        return this;
    }

    public final Point3 set(Point3 p) {
        x = p.x;
        y = p.y;
        z = p.z;
        return this;
    }

    public static final Point3 add(Point3 p, Vector3 v, Point3 dest) {
        dest.x = p.x + v.x;
        dest.y = p.y + v.y;
        dest.z = p.z + v.z;
        return dest;
    }

    public static final Vector3 sub(Point3 p1, Point3 p2, Vector3 dest) {
        dest.x = p1.x - p2.x;
        dest.y = p1.y - p2.y;
        dest.z = p1.z - p2.z;
        return dest;
    }

    public static final Point3 mid(Point3 p1, Point3 p2, Point3 dest) {
        dest.x = 0.5f * (p1.x + p2.x);
        dest.y = 0.5f * (p1.y + p2.y);
        dest.z = 0.5f * (p1.z + p2.z);
        return dest;
    }

    public static final Point3 blend(Point3 p0, Point3 p1, float blend, Point3 dest) {
        dest.x = (1 - blend) * p0.x + blend * p1.x;
        dest.y = (1 - blend) * p0.y + blend * p1.y;
        dest.z = (1 - blend) * p0.z + blend * p1.z;
        return dest;
    }

    public static final Vector3 normal(Point3 p0, Point3 p1, Point3 p2) {
        float edge1x = p1.x - p0.x;
        float edge1y = p1.y - p0.y;
        float edge1z = p1.z - p0.z;
        float edge2x = p2.x - p0.x;
        float edge2y = p2.y - p0.y;
        float edge2z = p2.z - p0.z;
        float nx = edge1y * edge2z - edge1z * edge2y;
        float ny = edge1z * edge2x - edge1x * edge2z;
        float nz = edge1x * edge2y - edge1y * edge2x;
        return new Vector3(nx, ny, nz);
    }

    public static final Vector3 normal(Point3 p0, Point3 p1, Point3 p2, Vector3 dest) {
        float edge1x = p1.x - p0.x;
        float edge1y = p1.y - p0.y;
        float edge1z = p1.z - p0.z;
        float edge2x = p2.x - p0.x;
        float edge2y = p2.y - p0.y;
        float edge2z = p2.z - p0.z;
        dest.x = edge1y * edge2z - edge1z * edge2y;
        dest.y = edge1z * edge2x - edge1x * edge2z;
        dest.z = edge1x * edge2y - edge1y * edge2x;
        return dest;
    }

    @Override
    public final String toString() {
        return String.format("(%.2f, %.2f, %.2f)", x, y, z);
    }
}