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

package org.sunflow.util;

public final class FloatArray {
    private float[] array;
    private int size;

    public FloatArray() {
        array = new float[10];
        size = 0;
    }

    public FloatArray(int capacity) {
        array = new float[capacity];
        size = 0;
    }

    /**
     * Append a float to the end of the array.
     * 
     * @param f
     */
    public final void add(float f) {
        if (size == array.length) {
            float[] oldArray = array;
            array = new float[(size * 3) / 2 + 1];
            System.arraycopy(oldArray, 0, array, 0, size);
        }
        array[size] = f;
        size++;
    }

    /**
     * Write a value to the specified index. Assumes the array is already big
     * enough.
     * 
     * @param index
     * @param value
     */
    public final void set(int index, float value) {
        array[index] = value;
    }

    /**
     * Read value from the array.
     * 
     * @param index index into the array
     * @return value at the specified index
     */
    public final float get(int index) {
        return array[index];
    }

    /**
     * Returns the number of elements added to the array.
     * 
     * @return current size of the array
     */
    public final int getSize() {
        return size;
    }

    /**
     * Return a copy of the array, trimmed to fit the size of its contents
     * exactly.
     * 
     * @return a new array of exactly the right length
     */
    public final float[] trim() {
        if (size < array.length) {
            float[] oldArray = array;
            array = new float[size];
            System.arraycopy(oldArray, 0, array, 0, size);
        }
        return array;
    }
}