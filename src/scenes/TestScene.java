package scenes;

import audio.AudioManager;
import audio.Sound;
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
import lwjgui.scene.control.Button;
import lwjgui.scene.control.Label;
import lwjgui.scene.control.Menu;
import lwjgui.scene.control.MenuBar;
import lwjgui.scene.layout.BorderPane;
import lwjgui.scene.layout.HBox;
import lwjgui.scene.layout.StackPane;
import lwjgui.scene.layout.VBox;
import objects.*;
import objects.Object;
import org.lwjgl.glfw.GLFW;
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

        Label menuLabel = new Label("menu");
        menuLabel.setFontSize(24);
        menuLabel.setTextFill(Color.GREEN);
        menuLabel.setAlignment(Pos.TOP_RIGHT);

        Label musicLabel = new Label("music");
        musicLabel.setTextFill(Color.AQUA);
        Label otherLabel = new Label("other");
        Button ebs = new Button("earthbound shop");
        Button ebg = new Button("earthbound guardian");
        Button ebn = new Button("earthbound night");
        Button lockb = new Button("lock mouse");

        ebs.setOnAction((event) -> {
            Sound.stopAll();
            SceneManager.playSound("res/audio/ethshop.ogg");
        });
        ebg.setOnAction((event) -> {
            Sound.stopAll();
            SceneManager.playSound("res/audio/ethguardian.ogg");
        });
        ebn.setOnAction((event) -> {
            Sound.stopAll();
            SceneManager.playSound("res/audio/ethnight.ogg");
        });
        lockb.setOnAction((event) -> {
            GLFW.glfwSetInputMode(DisplayManager.win, GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_DISABLED);
        });

        {
            VBox menuBox = new VBox();
            menuBox.setAlignment(Pos.TOP_LEFT);
            menuBox.setPadding(new Insets(4,4,4,4));
            menuBox.setBackgroundLegacy(Color.WHITE);

            menuBox.getChildren().add(menuLabel);
            menuBox.getChildren().add(musicLabel);
            menuBox.getChildren().add(ebs);
            menuBox.getChildren().add(ebg);
            menuBox.getChildren().add(ebn);
            menuBox.getChildren().add(otherLabel);
            menuBox.getChildren().add(lockb);

            root.setAlignment(Pos.TOP_LEFT);
            root.setLeft(menuBox);
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
