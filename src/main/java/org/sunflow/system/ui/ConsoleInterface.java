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

package org.sunflow.system.ui;

import org.sunflow.system.UI;
import org.sunflow.system.UserInterface;
import org.sunflow.system.UI.Module;
import org.sunflow.system.UI.PrintLevel;

/**
 * Basic console implementation of a user interface.
 */
public class ConsoleInterface implements UserInterface {
    private int min;
    private int max;
    private float invP;
    private String task;
    private int lastP;

    public ConsoleInterface() {
    }

    public void print(Module m, PrintLevel level, String s) {
        System.err.println(UI.formatOutput(m, level, s));
    }

    public void taskStart(String s, int min, int max) {
        task = s;
        this.min = min;
        this.max = max;
        lastP = -1;
        invP = 100.0f / (max - min);
    }

    public void taskUpdate(int current) {
        int p = (min == max) ? 0 : (int) ((current - min) * invP);
        if (p != lastP)
            System.err.print(task + " [" + (lastP = p) + "%]\r");
    }

    public void taskStop() {
        System.err.print("                                                                      \r");
    }
}