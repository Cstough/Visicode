package VisiCode.Internals;

public abstract class GameObject extends Entity{

    public abstract void Update(float deltaTime);

    public abstract void Render(Graphics g);

}
