package components;

import javax.swing.*;

public class Button extends ImageIcon {
    protected final int startX, startY, endX, endY;

    public Button(int startX, int startY, int endX, int endY, String path) {
        super(path);
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
    }

    public boolean checkClicked(int x, int y) {
        return (x >= startX && x <= endX) && (y >= startY && y <= endY);
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    public int getEndX() {
        return endX;
    }

    public int getEndY() {
        return endY;
    }
}
