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

package org.sunflow.system;

import java.io.File;
import java.util.Locale;

public final class FileUtils {
    /**
     * Extract the file extension from the specified filename.
     * 
     * @param filename filename to get the extension of
     * @return a string representing the file extension, or <code>null</code>
     *         if the filename doesn't have any extension, or is not a file
     */
    public static final String getExtension(String filename) {
        if (filename == null)
            return null;
        File f = new File(filename);
        if (f.isDirectory())
            return null;
        String name = new File(filename).getName();
        int idx = name.lastIndexOf('.');
        return idx == -1 ? null : name.substring(idx + 1).toLowerCase(Locale.ENGLISH);
    }
}