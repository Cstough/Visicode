package VisiCode.Internals;

/**
 * This will serve as the Object passed into each Render function that allows GameObjects to be rendered
 */
public class Graphics {

    private Bitmap frameBuffer;

    public Graphics(Bitmap frameBuffer) {
        this.frameBuffer = frameBuffer;
    }

    public void DrawSprite(Bitmap sprite, float px, float py) {

        int lx = Math.round(px);
        int ly = Math.round(py);

        byte[] comps = sprite.GetComponents();
        for(int y = 0; y < sprite.GetSize().height; y++) {
            for(int x = 0; x < sprite.GetSize().width; x++) {
                int screenX = lx + x;
                int screenY = ly + y;
                if(screenX >= 0 && screenX < frameBuffer.GetSize().width && screenY >= 0 && screenY < frameBuffer.GetSize().height) {
                    frameBuffer.DrawPixel(screenX, screenY, new byte[] {
                            comps[(y * sprite.GetSize().width + x) * 4 + 0],
                            comps[(y * sprite.GetSize().width + x) * 4 + 1],
                            comps[(y * sprite.GetSize().width + x) * 4 + 2],
                            comps[(y * sprite.GetSize().width + x) * 4 + 3]});
                }
            }
        }
    }

    public void DrawPoint(float x, float y, Color c) {
        frameBuffer.DrawPixel((int)x, (int)y, c.getBytes());
    }

    public void DrawRect(float x1, float y1, float x2, float y2, Color c) {
        byte[] comps = c.getBytes();
        for(int i = 0; i <= x2 - x1; i++) {
            frameBuffer.DrawPixel((int)x1 + i, (int)y1, comps);
            frameBuffer.DrawPixel((int)x2 - i, (int)y2, comps);
        }
        for(int i = 1; i < (y2 - y1); i++) {
            frameBuffer.DrawPixel((int)x1, (int)y1+i, comps);
            frameBuffer.DrawPixel((int)x2, (int)y2-i, comps);
        }
    }

    public void DrawRect(Vector2 center, Vector2 halfSize, Color c) {
        byte[] comps = c.getBytes();

        int startX = (int)(center.x - halfSize.x);
        int endX = (int)(center.x + halfSize.x) - 1;
        int startY = (int)(center.y - halfSize.y);
        int endY = (int)(center.y + halfSize.y) - 1;

        for(int i = 0; i < halfSize.x * 2; i++) {
            frameBuffer.DrawPixel(startX + i, startY, comps);
            frameBuffer.DrawPixel(endX - i, endY, comps);
        }
        for(int i = 0; i < halfSize.y * 2; i++) {
            frameBuffer.DrawPixel(startX, startY + i, comps);
            frameBuffer.DrawPixel(endX, endY - i, comps);
        }
    }
}
