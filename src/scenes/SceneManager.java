package scenes;

import audio.Sound;
import init.Main;
import lwjgui.scene.Window;
import models.RawModel;
import models.TexturedModel;
import obj.ModelData;
import obj.OBJLoader;
import objects.*;
import objects.Object;
import org.lwjgl.opengl.GL11;
import org.lwjglx.util.vector.Vector3f;
import renderer.DisplayManager;
import renderer.Loader;
import renderer.MasterRenderer;
import textures.ModelTexture;

import java.util.ArrayList;
import java.util.List;

public class SceneManager {

    /*scenemanager class:
    *
    * - all methods and variables are public static and can be accessed from anywhere
    * - first the scene file is loaded
    *
    *
    *
    * */

    static Loader loader;
    static MasterRenderer renderer = new MasterRenderer(Main.width, Main.height);
    static Player player;
    static Camera camera;
    static Light light;
    static String scenePath;
    static Scripting scripting = new Scripting();
    static boolean disposable = false;

    public static Scene currentScene;

    static List<Object> objects = new ArrayList<Object>();

    public static void loadScene(Scene scene) {

        dispose();

        if(scene.loading) {

            currentScene = scene;
            currentScene.start();

        }
    }

    public static void updateScene() {

        if(currentScene.loading) return;

        if(currentScene.renderable) {
            for (Object object : objects) {
                GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
                GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
                renderer.processObject(object);
            }
            renderer.render(currentScene.light, currentScene.camera);
            currentScene.player.movePlayer(currentScene.camera);
        }

        currentScene.update();

    }

    public static void addObject(Object object) {
        objects.add(object);
    }

    public static void removeObject(Object object) {
        objects.remove(object);
    }

    public static void playSound(String path) {
        Sound sound = new Sound(path, false, 1f);
        sound.play();
    }

    public static Loader getLoader() {
        return loader;
    }

    public static Vector3f getPlayerPosition() {
        return player.getPosition();
    }

    public static float getPlayerYaw() {
        return camera.yaw;
    }

    public static float getPlayerPitch() {
        return camera.pitch;
    }

    public static List<Object> getObjectsArray() {
        return objects;
    }

    public static void dispose() {

        if(currentScene != null) currentScene.dispose();

        objects.clear();

        Sound.stopAll();

    }


}
