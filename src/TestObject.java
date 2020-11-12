import VisiCode.DataStructures.PointQuadTree;
import VisiCode.Game;
import VisiCode.Internals.*;
import VisiCode.Internals.Color;
import VisiCode.Internals.Graphics;

import java.awt.*;

public class TestObject extends GameObject {

    PointQuadTree<Entity> qt;

    public TestObject() {
        qt = new PointQuadTree<Entity>(new Vector2(300, 200), new Vector2(300, 200), 2);
    }

    @Override
    public void Update(float deltaTime) {
        if(Game.Input().GetLeftMouseDown()) {
            Point p = Game.Input().GetMousePosition();
            Entity e = new Entity(new Vector2(p.x, p.y));
            qt.Insert(e);
        }
    }

    @Override
    public void Render(Graphics g) {
        qt.Render(g, Color.WHITE);
    }
}
