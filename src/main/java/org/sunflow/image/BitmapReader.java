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

import java.io.IOException;

/**
 * This is a very simple interface, designed to handle loading of bitmap data.
 */
public interface BitmapReader {
    /**
     * Load the specified filename. This method should throw exception if it
     * encounters any errors. If the file is valid but its contents are not
     * (invalid header for example), a {@link BitmapFormatException} may be
     * thrown. It is an error for this method to return <code>null</code>.
     * 
     * @param filename image filename to load
     * @param isLinear if this is <code>true</code>, the bitmap is assumed to
     *            be already in linear space. This can be usefull when reading
     *            greyscale images for bump mapping for example. HDR formats can
     *            ignore this flag since they usually always store data in
     *            linear form.
     * @return a new {@link Bitmap} object
     */
    public Bitmap load(String filename, boolean isLinear) throws IOException, BitmapFormatException;

    /**
     * This exception can be used internally by bitmap readers to signal they
     * have encountered a valid file but which contains invalid content.
     */
    @SuppressWarnings("serial")
    public static final class BitmapFormatException extends Exception {
        public BitmapFormatException(String message) {
            super(message);
        }
    }
}