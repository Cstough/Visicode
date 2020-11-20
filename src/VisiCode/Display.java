package VisiCode;

import VisiCode.Internals.Bitmap;
import VisiCode.Internals.Camera;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

public class Display extends Canvas {

    public Camera camera;

    int scale;
    Bitmap frameBuffer;
    BufferedImage displayImage;
    byte[] displayComponents;
    BufferStrategy bufferStrategy;
    Graphics graphics;
    VisiCode.Internals.Graphics graphicsObject;


    byte[] ClearColor;

    public Display(Dimension size, int scale) {

        this.camera = new Camera();
        this.addMouseListener(Game.Input().mScanner);
        this.scale = scale;
        this.setMinimumSize(new Dimension(size.width * scale, size.height * scale));
        this.setMaximumSize(new Dimension(size.width * scale, size.height * scale));
        this.setPreferredSize(new Dimension(size.width * scale, size.height * scale));

        frameBuffer = new Bitmap(size);
        displayImage = new BufferedImage(size.width, size.height, BufferedImage.TYPE_4BYTE_ABGR);
        displayComponents = ((DataBufferByte)(displayImage.getRaster().getDataBuffer())).getData();
    }

    void CreateGraphics() {
        createBufferStrategy(1);
        bufferStrategy = getBufferStrategy();
        graphics = bufferStrategy.getDrawGraphics();
        graphicsObject = new VisiCode.Internals.Graphics(frameBuffer);
    }

    void SwapBuffers() {
        try {
            frameBuffer.CopyToByteArray(displayComponents);
            graphics.drawImage(displayImage, 0, 0, frameBuffer.GetSize().width * this.scale, frameBuffer.GetSize().height * this.scale, null);
            bufferStrategy.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    void Clear() {
        frameBuffer.Clear(ClearColor);
    }
}
