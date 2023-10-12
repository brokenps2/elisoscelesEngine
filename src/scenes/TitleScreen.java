package scenes;

import audio.Sound;
import init.Main;
import lwjgui.geometry.Insets;
import lwjgui.geometry.Pos;
import lwjgui.paint.Color;
import lwjgui.scene.Window;
import lwjgui.scene.WindowManager;
import lwjgui.scene.control.Button;
import lwjgui.scene.control.Label;
import lwjgui.scene.layout.BorderPane;
import lwjgui.scene.layout.VBox;
import org.lwjgl.glfw.GLFW;
import renderer.DisplayManager;

public class TitleScreen extends Scene{

    Window window;

    @Override
    public void start() {
        createUI();
        renderable = false;
        loading = false;
    }

    public void createUI() {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(24));
        root.setBackgroundLegacy(null);

        Label menuLabel = new Label("title menu");
        menuLabel.setFontSize(40);
        menuLabel.setTextFill(Color.GREEN);
        menuLabel.setAlignment(Pos.TOP_RIGHT);

        Label scenesLabel = new Label("scenes");
        scenesLabel.setTextFill(Color.AQUA);

        Button scene1Button = new Button("TestScene");

        {
            VBox menuBox = new VBox();
            menuBox.setAlignment(Pos.TOP_LEFT);
            menuBox.setPadding(new Insets(4,4,4,4));
            menuBox.setBackgroundLegacy(Color.WHITE);

            menuBox.getChildren().add(menuLabel);
            menuBox.getChildren().add(scenesLabel);
            menuBox.getChildren().add(scene1Button);

            scene1Button.setOnAction((event) -> {
                SceneManager.loadScene(new TestScene());
            });

            root.setAlignment(Pos.TOP_LEFT);
            root.setCenter(menuBox);
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
