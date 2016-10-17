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

public class TGABitmapWriter implements BitmapWriter {
    private String filename;
    private int width, height;
    private byte[] data;

    public void configure(String option, String value) {
    }

    public void openFile(String filename) throws IOException {
        this.filename = filename;
    }

    public void writeHeader(int width, int height, int tileSize) throws IOException, UnsupportedOperationException {
        this.width = width;
        this.height = height;
        data = new byte[width * height * 4]; // RGBA8
    }

    public void writeTile(int x, int y, int w, int h, Color[] color, float[] alpha) throws IOException {
        color = ColorEncoder.unlinearize(color); // gamma correction
        byte[] tileData = ColorEncoder.quantizeRGBA8(color, alpha);
        for (int j = 0, index = 0; j < h; j++) {
            int imageIndex = 4 * (x + (height - 1 - (y + j)) * width);
            for (int i = 0; i < w; i++, index += 4, imageIndex += 4) {
                // swap bytes around so buffer is in native BGRA order
                data[imageIndex + 0] = tileData[index + 2];
                data[imageIndex + 1] = tileData[index + 1];
                data[imageIndex + 2] = tileData[index + 0];
                data[imageIndex + 3] = tileData[index + 3];
            }
        }
    }

    public void closeFile() throws IOException {
        // actually write the file from here
        OutputStream f = new BufferedOutputStream(new FileOutputStream(filename));
        // no id, no colormap, uncompressed 32bpp RGBA
        byte[] tgaHeader = { 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        f.write(tgaHeader);
        // then the size info
        f.write(width & 0xFF);
        f.write((width >> 8) & 0xFF);
        f.write(height & 0xFF);
        f.write((height >> 8) & 0xFF);
        // bitsperpixel and filler
        f.write(32);
        f.write(0);
        f.write(data); // write image data bytes (already in BGRA order)
        f.close();
    }
}