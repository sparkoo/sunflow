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

package org.sunflow.core.display;

import java.io.IOException;

import org.sunflow.PluginRegistry;
import org.sunflow.core.Display;
import org.sunflow.image.BitmapWriter;
import org.sunflow.image.Color;
import org.sunflow.system.FileUtils;
import org.sunflow.system.UI;
import org.sunflow.system.UI.Module;

public class FileDisplay implements Display {
    private BitmapWriter writer;
    private String filename;

    public FileDisplay(boolean saveImage) {
        this(saveImage ? "output.png" : ".none");
    }

    public FileDisplay(String filename) {
        this.filename = filename == null ? "output.png" : filename;
        String extension = FileUtils.getExtension(filename);
        writer = PluginRegistry.bitmapWriterPlugins.createObject(extension);
    }

    public void imageBegin(int w, int h, int bucketSize) {
        if (writer == null)
            return;
        try {
            writer.openFile(filename);
            writer.writeHeader(w, h, bucketSize);
        } catch (IOException e) {
            UI.printError(Module.IMG, "I/O error occured while preparing image for display: %s", e.getMessage());
        }
    }

    public void imagePrepare(int x, int y, int w, int h, int id) {
        // does nothing for files
    }

    public void imageUpdate(int x, int y, int w, int h, Color[] data, float[] alpha) {
        if (writer == null)
            return;
        try {
            writer.writeTile(x, y, w, h, data, alpha);
        } catch (IOException e) {
            UI.printError(Module.IMG, "I/O error occured while writing image tile [(%d,%d) %dx%d] image for display: %s", x, y, w, h, e.getMessage());
        }
    }

    public void imageFill(int x, int y, int w, int h, Color c, float alpha) {
        if (writer == null)
            return;
        Color[] colorTile = new Color[w * h];
        float[] alphaTile = new float[w * h];
        for (int i = 0; i < colorTile.length; i++) {
            colorTile[i] = c;
            alphaTile[i] = alpha;
        }
        imageUpdate(x, y, w, h, colorTile, alphaTile);
    }

    public void imageEnd() {
        if (writer == null)
            return;
        try {
            writer.closeFile();
        } catch (IOException e) {
            UI.printError(Module.IMG, "I/O error occured while closing the display: %s", e.getMessage());
        }
    }
}