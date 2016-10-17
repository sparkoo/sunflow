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

package org.sunflow.core.display;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import org.sunflow.SunflowAPI;
import org.sunflow.core.Display;
import org.sunflow.image.Color;
import org.sunflow.system.ImagePanel;

public class FrameDisplay implements Display {
    private String filename;
    private RenderFrame frame;

    public FrameDisplay() {
        this(null);
    }

    public FrameDisplay(String filename) {
        this.filename = filename;
        frame = null;
    }

    public void imageBegin(int w, int h, int bucketSize) {
        if (frame == null) {
            frame = new RenderFrame();
            frame.imagePanel.imageBegin(w, h, bucketSize);
            Dimension screenRes = Toolkit.getDefaultToolkit().getScreenSize();
            boolean needFit = false;
            if (w >= (screenRes.getWidth() - 200) || h >= (screenRes.getHeight() - 200)) {
                frame.imagePanel.setPreferredSize(new Dimension((int) screenRes.getWidth() - 200, (int) screenRes.getHeight() - 200));
                needFit = true;
            } else
                frame.imagePanel.setPreferredSize(new Dimension(w, h));
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            if (needFit)
                frame.imagePanel.fit();
        } else
            frame.imagePanel.imageBegin(w, h, bucketSize);
    }

    public void imagePrepare(int x, int y, int w, int h, int id) {
        frame.imagePanel.imagePrepare(x, y, w, h, id);
    }

    public void imageUpdate(int x, int y, int w, int h, Color[] data, float[] alpha) {
        frame.imagePanel.imageUpdate(x, y, w, h, data, alpha);
    }

    public void imageFill(int x, int y, int w, int h, Color c, float alpha) {
        frame.imagePanel.imageFill(x, y, w, h, c, alpha);
    }

    public void imageEnd() {
        frame.imagePanel.imageEnd();
        if (filename != null)
            frame.imagePanel.save(filename);
    }

    @SuppressWarnings("serial")
    private static class RenderFrame extends JFrame {
        ImagePanel imagePanel;

        RenderFrame() {
            super("Sunflow v" + SunflowAPI.VERSION);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
                        System.exit(0);
                }
            });
            imagePanel = new ImagePanel();
            setContentPane(imagePanel);
            pack();
        }
    }
}