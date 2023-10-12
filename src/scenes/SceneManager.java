package scenes;

import audio.Sound;
import init.Main;
import models.RawModel;
import models.TexturedModel;
import obj.ModelData;
import obj.OBJLoader;
import objects.*;
import objects.Object;
import org.lwjglx.util.vector.Vector3f;
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
    static Object map;
    static Player player;
    static Camera camera;
    static Light light;
    static String scenePath;
    static Scripting scripting = new Scripting();
    static boolean disposable = false;

    static Scene currentScene;

    static List<Object> objects = new ArrayList<Object>();

    public static void loadScene(Scene scene) {
        if(scene.loading) {

            currentScene = scene;
            currentScene.start();

        }
    }

    public static void updateScene() {

        if(currentScene.loading) return;for (Object object : objects) {
            renderer.processObject(object);
        }

        renderer.render(currentScene.light, currentScene.camera);

        currentScene.player.movePlayer(currentScene.camera);

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

    public static void dispose() {

        currentScene.dispose();

        Sound.stopAll();

        removeObject(map);

    }


}
