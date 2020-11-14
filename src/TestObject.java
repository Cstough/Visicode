import VisiCode.DataStructures.PointQuadTree;
import VisiCode.Game;
import VisiCode.Internals.*;
import VisiCode.Internals.Color;
import VisiCode.Internals.Graphics;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/*
This demonstrates one of the shortcomings of using a QuadTree with only positions rather than BoundingBoxes. Since the PointQuadTree only queries the center points that are within our query BoundingBox, we don't see every collision
unless we make an additional BoundingBox that is larger than our to check further out. This is a downside since we would need an extremely large checking BoundingBox to collide with massive object, but doing so would negate the
benefits of the QuadTree in the first place.
 */
public class TestObject extends GameObject {

    private TestObjectManager parent;
    private boolean colliding;
    private BoundingBox checkingBox;

    public TestObject(TestObjectManager parent) {
        this.parent = parent;
        this.colliding = false;
        this.boundingBox = new BoundingBox(this.position, new Vector2(25, 25));
        this.checkingBox = new BoundingBox(this.position, new Vector2(50, 50));
    }

    @Override
    public void Update(float deltaTime) {
        this.position.Add(new Vector2(parent.rand.nextInt(5) - 2, parent.rand.nextInt(5) - 2));
        this.boundingBox.center = this.position;
        //this.checkingBox.center = this.position;
        List<TestObject> collisions = parent.qt.Query(this.checkingBox);
        this.colliding = false;

        for(TestObject o : collisions) {
            if(o != this && o.boundingBox.Intersects(this.boundingBox)) {
                this.colliding = true;
            }
        }
    }

    @Override
    public void Render(Graphics g) {
        g.DrawRect(this.boundingBox.center, this.boundingBox.halfSize, (this.colliding) ? Color.RED : Color.GREEN);
    }
}
