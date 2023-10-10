package scenes;

import lwjgui.LWJGUI;
import lwjgui.LWJGUIApplication;
import lwjgui.scene.Window;
import lwjgui.scene.control.Label;
import lwjgui.scene.layout.StackPane;
import objects.*;
import objects.Object;
import org.lwjgl.nanovg.NanoVG;
import org.lwjgl.nanovg.NanoVGGL2;
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

        window = LWJGUI.initialize();
        StackPane pane = new StackPane();
        window.getScene().setRoot(pane);
        pane.getChildren().add(new Label("yahoo"));
        window.show();

        SceneManager.addObject(map);
        SceneManager.playSound("res/audio/ethnight.ogg");

        loading = false;

    }

    @Override
    public void update() {

    }

    @Override
    public void dispose() {

    }
}
