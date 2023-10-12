package scenes;

import init.Main;
import lwjgui.LWJGUI;
import lwjgui.LWJGUIApplication;
import lwjgui.font.Font;
import lwjgui.geometry.Insets;
import lwjgui.geometry.Pos;
import lwjgui.gl.Renderer;
import lwjgui.paint.Color;
import lwjgui.scene.Window;
import lwjgui.scene.WindowManager;
import lwjgui.scene.control.Label;
import lwjgui.scene.layout.BorderPane;
import lwjgui.scene.layout.StackPane;
import lwjgui.scene.layout.VBox;
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

        SceneManager.addObject(map);
        SceneManager.playSound("res/audio/ethnight.ogg");

        createUI();

        loading = false;

    }

    public void createUI() {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(24));
        root.setBackgroundLegacy(null);

        {
            VBox vbox = new VBox();
            vbox.setAlignment(Pos.CENTER);
            vbox.setBackgroundLegacy(Color.BLUE.alpha(0.4f));
            root.setCenter(vbox);

            Label label1 = new Label("test");
            label1.setTextFill(Color.AQUA);
            vbox.getChildren().add(label1);

            Label label2 = new Label("OpenGL drawn under ui");
            label2.setTextFill(Color.WHITE);
            vbox.getChildren().add(label2);
        }

        window = WindowManager.generateWindow(DisplayManager.win);

        window.setScene(new lwjgui.scene.Scene(root, Main.width, Main.height));
        window.setWindowAutoClear(false);
        window.show();
    }

    @Override
    public void update() {
        window.render();
        WindowManager.update();
    }

    @Override
    public void dispose() {

    }
}
