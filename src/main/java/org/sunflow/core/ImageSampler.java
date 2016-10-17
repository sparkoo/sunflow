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

/**
 * This interface represents an image sampling algorithm capable of rendering
 * the entire image. Implementations are responsible for anti-aliasing and
 * filtering.
 */
public interface ImageSampler {
    /**
     * Prepare the sampler for rendering an image of w x h pixels
     * 
     * @param w width of the image
     * @param h height of the image
     */
    public boolean prepare(Options options, Scene scene, int w, int h);

    /**
     * Render the image to the specified display. The sampler can assume the
     * display has been opened and that it will be closed after the method
     * returns.
     * 
     * @param display Display driver to send image data to
     */
    public void render(Display display);
}