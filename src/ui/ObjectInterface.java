package ui;

import init.Main;
import lwjgui.geometry.Insets;
import lwjgui.geometry.Pos;
import lwjgui.paint.Color;
import lwjgui.scene.Window;
import lwjgui.scene.WindowManager;
import lwjgui.scene.control.Button;
import lwjgui.scene.control.Label;
import lwjgui.scene.control.TextField;
import lwjgui.scene.layout.BorderPane;
import lwjgui.scene.layout.VBox;
import renderer.DisplayManager;
import scenes.SceneManager;
import scenes.TestScene;

public class ObjectInterface extends UIObject{

    Window window;

    @Override
    public void create() {

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(24));
        root.setBackgroundLegacy(null);

        Label menuLabel = new Label("add object");
        menuLabel.setFontSize(24);
        menuLabel.setTextFill(Color.GREEN);
        menuLabel.setAlignment(Pos.TOP_RIGHT);

        Button addButton = new Button("add object");

        TextField objPath = new TextField();
        TextField texPath = new TextField();
        TextField x = new TextField();
        x.setMaxSize(50, 50);
        TextField y = new TextField();
        y.setMaxSize(50, 50);
        TextField z = new TextField();
        z.setMaxSize(50, 50);
        TextField scale = new TextField();

        {
            VBox menuBox = new VBox();
            menuBox.setAlignment(Pos.TOP_LEFT);
            menuBox.setPadding(new Insets(8,8,8,8));
            menuBox.setBackgroundLegacy(Color.WHITE);

            menuBox.getChildren().add(menuLabel);
            menuBox.getChildren().add(addButton);
            menuBox.getChildren().add(objPath);
            menuBox.getChildren().add(texPath);
            menuBox.getChildren().add(x);
            menuBox.getChildren().add(y);
            menuBox.getChildren().add(z);
            menuBox.getChildren().add(scale);

            root.setAlignment(Pos.TOP_RIGHT);
            root.setCenter(menuBox);
        }

        window = WindowManager.generateWindow(DisplayManager.win);
        /* this line will error if you aren't using
        this specific version of nanovg for some reason */
        window.setWindowAutoClear(false);

        window.setScene(new lwjgui.scene.Scene(root, Main.width, Main.height));
        window.show();

    }

    @Override
    public void update() {
        window.render();
    }
}
