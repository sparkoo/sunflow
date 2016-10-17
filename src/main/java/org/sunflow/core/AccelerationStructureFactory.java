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

import org.sunflow.PluginRegistry;
import org.sunflow.system.UI;
import org.sunflow.system.UI.Module;

class AccelerationStructureFactory {
    static final AccelerationStructure create(String name, int n, boolean primitives) {
        if (name == null || name.equals("auto")) {
            if (primitives) {
                if (n > 20000000)
                    name = "uniformgrid";
                else if (n > 2000000)
                    name = "bih";
                else if (n > 2)
                    name = "kdtree";
                else
                    name = "null";
            } else {
                if (n > 2)
                    name = "bih";
                else
                    name = "null";
            }
        }
        AccelerationStructure accel = PluginRegistry.accelPlugins.createObject(name);
        if (accel == null) {
            UI.printWarning(Module.ACCEL, "Unrecognized intersection accelerator \"%s\" - using auto", name);
            return create(null, n, primitives);
        }
        return accel;
    }
}