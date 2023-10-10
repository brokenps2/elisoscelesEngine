package input;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWCursorPosCallback;;

public class Mouse extends GLFWCursorPosCallback {

    private GLFWCursorPosCallback mouseMove;

    public static double mouseX;
    public static double mouseY;

    public static double getMouseX() {
        return mouseX;
    }

    public static double getMouseY() {
        return mouseY;
    }

    @Override
    public void invoke(long window, double xpos, double ypos) {

        mouseX = xpos;
        mouseY = ypos;

    }


}
