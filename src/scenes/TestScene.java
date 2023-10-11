package scenes;

import lwjgui.LWJGUI;
import lwjgui.scene.Context;
import lwjgui.scene.Node;
import lwjgui.scene.Window;
import lwjgui.scene.WindowManager;
import lwjgui.scene.control.Label;
import lwjgui.scene.layout.StackPane;
import objects.*;
import org.lwjgl.nuklear.NkContext;
import org.lwjglx.util.vector.Vector3f;
import renderer.DisplayManager;

public class TestScene extends Scene{

    Window window;

    @Override
    public void start() {

        map = ObjectGetter.getModel("res/mdl/oldScene1/default.obj" , "res/mdl/oldScene1/default.png", 0, 0, 0, 2f);
        light = new Light(new Vector3f(2000,2000,2000), new Vector3f(1,1,1));
        player = new Player();
        camera = new Camera();

        window = WindowManager.generateWindow(DisplayManager.win);

        StackPane pane = new StackPane();
        pane.getChildren().add(new Label("Hello World!"));
        window.setScene(new lwjgui.scene.Scene(pane, 640, 480));
        window.show();

        SceneManager.addObject(map);
        SceneManager.playSound("res/audio/ethnight.ogg");

        loading = false;

    }

    @Override
    public void update() {

        window.render();

    }

    @Override
    public void dispose() {

    }
}
