/**
 * Copyright (c) 2020, Javazilla Software
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package java.awt;

import com.javazilla.wava.client.WebGraphics;

public abstract class Component {

    public WebGraphics WEBG;

    int x;

    int y;

    int width = 100;

    int height = 100;

    public void setWebGraphics(WebGraphics WEBG) {
        this.WEBG = WEBG;
    }

    public void repaint(int x, int y, int width, int height) {
        repaint(0, x, y, width, height);
    }

    public void repaint() {
        repaint(0, 0, 0, width, height);
    }

    public void repaint(long tm, int x, int y, int width, int height) {
       // WEBG.translate(x, y);
        paint(WEBG);
    }

    public void paint(Graphics g) {
    }

}