package renderer;

import input.Keyboard;
import org.lwjgl.Version;
import org.lwjgl.glfw.GLFW;

import static org.lwjgl.glfw.GLFW.*;

public class DisplayManager {

    public static long win;
    public static boolean running = true;
    public int WIDTH;
    public int HEIGHT;

    public static long lastFrameTime;
    public static float delta;

    public static void create(int width, int height, boolean fullscreen) {

        System.out.println("LWJGL v" + Version.getVersion());

        glfwInit();

        if(fullscreen) {
            win = glfwCreateWindow(width, height, "program1", glfwGetPrimaryMonitor(), 0);
        } else {
            win = glfwCreateWindow(width, height, "program1", 0, 0);
        }




        glfwSetWindowPos(win, 600, 200);
        glfwMakeContextCurrent(win);

        glfwShowWindow(win);

        //make window not resizeable
        glfwSetWindowAttrib(win, GLFW_RESIZABLE, GLFW_FALSE);


        //make window fullscreen (keep commented out for now)
        //glfwSetWindowAttrib(win, GLFW_DECORATED, GLFW_FALSE);
        //glfwSetWindowMonitor(win, glfwGetPrimaryMonitor(), 0, 0, 1440, 1080, 144);

        glfwSetKeyCallback(win, new input.Keyboard());
        glfwSetCursorPosCallback(win, new input.Mouse());


    }

    public static void update() {


        glfwPollEvents();
        glfwSwapBuffers(win);

        long currentFrameTime = getTime();
        delta = (currentFrameTime - lastFrameTime) / 1000f;
        lastFrameTime = currentFrameTime;

        if(Keyboard.isKeyDown(GLFW_KEY_LEFT_ALT)) running = false;
        //re enable cursor
        if(Keyboard.isKeyPressed(GLFW_KEY_X)) GLFW.glfwSetInputMode(win, GLFW_CURSOR, GLFW_CURSOR_NORMAL);
        if(glfwWindowShouldClose(win)) running = false;


    }

    public static float getFrameTimeSeconds() {
        return delta;
    }

    private static long getTime() {
        return glfwGetTimerValue() * 1000 / glfwGetTimerFrequency();
    }

    public static void close() {
        System.out.println();
        System.out.println("Program closed.");
        glfwDestroyWindow(win);
        glfwTerminate();
    }

}
