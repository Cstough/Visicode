import VisiCode.DataStructures.PointQuadTree;
import VisiCode.Game;
import VisiCode.Internals.*;
import VisiCode.Internals.Color;
import VisiCode.Internals.Graphics;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TestObject extends GameObject {

    PointQuadTree<Entity> qt;
    BoundingBox viewport;
    List<Entity> entitiesInView;

    public TestObject() {
        qt = new PointQuadTree<Entity>(Vector2.Dim2Vec2(Game.Config().GetScreenSize()).Mul(0.5f), Vector2.Dim2Vec2(Game.Config().GetScreenSize()).Mul(0.5f), 2);
        viewport = new BoundingBox(300, 300, 100, 100);
        entitiesInView = new ArrayList<>();
    }

    @Override
    public void Update(float deltaTime) {
        if(Game.Input().GetLeftMouseDown()) {
            Point p = Game.Input().GetMousePosition();
            Entity e = new Entity(new Vector2(p.x, p.y));
            qt.Insert(e);
        }
        entitiesInView = qt.Query(viewport);
    }

    @Override
    public void Render(Graphics g) {
        qt.Render(g, Color.WHITE);
        g.DrawRect(viewport.center, viewport.halfSize, Color.GREEN);

        for(Entity e : entitiesInView) {
            g.DrawPoint(e.position.x, e.position.y, Color.RED);
        }
    }
}
