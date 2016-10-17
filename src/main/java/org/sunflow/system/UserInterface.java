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

import org.sunflow.system.UI.Module;
import org.sunflow.system.UI.PrintLevel;

public interface UserInterface {
    /**
     * Displays some information to the user from the specified module with the
     * specified print level. A user interface is free to show or ignore any
     * message. Level filtering is done in the core and shouldn't be
     * re-implemented by the user interface. All messages will be short enough
     * to fit on one line.
     * 
     * @param m module the message came from
     * @param level seriousness of the message
     * @param s string to display
     */
    void print(Module m, PrintLevel level, String s);

    /**
     * Prepare a progress bar representing a lengthy task. The actual progress
     * is first shown by the call to update and closed when update is closed
     * with the max value. It is currently not possible to nest calls to
     * setTask, so only one task needs to be tracked at a time.
     * 
     * @param s desriptive string
     * @param min minimum value of the task
     * @param max maximum value of the task
     */
    void taskStart(String s, int min, int max);

    /**
     * Updates the current progress bar to a value between the current min and
     * max. When min or max are passed the progressed bar is shown or hidden
     * respectively.
     * 
     * @param current current value of the task in progress.
     */
    void taskUpdate(int current);

    /**
     * Closes the current progress bar to indicate the task is over
     */
    void taskStop();
}