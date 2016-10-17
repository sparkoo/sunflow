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

package org.sunflow.image;

public final class XYZColor {
    private float X, Y, Z;

    public XYZColor() {
    }

    public XYZColor(float X, float Y, float Z) {
        this.X = X;
        this.Y = Y;
        this.Z = Z;
    }

    public final float getX() {
        return X;
    }

    public final float getY() {
        return Y;
    }

    public final float getZ() {
        return Z;
    }

    public final XYZColor mul(float s) {
        X *= s;
        Y *= s;
        Z *= s;
        return this;
    }

    public final void normalize() {
        float XYZ = X + Y + Z;
        if (XYZ < 1e-6f)
            return;
        float s = 1 / XYZ;
        X *= s;
        Y *= s;
        Z *= s;
    }

    @Override
    public final String toString() {
        return String.format("(%.3f, %.3f, %.3f)", X, Y, Z);
    }
}