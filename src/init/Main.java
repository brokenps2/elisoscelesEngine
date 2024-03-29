package init;

import audio.AudioManager;
import org.lwjgl.opengl.GL;
import renderer.DisplayManager;
import scenes.SceneManager;
import scenes.TitleScreen;

public class Main {

    //PROGRAM ENTRYPOINT

    public static int width;
    public static int height;
    public static boolean fscreen;

    public static void main(String[] args) {

        if(args.length != 3) {
            System.out.println("Usage: java -jar [jarfile] [width] [height] [fullscreen true|false]");
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

            SceneManager.loadScene(new TitleScreen());

            while(true) {
                DisplayManager.update();
                SceneManager.updateScene();
                if(!DisplayManager.running) break;
            }

            AudioManager.disposeAudio();
            SceneManager.dispose();
            DisplayManager.close();
        }

    }
}