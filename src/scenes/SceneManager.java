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

    static List<Object> objects = new ArrayList<Object>();

    public static void loadScene(String customScenePath) {

        dispose();

        scenePath = customScenePath;

        loader = new Loader();
        ModelData data = OBJLoader.loadOBJ(scenePath + "default.obj");
        RawModel model = loader.loadToVAO(data.getVertices(), data.getTextureCoords(), data.getNormals(), data.getIndices());
        ModelTexture texture = new ModelTexture(loader.loadTexture(scenePath + "default.png"));
        TexturedModel mapModel = new TexturedModel(model, texture);
        map = new Object(mapModel, new Vector3f(0,0,0),0,0, 0, 2f);

        camera = new Camera();
        player = new Player();
        player.setPosition(0,0,0);
        light = new Light(new Vector3f(2000,2000,2000), new Vector3f(1,1,1));

        scripting.exec(scenePath + "scripts/start.js");

        objects.add(map);

        disposable = true;

    }

    public static void updateScene() {

        renderer.processObject(map);

        for (Object object : objects) {
            renderer.processObject(object);
        }

        renderer.render(light, camera);
        player.movePlayer(camera);

        scripting.exec(scenePath + "scripts/update.js");

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

        if(disposable) scripting.exec("res/scenes/test/scripts/dispose.js"); disposable = false;

        Sound.stopAll();

        removeObject(map);

    }


}
