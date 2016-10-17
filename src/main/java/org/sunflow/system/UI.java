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

import java.util.Locale;

import org.sunflow.system.ui.ConsoleInterface;
import org.sunflow.system.ui.SilentInterface;

/**
 * Static singleton interface to a UserInterface object. This is set to a text
 * console by default.
 */
public final class UI {
    private static UserInterface ui = new ConsoleInterface();
    private static boolean canceled = false;
    private static int verbosity = 3;

    public enum Module {
        API, GEOM, HAIR, ACCEL, BCKT, IPR, LIGHT, GUI, SCENE, BENCH, TEX, IMG, DISP, QMC, SYS, USER, CAM,
    }

    public enum PrintLevel {
        ERROR, WARN, INFO, DETAIL
    }

    private UI() {
    }

    /**
     * Sets the active user interface implementation. Passing <code>null</code>
     * silences printing completely.
     * 
     * @param ui object to recieve all user interface calls
     */
    public final static void set(UserInterface ui) {
        if (ui == null)
            ui = new SilentInterface();
        UI.ui = ui;
    }

    public final static void verbosity(int verbosity) {
        UI.verbosity = verbosity;
    }

    public final static String formatOutput(Module m, PrintLevel level, String s) {
        return String.format("%-5s  %-6s: %s", m.name(), level.name().toLowerCase(Locale.ENGLISH), s);
    }

    public final static synchronized void printDetailed(Module m, String s, Object... args) {
        if (verbosity > 3)
            ui.print(m, PrintLevel.DETAIL, String.format(s, args));
    }

    public final static synchronized void printInfo(Module m, String s, Object... args) {
        if (verbosity > 2)
            ui.print(m, PrintLevel.INFO, String.format(s, args));
    }

    public final static synchronized void printWarning(Module m, String s, Object... args) {
        if (verbosity > 1)
            ui.print(m, PrintLevel.WARN, String.format(s, args));
    }

    public final static synchronized void printError(Module m, String s, Object... args) {
        if (verbosity > 0)
            ui.print(m, PrintLevel.ERROR, String.format(s, args));
    }

    public final static synchronized void taskStart(String s, int min, int max) {
        ui.taskStart(s, min, max);
    }

    public final static synchronized void taskUpdate(int current) {
        ui.taskUpdate(current);
    }

    public final static synchronized void taskStop() {
        ui.taskStop();
        // reset canceled status
        // this assume the parent application will deal with it immediately
        canceled = false;
    }

    /**
     * Cancel the currently active task. This forces the application to abort as
     * soon as possible.
     */
    public final static synchronized void taskCancel() {
        printInfo(Module.GUI, "Abort requested by the user ...");
        canceled = true;
    }

    /**
     * Check to see if the current task should be aborted.
     * 
     * @return <code>true</code> if the current task should be stopped,
     *         <code>false</code> otherwise
     */
    public final static synchronized boolean taskCanceled() {
        if (canceled)
            printInfo(Module.GUI, "Abort request noticed by the current task");
        return canceled;
    }
}