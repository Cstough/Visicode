import VisiCode.DataStructures.PointQuadTree;
import VisiCode.Game;
import VisiCode.Internals.*;

import java.util.ArrayList;
import java.util.Random;

public class TestObjectManager extends GameObject {

    PointQuadTree<TestObject> qt;
    ArrayList<TestObject> objects;
    Random rand;

    public TestObjectManager() {
        qt = new PointQuadTree<TestObject>(Vector2.Dim2Vec2(Game.Config().GetScreenSize()).Mul(0.5f), Vector2.Dim2Vec2(Game.Config().GetScreenSize()).Mul(1), 2);
        objects = new ArrayList<TestObject>();
        rand = new Random();
        for(int i = 0; i < 50; i++) {
            TestObject o = new TestObject(this);
            float x = rand.nextInt(Game.Config().GetScreenSize().width/2) + Game.Config().GetScreenSize().width/4f;
            float y = rand.nextInt(Game.Config().GetScreenSize().height/2) + Game.Config().GetScreenSize().height/4f;
            o.position = new Vector2(x, y);
            objects.add(o);
        }
    }


    @Override
    public void Update(float deltaTime) {
        //first build a new quad tree
        qt = new PointQuadTree<TestObject>(Vector2.Dim2Vec2(Game.Config().GetScreenSize()).Mul(0.5f), Vector2.Dim2Vec2(Game.Config().GetScreenSize()).Mul(1), 2);
        for(TestObject o : objects) {
            qt.Insert(o);
        }
        //then update each object from objects
        for(TestObject o : objects) {
            o.Update(deltaTime);
        }
    }

    @Override
    public void Render(Graphics g) {
        qt.Render(g, Color.WHITE);
        for(TestObject o : objects) {
            o.Render(g);
        }
    }
}
