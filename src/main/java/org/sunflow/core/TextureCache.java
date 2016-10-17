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

package org.sunflow.core;

import java.util.HashMap;

import org.sunflow.system.UI;
import org.sunflow.system.UI.Module;

/**
 * Maintains a cache of all loaded texture maps. This is usefull if the same
 * texture might be used more than once in your scene.
 */
public final class TextureCache {
    private static HashMap<String, Texture> textures = new HashMap<String, Texture>();

    private TextureCache() {
    }

    /**
     * Gets a reference to the texture specified by the given filename. If the
     * texture has already been loaded the previous reference is returned,
     * otherwise, a new texture is created.
     * 
     * @param filename image file to load
     * @param isLinear is the texture gamma corrected?
     * @return texture object
     * @see Texture
     */
    public synchronized static Texture getTexture(String filename, boolean isLinear) {
        if (textures.containsKey(filename)) {
            UI.printInfo(Module.TEX, "Using cached copy for file \"%s\" ...", filename);
            return textures.get(filename);
        }
        UI.printInfo(Module.TEX, "Using file \"%s\" ...", filename);
        Texture t = new Texture(filename, isLinear);
        textures.put(filename, t);
        return t;
    }

    /**
     * Flush all textures from the cache, this will cause them to be reloaded
     * anew the next time they are accessed.
     */
    public synchronized static void flush() {
        UI.printInfo(Module.TEX, "Flushing texture cache");
        textures.clear();
    }
}