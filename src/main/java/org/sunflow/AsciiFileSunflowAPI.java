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

package org.sunflow;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Locale;

import org.sunflow.core.ParameterList.InterpolationType;
import org.sunflow.core.parser.SCAbstractParser.Keyword;
import org.sunflow.math.Matrix4;

class AsciiFileSunflowAPI extends FileSunflowAPI {
    private OutputStream stream;

    AsciiFileSunflowAPI(String filename) throws IOException {
        stream = new BufferedOutputStream(new FileOutputStream(filename));
    }

    @Override
    protected void writeBoolean(boolean value) {
        if (value)
            writeString("true");
        else
            writeString("false");
    }

    @Override
    protected void writeFloat(float value) {
        writeString(String.format("%s", value));
    }

    @Override
    protected void writeInt(int value) {
        writeString(String.format("%d", value));
    }

    @Override
    protected void writeInterpolationType(InterpolationType interp) {
        writeString(String.format("%s", interp.toString().toLowerCase(Locale.ENGLISH)));
    }

    @Override
    protected void writeKeyword(Keyword keyword) {
        writeString(String.format("%s", keyword.toString().toLowerCase(Locale.ENGLISH).replace("_array", "[]")));
    }

    @Override
    protected void writeMatrix(Matrix4 value) {
        writeString("row");
        for (float f : value.asRowMajor())
            writeFloat(f);
    }

    @Override
    protected void writeNewline(int indentNext) {
        try {
            stream.write('\n');
            for (int i = 0; i < indentNext; i++)
                stream.write('\t');
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    protected void writeString(String string) {
        try {
            // check if we need to write string with quotes
            if (string.contains(" ") && !string.contains("<code>"))
                stream.write(String.format("\"%s\"", string).getBytes());
            else
                stream.write(string.getBytes());
            stream.write(' ');
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    protected void writeVerbatimString(String string) {
        writeString(String.format("<code>%s\n</code> ", string));
    }

    @Override
    public void close() {
        try {
            stream.close();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}