package VisiCode.Internals;

public class BoundingBox {

    private Vector2 center, halfSize;

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

}
