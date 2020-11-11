import VisiCode.Game;
import VisiCode.Internals.Bitmap;
import VisiCode.Internals.Color;
import VisiCode.Internals.GameObject;
import VisiCode.Internals.Graphics;

import java.awt.*;

public class TestObject extends GameObject {

    Bitmap sprite;

    float x, y;

    public TestObject(int x, int y) {
        this.x = x;
        this.y = y;
        sprite = Bitmap.LoadSprite("src/test.png");
    }

    @Override
    public void Update(float deltaTime) {
        Point p = Game.Input().GetMousePosition();
        x = p.x;
        y = p.y;
    }

    @Override
    public void Render(Graphics g) {
        g.DrawSprite(sprite, x, y);
    }
}
