package scenes;

import objects.*;
import objects.Object;
import org.lwjglx.util.vector.Vector3f;

public class TestScene extends Scene{

    @Override
    public void start() {

        map = ObjectGetter.getModel("res/mdl/oldScene1/default.obj" , "res/mdl/oldScene1/default.png", 0, 0, 0, 2f);
        light = new Light(new Vector3f(2000,2000,2000), new Vector3f(1,1,1));
        player = new Player();
        camera = new Camera();

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

    @Override
    public boolean isLoading() {
        return loading;
    }

}
