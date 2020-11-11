package VisiCode.Internals;

public class Entity {
    protected Vector2 position;
    protected BoundingBox boundingBox;


    public Vector2 getPosition() {
        return position;
    }

    public BoundingBox getBoundingBox() {
        return boundingBox;
    }
}
