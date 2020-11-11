package VisiCode;

import java.awt.*;

public class Config {

    private Dimension screenSize;
    private String title;
    private int screenScale;
    private byte[] clearColor;
    private boolean hideCursor;

    public Config() {
        this.screenSize = new Dimension(200, 150);
        this.title = "New Game!";
        this.screenScale = 4;
        clearColor = new byte[] {(byte)255, (byte)0, (byte)0, (byte)0}; //ABGR
        hideCursor = false;
    }

    public void SetTitle(String title) {
        this.title = title;
    }

    public String GetTitle() {
        return this.title;
    }

    public void SetScreenSize(Dimension size) {
        this.screenSize = size;
    }

    public Dimension GetScreenSize() {
        return this.screenSize;
    }

    public void SetScreenScale(int scale) {
        this.screenScale = scale;
    }

    public int GetScreenScale() {
        return this.screenScale;
    }

    public void SetClearColor(byte[] color) {
        clearColor = color;
    }

    public byte[] GetClearColor() {
        return clearColor;
    }

    public void hideCursor(boolean hide) {
        this.hideCursor = hide;
    }

    public boolean getCursorState() {
        return hideCursor;
    }
}
