import VisiCode.*;

import java.awt.*;

public class Main {

    public static void main(String[] args) {
        Game.Init();

        Game.Config().SetScreenSize(new Dimension(600, 400));
        Game.Config().SetScreenScale(2);

        Game.AddGameObject(new TestObject());

        Game.Run();
    }
}
