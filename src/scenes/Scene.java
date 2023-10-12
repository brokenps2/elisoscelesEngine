package scenes;

import objects.Camera;
import objects.Light;
import objects.Object;
import objects.Player;

public abstract class Scene {

    public Object map;
    public Light light;
    public Player player;
    public Camera camera;
    public boolean loading = true;
    public boolean renderable = true;
    public abstract void start();
    public abstract void update();
    public abstract void dispose();

}
