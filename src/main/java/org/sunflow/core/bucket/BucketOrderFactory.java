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

package org.sunflow.core.bucket;

import org.sunflow.PluginRegistry;
import org.sunflow.core.BucketOrder;
import org.sunflow.system.UI;
import org.sunflow.system.UI.Module;

public class BucketOrderFactory {
    public static BucketOrder create(String order) {
        boolean flip = false;
        if (order.startsWith("inverse") || order.startsWith("invert") || order.startsWith("reverse")) {
            String[] tokens = order.split("\\s+");
            if (tokens.length == 2) {
                order = tokens[1];
                flip = true;
            }
        }
        BucketOrder o = PluginRegistry.bucketOrderPlugins.createObject(order);
        if (o == null) {
            UI.printWarning(Module.BCKT, "Unrecognized bucket ordering: \"%s\" - using hilbert", order);
            return create("hilbert");
        }
        return flip ? new InvertedBucketOrder(o) : o;
    }
}