import VisiCode.*;

import java.awt.*;

public class Main {

    public static void main(String[] args) {
        Game.Init();

        Game.Config().SetScreenSize(new Dimension(300, 200));
        Game.Config().SetScreenScale(4);

        Game.AddGameObject(new TestObject(0, 0));

        Game.Run();
    }
}
