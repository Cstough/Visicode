package VisiCode.Internals;

public class Camera {

    private Vector2 position;

    public Camera() {
        this.position = new Vector2();
    }

    public void Move(Vector2 move) {
        this.position.Add(move);
    }

    public void SetPosition(Vector2 pos) {
        this.position.x = pos.x;
        this.position.y = pos.y;
    }

    public Vector2 Position() {
        return this.position;
    }
}
