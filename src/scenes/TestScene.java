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
import ui.ObjectInterface;
import ui.TestSceneSidebar;

public class TestScene extends Scene{

    TestSceneSidebar sidebar = new TestSceneSidebar();
    ObjectInterface objectInterface = new ObjectInterface();


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
        //sidebar.create();
        objectInterface.create();
    }

    @Override
    public void update() {
        WindowManager.update();
        objectInterface.update();
    }

    @Override
    public void dispose() {

    }
}
