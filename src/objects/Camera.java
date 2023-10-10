package objects;

import input.Mouse;
import org.lwjgl.glfw.GLFW;

import org.lwjglx.util.vector.Vector3f;
import renderer.DisplayManager;


public class Camera {

    public Vector3f position = new Vector3f(0,6, 0);
    public float pitch = 90;
    public float yaw = 90;
    public float roll;
    public static input.Keyboard keyboard = new input.Keyboard();
    boolean w = false;
    boolean s = false;
    boolean a = false;
    boolean d = false;


    char lastKey = ' ';

    double oldMouseX = 0, oldMouseY = 0, newMouseX = 0, newMouseY = 0;

    float acceleration = 0.0f;
    float strafeAcceleration = 0.0f;
    float speed = 0.4f;


    public Camera() {

    }

    public void move(float speed) {

        GLFW.glfwSetKeyCallback(DisplayManager.win, keyboard);

        if(keyboard.isKeyDown(GLFW.GLFW_KEY_W)){
            position.x += (float) (Math.sin(Math.toRadians(yaw)) * speed * DisplayManager.delta);
            position.z -= (float) (Math.cos(Math.toRadians(yaw)) * speed * DisplayManager.delta);
        }

        if(keyboard.isKeyDown(GLFW.GLFW_KEY_D)) {
            position.x -= (float) (-Math.cos(Math.toRadians(yaw)) * speed * DisplayManager.delta);
            position.z += (float) (Math.sin(Math.toRadians(yaw)) * speed * DisplayManager.delta);
        }

        if(keyboard.isKeyDown(GLFW.GLFW_KEY_A)){
            position.x += (float) (-Math.cos(Math.toRadians(yaw)) * speed * DisplayManager.delta);
            position.z -= (float) (Math.sin(Math.toRadians(yaw)) * speed * DisplayManager.delta);
        }

        if(keyboard.isKeyDown(GLFW.GLFW_KEY_S)) {
            position.x -= (float) (Math.sin(Math.toRadians(yaw)) * speed * DisplayManager.delta);
            position.z += (float) (Math.cos(Math.toRadians(yaw)) * speed * DisplayManager.delta);
        }

        if(!keyboard.isKeyDown(GLFW.GLFW_KEY_W) && !keyboard.isKeyDown(GLFW.GLFW_KEY_A) && !keyboard.isKeyDown(GLFW.GLFW_KEY_D) && !keyboard.isKeyDown(GLFW.GLFW_KEY_S) && lastKey == 'w') {
            position.x += (float) (Math.sin(Math.toRadians(yaw)) * acceleration * DisplayManager.delta);
            position.z -= (float) (Math.cos(Math.toRadians(yaw)) * acceleration * DisplayManager.delta);
        }
        if(!keyboard.isKeyDown(GLFW.GLFW_KEY_W) && !keyboard.isKeyDown(GLFW.GLFW_KEY_A) && !keyboard.isKeyDown(GLFW.GLFW_KEY_D) && !keyboard.isKeyDown(GLFW.GLFW_KEY_S) && lastKey == 's') {
            position.x -= (float) (Math.sin(Math.toRadians(yaw)) * acceleration * DisplayManager.delta);
            position.z += (float) (Math.cos(Math.toRadians(yaw)) * acceleration * DisplayManager.delta);
        }
        if(!keyboard.isKeyDown(GLFW.GLFW_KEY_W) && !keyboard.isKeyDown(GLFW.GLFW_KEY_A) && !keyboard.isKeyDown(GLFW.GLFW_KEY_D) && !keyboard.isKeyDown(GLFW.GLFW_KEY_S) && lastKey == 'a') {
            position.x += (float) (-Math.sin(Math.toRadians(yaw)) * acceleration * DisplayManager.delta);
            position.z -= (float) (Math.cos(Math.toRadians(yaw)) * acceleration * DisplayManager.delta);
        }
        if(!keyboard.isKeyDown(GLFW.GLFW_KEY_W) && !keyboard.isKeyDown(GLFW.GLFW_KEY_A) && !keyboard.isKeyDown(GLFW.GLFW_KEY_D) && !keyboard.isKeyDown(GLFW.GLFW_KEY_S) && lastKey == 'd') {
            position.x -= (float) (-Math.sin(Math.toRadians(yaw)) * acceleration * DisplayManager.delta);
            position.z += (float) (Math.cos(Math.toRadians(yaw)) * acceleration * DisplayManager.delta);
        }
        if(!keyboard.isKeyDown(GLFW.GLFW_KEY_W) && !keyboard.isKeyDown(GLFW.GLFW_KEY_A) && !keyboard.isKeyDown(GLFW.GLFW_KEY_D) && !keyboard.isKeyDown(GLFW.GLFW_KEY_S)) {
            acceleration -= 1f;
        }


        if(keyboard.isKeyDown(GLFW.GLFW_KEY_X)){
            position.y -= speed * DisplayManager.delta;
        }

        if(keyboard.isKeyDown(GLFW.GLFW_KEY_C)){
            position.y += speed * DisplayManager.delta;
        }


        if(keyboard.isKeyDown(GLFW.GLFW_KEY_UP)){
            position.x += (float) (Math.sin(Math.toRadians(yaw)) * speed * DisplayManager.delta);
            position.z -= (float) (Math.cos(Math.toRadians(yaw)) * speed * DisplayManager.delta);
        }
        if(keyboard.isKeyDown(GLFW.GLFW_KEY_DOWN)){
            position.x -= (float) (Math.sin(Math.toRadians(yaw)) * speed * DisplayManager.delta);
            position.z += (float) (Math.cos(Math.toRadians(yaw)) * speed * DisplayManager.delta);
        }
        if(keyboard.isKeyDown(GLFW.GLFW_KEY_LEFT)) {
            yaw -= 1f;
            if(pitch > 0) pitch = 0;
            if(pitch < 0) pitch = 0;
        }
        if(keyboard.isKeyDown(GLFW.GLFW_KEY_RIGHT)) {
            yaw += 1f;
            if(pitch > 0) pitch = 0;
            if(pitch < 0) pitch = 0;
        }



        calculateMouse();

    }

    public void calculateMouse() {

        if(GLFW.glfwGetMouseButton(DisplayManager.win, GLFW.GLFW_MOUSE_BUTTON_LEFT) == 1) {
            GLFW.glfwSetInputMode(DisplayManager.win, GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_DISABLED);
        }

        if(DisplayManager.mouseLocked()) {
            newMouseX = Mouse.getMouseX();
            newMouseY = Mouse.getMouseY();

            if(pitch > 90) pitch = 90;
            if(pitch < -90) pitch = -90;

            float dx = (float) (newMouseX - oldMouseX);
            float dy = (float) (newMouseY - oldMouseY);

            yaw += dx * 0.2f;
            pitch += dy * 0.2f;

            oldMouseX = newMouseX;
            oldMouseY = newMouseY;
        }
    }

    public void setPosition(float x, float y, float z) {
        position.x = x;
        position.y = y;
        position.z = z;
    }

    public void dispose() {
        pitch = 0;
        yaw = 0;
        roll = 0;
        position = null;
    }

    public Vector3f getPosition() {
        return position;
    }

    public float getPitch() {
        return pitch;
    }

    public float getYaw() {
        return yaw;
    }

    public float getRoll() {
        return roll;
    }

}
