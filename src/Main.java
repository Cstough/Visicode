import VisiCode.*;

import java.awt.*;

public class Main {

    public static void main(String[] args) {
        Game.Init();

        Game.Config().SetScreenSize(new Dimension(1200, 700));
        Game.Config().SetScreenScale(1);

        Game.AddGameObject(new TestObject());

        Game.Run();
    }
}
