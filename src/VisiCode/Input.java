package VisiCode;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * The Input class is the central point where the programmer accesses user input either by keyboard or mouse.
 */
public class Input {

    KeyScanner kScanner;
    MouseScanner mScanner;

    public Input() {
        kScanner = new KeyScanner();
        mScanner = new MouseScanner();
    }

    public boolean GetKeyDown(int keyCode) {
        return kScanner.GetKeyDown(keyCode);
    }

    public boolean GetLeftMouseDown() {
        return mScanner.LeftMouseDown;
    }

    public boolean GetRightMouseDown() {
        return mScanner.RightMouseDown;
    }

    public Point GetMousePosition() {

        Point dispPos = Game.GetDisplayPosition();
        Point mousePos = mScanner.GetMousePos();
        int scale = Game.Config().GetScreenScale();

        return new Point((mousePos.x - dispPos.x) / scale, (mousePos.y - dispPos.y) / scale);

    }

    /**
     * Private KeyBoard listener class for use by the Input class only!
     */
    private class KeyScanner extends KeyAdapter {

        public ArrayList<Integer> currKeys, prevKeys;

        public KeyScanner() {
            currKeys = new ArrayList<>();
            prevKeys = new ArrayList<>();
        }

        boolean GetKeyDown(int keycode) {
            if(currKeys.contains(keycode)) {
                return true;
            }
            return false;
        }

        boolean GetKeyUp(int keycode) {
            if(prevKeys.contains(keycode) && !currKeys.contains(keycode)) {
                return true;
            }
            return false;
        }

        boolean GetKeyTyped(int keycode) {
            if(!prevKeys.contains(keycode) && currKeys.contains(keycode)) {
                return true;
            }
            return false;
        }

        //Needed for implementation of KeyListener
        @Override
        public void keyPressed(KeyEvent e) {
            if(!currKeys.contains(e.getKeyCode()))
                currKeys.add(e.getKeyCode());
        }

        @Override
        public void keyReleased(KeyEvent e) {
            for(int i = 0; i < currKeys.size(); i++) {
                if(currKeys.get(i) == e.getKeyCode()) {
                    currKeys.remove(i);
                    return;
                }
            }
        }
    }

    /**
     * Private Mouse Listener class for use by the Input class only!
     */
    private class MouseScanner extends MouseAdapter{

        private boolean LeftMouseDown, RightMouseDown;

        public MouseScanner() {
            LeftMouseDown = false;
            RightMouseDown = false;
        }

        public Point GetMousePos() {
            return MouseInfo.getPointerInfo().getLocation();
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {
            if(e.getButton() == 1) {
                LeftMouseDown = true;
            } else if(e.getButton() == 3) {
                RightMouseDown = true;
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if(e.getButton() == 1) {
                LeftMouseDown = false;
            } else if(e.getButton() == 3) {
                RightMouseDown = false;
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
}
