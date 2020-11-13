package VisiCode.Internals;

public class Entity {

    public Vector2 position;
    public BoundingBox boundingBox;

    public Entity() {
        this.position = new Vector2();
        this.boundingBox = new BoundingBox();
    }

    public Entity(Vector2 position) {
        this.position = position;
    }
}
