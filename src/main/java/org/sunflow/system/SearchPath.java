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
import java.io.IOException;
import java.util.LinkedList;

import org.sunflow.system.UI.Module;

public class SearchPath {
    private LinkedList<String> searchPath;
    private String type;

    public SearchPath(String type) {
        this.type = type;
        searchPath = new LinkedList<String>();
    }

    public void resetSearchPath() {
        searchPath.clear();
    }

    public void addSearchPath(String path) {
        File f = new File(path);
        if (f.exists() && f.isDirectory()) {
            try {
                path = f.getCanonicalPath();
                for (String prefix : searchPath)
                    if (prefix.equals(path))
                        return;
                UI.printInfo(Module.SYS, "Adding %s search path: \"%s\"", type, path);
                searchPath.add(path);
            } catch (IOException e) {
                UI.printError(Module.SYS, "Invalid %s search path specification: \"%s\" - %s", type, path, e.getMessage());
            }
        } else
            UI.printError(Module.SYS, "Invalid %s search path specification: \"%s\" - invalid directory", type, path);
    }

    public String resolvePath(String filename) {
        // account for relative naming schemes from 3rd party softwares
        if (filename.startsWith("//"))
            filename = filename.substring(2);
        UI.printDetailed(Module.SYS, "Resolving %s path \"%s\" ...", type, filename);
        File f = new File(filename);
        if (!f.isAbsolute()) {
            for (String prefix : searchPath) {
                UI.printDetailed(Module.SYS, "  * searching: \"%s\" ...", prefix);
                if (prefix.endsWith(File.separator) || filename.startsWith(File.separator))
                    f = new File(prefix + filename);
                else
                    f = new File(prefix + File.separator + filename);
                if (f.exists()) {
                    // suggested path exists - try it
                    return f.getAbsolutePath();
                }
            }
        }
        // file was not found in the search paths - return the filename itself
        return filename;
    }
}