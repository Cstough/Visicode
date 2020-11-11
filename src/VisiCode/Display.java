package VisiCode;

import VisiCode.Internals.Bitmap;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

public class Display extends Canvas {

    int scale;
    Bitmap frameBuffer;
    BufferedImage displayImage;
    byte[] displayComponents;
    BufferStrategy bufferStrategy;
    Graphics graphics;
    VisiCode.Internals.Graphics graphicsObject;


    byte[] ClearColor;

    public Display(Dimension size, int scale) {

        this.addMouseListener(Game.Input().mScanner);
        this.scale = scale;
        this.setMinimumSize(new Dimension(size.width * scale, size.height * scale));
        this.setMaximumSize(new Dimension(size.width * scale, size.height * scale));
        this.setPreferredSize(new Dimension(size.width * scale, size.height * scale));

        frameBuffer = new Bitmap(size);
        displayImage = new BufferedImage(size.width, size.height, BufferedImage.TYPE_4BYTE_ABGR);
        displayComponents = ((DataBufferByte)(displayImage.getRaster().getDataBuffer())).getData();
    }

    public void CreateGraphics() {
        createBufferStrategy(1);
        bufferStrategy = getBufferStrategy();
        graphics = bufferStrategy.getDrawGraphics();
        graphicsObject = new VisiCode.Internals.Graphics(frameBuffer);
    }

    public void SwapBuffers() {
        try {
            frameBuffer.CopyToByteArray(displayComponents);
            graphics.drawImage(displayImage, 0, 0, frameBuffer.GetSize().width * this.scale, frameBuffer.GetSize().height * this.scale, null);
            bufferStrategy.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void Clear() {
        frameBuffer.Clear(ClearColor);
    }
}
