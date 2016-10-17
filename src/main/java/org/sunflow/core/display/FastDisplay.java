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
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.sunflow.SunflowAPI;
import org.sunflow.core.Display;
import org.sunflow.image.Color;
import org.sunflow.system.Timer;

@SuppressWarnings("serial")
public class FastDisplay extends JPanel implements Display {
    private JFrame frame;
    private BufferedImage image;
    private int[] pixels;
    private Timer t;
    private float seconds;
    private int frames;

    public FastDisplay() {
        image = null;
        frame = null;
        t = new Timer();
        frames = 0;
        seconds = 0;
    }

    public synchronized void imageBegin(int w, int h, int bucketSize) {
        if (frame != null && image != null && w == image.getWidth() && h == image.getHeight()) {
            // nothing to do
        } else {
            // allocate new framebuffer
            pixels = new int[w * h];
            image = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
            // prepare frame
            if (frame == null) {
                setPreferredSize(new Dimension(w, h));
                frame = new JFrame("Sunflow v" + SunflowAPI.VERSION);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
                            System.exit(0);
                    }
                });
                frame.setContentPane(this);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        }
        // start counter
        t.start();
    }

    public void imagePrepare(int x, int y, int w, int h, int id) {
    }

    public void imageUpdate(int x, int y, int w, int h, Color[] data, float[] alpha) {
        int iw = image.getWidth();
        int off = x + iw * y;
        iw -= w;
        for (int j = 0, index = 0; j < h; j++, off += iw)
            for (int i = 0; i < w; i++, index++, off++)
                pixels[off] = 0xFF000000 | data[index].toRGB();
    }

    public void imageFill(int x, int y, int w, int h, Color c, float alpha) {
        int iw = image.getWidth();
        int off = x + iw * y;
        iw -= w;
        int rgb = 0xFF000000 | c.toRGB();
        for (int j = 0, index = 0; j < h; j++, off += iw)
            for (int i = 0; i < w; i++, index++, off++)
                pixels[off] = rgb;
    }

    public synchronized void imageEnd() {
        // copy buffer
        image.setRGB(0, 0, image.getWidth(), image.getHeight(), pixels, 0, image.getWidth());
        repaint();
        // update stats
        t.end();
        seconds += t.seconds();
        frames++;
        if (seconds > 1) {
            // display average fps every second
            frame.setTitle(String.format("Sunflow v%s - %.2f fps", SunflowAPI.VERSION, frames / seconds));
            frames = 0;
            seconds = 0;
        }
    }

    @Override
    public synchronized void paint(Graphics g) {
        if (image == null)
            return;
        g.drawImage(image, 0, 0, null);
    }
}