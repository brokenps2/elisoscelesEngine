package ui;

import audio.Sound;
import init.Main;
import lwjgui.geometry.Insets;
import lwjgui.geometry.Pos;
import lwjgui.paint.Color;
import lwjgui.scene.Window;
import lwjgui.scene.WindowManager;
import lwjgui.scene.control.*;
import lwjgui.scene.layout.BorderPane;
import lwjgui.scene.layout.VBox;
import objects.Object;
import objects.ObjectGetter;
import org.lwjgl.glfw.GLFW;
import renderer.DisplayManager;
import scenes.SceneManager;

import java.util.ArrayList;
import java.util.List;

public class ObjectInterface {

    public VBox menuBox = new VBox();
    Window window;
    boolean active = false;

    public void create() {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(24));
        root.setBackgroundLegacy(null);

        Label menuLabel = new Label("objects menu");
        menuLabel.setFontSize(24);
        menuLabel.setTextFill(Color.GREEN);
        menuLabel.setAlignment(Pos.TOP_RIGHT);

        Button addb = new Button("add object");
        Button removeb = new Button("remove object");

        TextField x = new TextField();
        x.setPrompt("x");
        x.setMaxSize(50,50);
        TextField y = new TextField();
        y.setPrompt("y");
        y.setMaxSize(50,50);
        TextField z = new TextField();
        z.setPrompt("z");
        z.setMaxSize(50,50);
        TextField name = new TextField();
        name.setPrompt("name");
        TextField model = new TextField();
        model.setPrompt("model path");
        TextField texture = new TextField();
        texture.setPrompt("texture path");

        //list of all objects in SceneManager.getObjects()
        List<String> objectNames = new ArrayList<String>();
        for(Object object : SceneManager.getObjectsArray()) objectNames.add(object.getName());
        TreeView<String> objectList = new TreeView<String>();
        for(String objectName : objectNames) {
            objectList.getChildren().add(new Label(objectName));
        }

        addb.setOnAction((event) -> {
            try {
                SceneManager.addObject(ObjectGetter.getModel(model.getText(), texture.getText(), Float.parseFloat(x.getText()), Float.parseFloat(y.getText()), Float.parseFloat(z.getText()), 1f, name.getText()));
                objectNames.clear();
                for(Object object : SceneManager.getObjectsArray()) objectNames.add(object.getName());
            } catch (Exception e) {
                System.out.println("error adding object");
                e.printStackTrace();
            }
            for(String objectName : objectNames) {
                objectList.getChildren().add(new TreeItem<String>(objectName));
            }
        });

        {
            menuBox.setAlignment(Pos.TOP_LEFT);
            menuBox.setPadding(new Insets(4, 4, 4, 4));
            menuBox.setBackgroundLegacy(Color.WHITE);

            menuBox.getChildren().add(menuLabel);
            menuBox.getChildren().add(addb);
            menuBox.getChildren().add(removeb);
            menuBox.getChildren().add(x);
            menuBox.getChildren().add(y);
            menuBox.getChildren().add(z);
            menuBox.getChildren().add(name);
            menuBox.getChildren().add(model);
            menuBox.getChildren().add(texture);
            //menuBox.getChildren().add(objectList);

            root.setAlignment(Pos.TOP_RIGHT);
            root.setRight(menuBox);

            window = WindowManager.generateWindow(DisplayManager.win);
            /* this line will error if you aren't using
            this specific version of nanovg for some reason */
            window.setWindowAutoClear(false);
            window.show();

            window.setScene(new lwjgui.scene.Scene(root, Main.width, Main.height));

        }
    }

    public void toggleMenu() {
        active = !active;
    }

    public void update() {
        if(active) window.render();
    }

}
