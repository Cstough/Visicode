package VisiCode;

import VisiCode.Internals.*;
import VisiCode.Internals.Timer;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Game {

    private static Input input;
    private static Config config;
    private static Display display;

    private static final float FRAME_RATE = 30.0f;
    private static Timer timer = null;
    private static float deltaTime;
    public static JFrame frame;

    private static ArrayList<GameObject> objects;

    public static void Init() {
        input = new Input();
        config = new Config();
        objects = new ArrayList<>();
    }

    public static void Run() {

        display = new Display(config.GetScreenSize(), config.GetScreenScale());

        display.ClearColor = config.GetClearColor();

        frame = new JFrame();
        frame.add(display);
        frame.setSize(config.GetScreenSize());
        frame.setResizable(false);
        frame.pack();
        frame.setTitle(config.GetTitle());
        frame.setLocationRelativeTo(null);
        //frame.addMouseListener(input.mScanner);
        frame.addKeyListener(input.kScanner);
        frame.setAutoRequestFocus(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

        if(Game.Config().getCursorState()) {
            BufferedImage cursor = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
            Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursor, new Point(0, 0), "blank cursor");
            frame.getContentPane().setCursor(blankCursor);
        }

        display.CreateGraphics();

        //Game Loop
        timer = new Timer();
        timer.Init();
        float elapsedTime;
        float accumulator = 0f;
        float interval = 1f / FRAME_RATE;

        while(true) {
            elapsedTime = timer.getElapsedTime();
            accumulator += elapsedTime;
            while(accumulator >= interval) {
                deltaTime = interval;
                Update();
                accumulator -= interval;
            }
            Render();
            display.SwapBuffers();
            sync();
        }
    }

    private static void Update() {
        for(GameObject Object : objects) {
            Object.Update(deltaTime);
        }
    }

    private static void Render() {

        display.Clear();

        for(GameObject Object : objects) {
            Object.Render(display.graphicsObject);
        }
    }

    public static void AddGameObject(GameObject object) {
        Game.objects.add(object);
    }

    public static void RemoveGameObject(GameObject object) {
        Game.objects.remove(object);
    }

    public static Input Input() {
        return input;
    }

    public static Config Config() {
        return config;
    }

    public static Display Display() {return display;}

    private static void sync() {
        float loopSlot = 1f / FRAME_RATE;
        double endTime = timer.getLastLoopTime() + loopSlot;
        while(timer.getTime() < endTime) {
            try {
                Thread.sleep(1);
            } catch(InterruptedException e) {}
        }
    }

    public static Point GetWindowPosition() {
        return frame.getLocationOnScreen();
    }

    public static Point GetDisplayPosition() {
        return display.getLocationOnScreen();
    }
}
