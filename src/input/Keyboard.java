package input;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallback;

public class Keyboard extends GLFWKeyCallback {
    private static boolean[] keys = new boolean[GLFW.GLFW_KEY_LAST];
    private static boolean[] keysPressed = new boolean[keys.length];

    @Override
    public void invoke(long window, int key, int scancode, int action, int mods) {
        if (key < 0 || key >= keys.length) {
            return;
        }

        if (action == GLFW.GLFW_PRESS) {
            if (!keys[key]) {
                keysPressed[key] = true;
            }
            keys[key] = true;
        } else if (action == GLFW.GLFW_RELEASE) {
            keys[key] = false;
        }
    }

    public static boolean isKeyDown(int keycode){
        if (keycode < 0 || keycode >= keys.length) {
            return false;
        }
        return keys[keycode];
    }

    public static boolean isKeyPressed(int keycode) {
        if (keycode < 0 || keycode >= keysPressed.length) {
            return false;
        }
        boolean pressed = keysPressed[keycode];
        keysPressed[keycode] = false;
        return pressed;
    }
}