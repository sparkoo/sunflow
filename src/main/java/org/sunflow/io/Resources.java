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

package org.sunflow.io;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Resources {
    private static Resources uniqueInstance;

    private Map <Object,Object> availableResources;

    private Resources() {
        availableResources = new ConcurrentHashMap <Object,Object> ();
    }

    public static synchronized Resources getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Resources();
        }
        return uniqueInstance;
    }

    public Object get(Object key) {
        return availableResources.get(key);
    }

    public void store(Object key, Object value) {
        availableResources.put(key,value);
    }

    public boolean contains(Object key) {
        return availableResources.containsKey(key);
    }
}
