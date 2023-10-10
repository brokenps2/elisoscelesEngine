package objects;

import com.bulletphysics.collision.shapes.BoxShape;
import com.bulletphysics.collision.shapes.CollisionShape;
import com.bulletphysics.dynamics.RigidBody;
import com.bulletphysics.dynamics.RigidBodyConstructionInfo;
import com.bulletphysics.linearmath.DefaultMotionState;
import org.lwjgl.glfw.GLFW;
import org.lwjglx.util.vector.Vector3f;
import renderer.DisplayManager;
import terrain.Terrain;

import static objects.Camera.keyboard;

public class Player {

    public static Vector3f position = new Vector3f(0,0,0);


    public static float speed = 20f;
    private static final float gravity = -50f;
    private static final float jumpPower = 30f;

    private static final float terrainHeight = 6;
    private float upwardSpeed = 0;


    public void movePlayer(Camera camera) {

        position = camera.getPosition();

        upwardSpeed += gravity * DisplayManager.getFrameTimeSeconds();
        position.y += upwardSpeed * DisplayManager.getFrameTimeSeconds();

        if(position.y < terrainHeight) {
            upwardSpeed = 0;
            position.y = terrainHeight;
        }

        if(GLFW.glfwGetKey(DisplayManager.win, GLFW.GLFW_KEY_R) == GLFW.GLFW_PRESS) {
            position.y = 6;
        }

        if(keyboard.isKeyDown(GLFW.GLFW_KEY_SPACE)){
            if(position.y == terrainHeight) {
                upwardSpeed = jumpPower;
            }
        }

        if(keyboard.isKeyDown(GLFW.GLFW_KEY_LEFT_SHIFT)) {
            speed = 30f;
        } else {
            speed = 20f;
        }


        camera.position.x = this.position.x;
        camera.position.z = this.position.z;
        camera.move(speed);

    }

    public void setPosition(float x, float y, float z) {
        position.x = x;
        position.y = y;
        position.z = z;
    }

    public Vector3f getPosition() {
        return position;
    }

    public void disposeLoader() {
    }

}
