package VisiCode.Internals;

public class BoundingBox {

    public Vector2 center, halfSize;

    public BoundingBox() {
        this.center = new Vector2();
        this.halfSize = new Vector2();
    }

    public BoundingBox(Vector2 center, Vector2 halfSize) {
        this.center = center;
        this.halfSize = halfSize;
    }

    public BoundingBox(float cx, float cy, float hw, float hh) {
        this.center = new Vector2(cx, cy);
        this.halfSize = new Vector2(hw, hh);
    }

    public boolean ContainsPoint(Vector2 p) {
        return p.x >= this.center.x - this.halfSize.x && p.x <= this.center.x + this.halfSize.x &&
                p.y >= this.center.y - this.halfSize.y && p.y <= this.center.y + this.halfSize.y;
    }

    public boolean Intersects(BoundingBox b) {
        return this.center.x - this.halfSize.x <= b.center.x + b.halfSize.x &&
                this.center.x + this.halfSize.x >= b.center.x - b.halfSize.x &&
                this.center.y - this.halfSize.y <= b.center.y + b.halfSize.y &&
                this.center.y + this.halfSize.y >= b.center.y - b.halfSize.y;
    }
}
