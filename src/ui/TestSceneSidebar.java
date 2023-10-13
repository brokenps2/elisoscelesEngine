package ui;

import audio.Sound;
import init.Main;
import lwjgui.geometry.Insets;
import lwjgui.geometry.Pos;
import lwjgui.paint.Color;
import lwjgui.scene.Window;
import lwjgui.scene.WindowManager;
import lwjgui.scene.control.Button;
import lwjgui.scene.control.Label;
import lwjgui.scene.control.Toggle;
import lwjgui.scene.control.ToggleButton;
import lwjgui.scene.layout.BorderPane;
import lwjgui.scene.layout.VBox;
import org.lwjgl.glfw.GLFW;
import renderer.DisplayManager;
import scenes.Scene;
import scenes.SceneManager;

public class TestSceneSidebar extends UIObject{

    Window window;
    Button resetb = new Button("reset scene");
    public VBox menuBox = new VBox();

    @Override
    public void create() {
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
            menuBox.getChildren().add(resetb);

            root.setAlignment(Pos.TOP_LEFT);
            root.setLeft(menuBox);
        }

        window = WindowManager.generateWindow(DisplayManager.win);
        /* this line will error if you aren't using
        this specific version of nanovg for some reason */
        window.setWindowAutoClear(false);

        window.setScene(new lwjgui.scene.Scene(root, Main.width, Main.height));
        window.show();
    }

    public void enableReset(Scene scene) {
        resetb.setOnAction((event) -> {
            SceneManager.loadScene(scene);
        });
    }

    @Override
    public void update() {
        window.render();
    }

}
