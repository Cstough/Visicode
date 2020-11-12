package VisiCode.Internals;

public class Entity {

    public Vector2 position;
    protected BoundingBox boundingBox;

    public Entity() {
        this.position = new Vector2();
        this.boundingBox = new BoundingBox();
    }

    public Entity(Vector2 position) {
        this.position = position;
    }

    public Vector2 getPosition() {
        return position;
    }

    public BoundingBox getBoundingBox() {
        return boundingBox;
    }
}
