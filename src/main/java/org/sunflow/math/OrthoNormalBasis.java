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

public final class OrthoNormalBasis {
    private Vector3 u, v, w;

    private OrthoNormalBasis() {
        u = new Vector3();
        v = new Vector3();
        w = new Vector3();
    }

    public void flipU() {
        u.negate();
    }

    public void flipV() {
        v.negate();
    }

    public void flipW() {
        w.negate();
    }

    public void swapUV() {
        Vector3 t = u;
        u = v;
        v = t;
    }

    public void swapVW() {
        Vector3 t = v;
        v = w;
        w = t;
    }

    public void swapWU() {
        Vector3 t = w;
        w = u;
        u = t;
    }

    public Vector3 transform(Vector3 a, Vector3 dest) {
        dest.x = (a.x * u.x) + (a.y * v.x) + (a.z * w.x);
        dest.y = (a.x * u.y) + (a.y * v.y) + (a.z * w.y);
        dest.z = (a.x * u.z) + (a.y * v.z) + (a.z * w.z);
        return dest;
    }

    public Vector3 transform(Vector3 a) {
        float x = (a.x * u.x) + (a.y * v.x) + (a.z * w.x);
        float y = (a.x * u.y) + (a.y * v.y) + (a.z * w.y);
        float z = (a.x * u.z) + (a.y * v.z) + (a.z * w.z);
        return a.set(x, y, z);
    }

    public Vector3 untransform(Vector3 a, Vector3 dest) {
        dest.x = Vector3.dot(a, u);
        dest.y = Vector3.dot(a, v);
        dest.z = Vector3.dot(a, w);
        return dest;
    }

    public Vector3 untransform(Vector3 a) {
        float x = Vector3.dot(a, u);
        float y = Vector3.dot(a, v);
        float z = Vector3.dot(a, w);
        return a.set(x, y, z);
    }

    public float untransformX(Vector3 a) {
        return Vector3.dot(a, u);
    }

    public float untransformY(Vector3 a) {
        return Vector3.dot(a, v);
    }

    public float untransformZ(Vector3 a) {
        return Vector3.dot(a, w);
    }

    public static final OrthoNormalBasis makeFromW(Vector3 w) {
        OrthoNormalBasis onb = new OrthoNormalBasis();
        w.normalize(onb.w);
        if ((Math.abs(onb.w.x) < Math.abs(onb.w.y)) && (Math.abs(onb.w.x) < Math.abs(onb.w.z))) {
            onb.v.x = 0;
            onb.v.y = onb.w.z;
            onb.v.z = -onb.w.y;
        } else if (Math.abs(onb.w.y) < Math.abs(onb.w.z)) {
            onb.v.x = onb.w.z;
            onb.v.y = 0;
            onb.v.z = -onb.w.x;
        } else {
            onb.v.x = onb.w.y;
            onb.v.y = -onb.w.x;
            onb.v.z = 0;
        }
        Vector3.cross(onb.v.normalize(), onb.w, onb.u);
        return onb;
    }

    public static final OrthoNormalBasis makeFromWV(Vector3 w, Vector3 v) {
        OrthoNormalBasis onb = new OrthoNormalBasis();
        w.normalize(onb.w);
        Vector3.cross(v, onb.w, onb.u).normalize();
        Vector3.cross(onb.w, onb.u, onb.v);
        return onb;
    }
}