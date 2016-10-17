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

package org.sunflow.image.readers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.sunflow.image.Bitmap;
import org.sunflow.image.BitmapReader;
import org.sunflow.image.Color;
import org.sunflow.image.formats.BitmapRGBA8;

public class PNGBitmapReader implements BitmapReader {
    public Bitmap load(String filename, boolean isLinear) throws IOException, BitmapFormatException {
        // regular image, load using Java api
        BufferedImage bi = ImageIO.read(new File(filename));
        int width = bi.getWidth();
        int height = bi.getHeight();
        byte[] pixels = new byte[4 * width * height];
        for (int y = 0, index = 0; y < height; y++) {
            for (int x = 0; x < width; x++, index += 4) {
                int argb = bi.getRGB(x, height - 1 - y);
                pixels[index + 0] = (byte) (argb >> 16);
                pixels[index + 1] = (byte) (argb >> 8);
                pixels[index + 2] = (byte) argb;
                pixels[index + 3] = (byte) (argb >> 24);
            }
        }
        if (!isLinear) {
            for (int index = 0; index < pixels.length; index += 4) {
                pixels[index + 0] = Color.NATIVE_SPACE.rgbToLinear(pixels[index + 0]);
                pixels[index + 1] = Color.NATIVE_SPACE.rgbToLinear(pixels[index + 1]);
                pixels[index + 2] = Color.NATIVE_SPACE.rgbToLinear(pixels[index + 2]);
            }
        }
        return new BitmapRGBA8(width, height, pixels);
    }
}