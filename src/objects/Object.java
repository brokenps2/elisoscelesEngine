package objects;

import com.bulletphysics.collision.dispatch.CollisionObject;
import com.bulletphysics.dynamics.RigidBody;
import com.bulletphysics.linearmath.MotionState;
import com.bulletphysics.linearmath.Transform;
import models.TexturedModel;
import org.lwjglx.util.vector.Vector3f;

public class Object {

    private TexturedModel model;
    private Vector3f position;
    private float rotX, rotY, rotZ;
    private float scale;

    public Object(TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ, float scale) {
        this.model = model;
        this.position = position;
        this.rotX = rotX;
        this.rotY = rotY;
        this.rotZ = rotZ;
        this.scale = scale;
    }

    public void move(float dx, float dy, float dz) {
        this.position.x += dx;
        this.position.y += dy;
        this.position.z += dz;
    }

    public void rotate(float dx, float dy, float dz) {
        this.rotX += dx;
        this.rotY += dy;
        this.rotZ += dz;
    }

    public void update() {

    }

    public TexturedModel getModel() {
        return model;
    }

    public Vector3f getPosition() {
        return position;
    }

    public float getRotX() {
        return rotX;
    }

    public float getRotY() {
        return rotY;
    }

    public float getRotZ() {
        return rotZ;
    }

    public float getScale() {
        return scale;
    }

    public void setModel(TexturedModel model) {
        this.model = model;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public void setPositionHorizontal(float x, float z) {
        x = this.position.x;
        z = this.position.z;
    }

    public void setRotX(float rotX) {
        this.rotX = rotX;
    }

    public void setRotY(float rotY) {
        this.rotY = rotY;
    }

    public void setRotZ(float rotZ) {
        this.rotZ = rotZ;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

}
