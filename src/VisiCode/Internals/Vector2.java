package VisiCode.Internals;

public class Vector2 {
    public float x, y;

    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2() {
        this.x = 0f;
        this.y = 0f;
    }

    public void Normalize() {
        float l = (float)Math.sqrt(x*x + y*y);
        x /= l;
        y /= l;
    }

    public void Add(Vector2 v) {
        x += v.x;
        y += v.y;
    }

    public static Vector2 Add(Vector2 v1, Vector2 v2) {
        return new Vector2(v1.x + v2.x, v1.y + v2.y);
    }

    public Vector2 Mul(float m) {
        return new Vector2(x * m, y * m);
    }

    public static Vector2 Mul(Vector2 v, float f) {
        return new Vector2(v.x * f, v.y * f);
    }

    public void Scale(float m) {
        x *= m;
        y *= m;
    }
}
