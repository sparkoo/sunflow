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

package org.sunflow.image.writers;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.sunflow.image.BitmapWriter;
import org.sunflow.image.Color;
import org.sunflow.image.ColorEncoder;

public class HDRBitmapWriter implements BitmapWriter {
    private String filename;
    private int width, height;
    private int[] data;

    public void configure(String option, String value) {
    }

    public void openFile(String filename) throws IOException {
        this.filename = filename;
    }

    public void writeHeader(int width, int height, int tileSize) throws IOException, UnsupportedOperationException {
        this.width = width;
        this.height = height;
        data = new int[width * height];
    }

    public void writeTile(int x, int y, int w, int h, Color[] color, float[] alpha) throws IOException {
        int[] tileData = ColorEncoder.encodeRGBE(color);
        for (int j = 0, index = 0, pixel = x + y * width; j < h; j++, pixel += width - w)
            for (int i = 0; i < w; i++, index++, pixel++)
                data[pixel] = tileData[index];
    }

    public void closeFile() throws IOException {
        OutputStream f = new BufferedOutputStream(new FileOutputStream(filename));
        f.write("#?RGBE\n".getBytes());
        f.write("FORMAT=32-bit_rle_rgbe\n\n".getBytes());
        f.write(("-Y " + height + " +X " + width + "\n").getBytes());
        for (int i = 0; i < data.length; i++) {
            int rgbe = data[i];
            f.write(rgbe >> 24);
            f.write(rgbe >> 16);
            f.write(rgbe >> 8);
            f.write(rgbe);
        }
        f.close();
    }
}