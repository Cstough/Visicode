package VisiCode.Internals;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Bitmap {

    private int width, height;
    private byte[] components; //ABGR

    public Bitmap() {
        this.width = 0;
        this.height = 0;
        this.components = new byte[0];
    }

    public Bitmap(Dimension size) {
        this.width = size.width;
        this.height = size.height;
        this.components = new byte[this.width * this.height * 4];
    }

    public Dimension GetSize() {
        return new Dimension(this.width, this.height);
    }
    public byte[] GetComponents() {
        return components;
    }

    /**
     * A simple clear function that takes an ABGR color and encodes it into an ABGR framebuffer
     * @param shade
     */
    public void Clear(byte[] shade) {
        for(int i = 0; i < this.components.length / 4; i++) {
            this.components[i * 4    ] = shade[0];
            this.components[i * 4 + 1] = shade[3];
            this.components[i * 4 + 2] = shade[2];
            this.components[i * 4 + 3] = shade[1];
        }
    }

    public void DrawPixel(int x, int y, byte[] Color) {
        if(x < 0 || x > this.width - 1 || y < 0 || y > this.height - 1) {
            return;
        }
        int index = ((y * this.width) + x) * 4;
        this.components[index + 1] = Color[1];
        this.components[index + 2] = Color[2];
        this.components[index + 3] = Color[3];
        if(Color[0] == 0x00) {
            return;
        } else {
            this.components[index] = Color[0];
        }
    }

    public void CopyToByteArray(byte[] dest) {
        for (int i = 0; i < this.width * this.height; i++) {
            dest[i * 4 + 0] = this.components[i * 4 + 0];
            dest[i * 4 + 1] = this.components[i * 4 + 3];
            dest[i * 4 + 2] = this.components[i * 4 + 2];
            dest[i * 4 + 3] = this.components[i * 4 + 1];
        }
    }

    public static Bitmap LoadSprite(String filename) {
        BufferedImage image = null;
        Bitmap spr = null;
        try {
            image = ImageIO.read(new File(filename));
        } catch(IOException e) {
            //image = ImageIO.read(new File("missingTexture"));
        }

        spr = new Bitmap(image.getData().getBounds().getSize());

        for(int i = 0; i < spr.height; i++) {
            for(int j = 0; j < spr.width; j++) {
                int colorint = image.getRGB(j, i);
                byte[] bytecolor = new byte[] {
                        (byte)(colorint >> 24),
                        (byte)(colorint >> 16),
                        (byte)(colorint >> 8),
                        (byte)(colorint >> 0)
                };
                spr.DrawPixel(j, i, bytecolor);
            }
        }

        return spr;
    }
}
