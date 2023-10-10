package init;

import audio.AudioManager;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjglx.Sys;
import renderer.DisplayManager;
import scenes.SceneManager;
import scenes.TestScene;

public class Main {

    //PROGRAM ENTRYPOINT

    public static int width;
    public static int height;
    public static boolean fscreen;

    public static void main(String[] args) {

        if(args.length != 3) {
            System.out.println("Usage: java -jar [jarfile] [width] [height] [fullscreen (true/false)]");
            System.exit(0);
        }

        width = Integer.parseInt(args[0]);
        height = Integer.parseInt(args[1]);
        fscreen = Boolean.parseBoolean(args[2]);

        if(PreChecks.checkBeforeInit()) {
            DisplayManager.create(width, height, fscreen);
            AudioManager.initAudio();
            GL.createCapabilities();
            System.out.println();
            System.out.println();

            SceneManager.loadScene(new TestScene());

            while(true) {
                DisplayManager.update();
                if(!DisplayManager.running) break;

                SceneManager.updateScene();

            }

            AudioManager.disposeAudio();
            SceneManager.dispose();
            DisplayManager.close();
        }

    }
}