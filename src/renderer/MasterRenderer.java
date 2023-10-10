package renderer;

import models.TexturedModel;
import objects.Camera;
import objects.Light;
import objects.Object;
import org.lwjgl.opengl.*;
import org.lwjglx.util.vector.Matrix4f;
import shaders.StaticShader;
import shaders.TerrainShader;
import terrain.Terrain;

import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.lwjgl.glfw.GLFW.*;

public class MasterRenderer {

    private static final float FOV = 90;
    private static final float NEAR_PLANE = 0.1f;
    private static final float FAR_PLANE = 1000;
    public static float RED = 0.0f;
    public static float GREEN = 0.0f;
    public static float BLUE = 0.0f;

    private Matrix4f projectionMatrix;

    private StaticShader shader = new StaticShader();
    private ObjectRenderer objectRenderer;

    private TerrainRenderer terrainRenderer;
    private TerrainShader terrainShader = new TerrainShader();

    private Map<TexturedModel,List<Object>> objects = new HashMap<TexturedModel, List<Object>>();
    private List<Terrain> terrains = new ArrayList<Terrain>();


    public MasterRenderer(int width, int height) {

        GL11.glViewport(0, 0, width, height);
        GL11.glOrtho(0, width, height, 0, 1, -1);

        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glCullFace(GL11.GL_BACK);

        GL11.glDisable(GL11.GL_POINT_SMOOTH);
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        GL11.glDisable(GL11.GL_POLYGON_SMOOTH);
        GL11.glHint(GL11.GL_POINT_SMOOTH, GL11.GL_DONT_CARE);
        GL11.glHint(GL11.GL_LINE_SMOOTH, GL11.GL_DONT_CARE);
        GL11.glHint(GL11.GL_POLYGON_SMOOTH_HINT, GL11.GL_DONT_CARE);

        createProjectionMatrix();
        objectRenderer = new ObjectRenderer(shader, projectionMatrix);
        terrainRenderer = new TerrainRenderer(terrainShader, projectionMatrix);
        shader.start();
        shader.loadProjectionMatrix(projectionMatrix);
        shader.stop();
    }

    public void prepare() {
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glClearColor(RED, GREEN, BLUE, 1);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
    }


    public void render(Light sun, Camera camera) {
        prepare();
        shader.start();
        shader.loadSkyColor(RED, GREEN, BLUE);
        shader.loadLight(sun);
        shader.loadViewMatrix(camera);

        objectRenderer.render(objects);

        shader.stop();

        terrainShader.start();
        terrainShader.loadLight(sun);
        terrainShader.loadViewMatrix(camera);
        terrainRenderer.render(terrains);
        terrainShader.stop();

        terrains.clear();
        objects.clear();
    }

    public void processTerrain(Terrain terrain) {
        terrains.add(terrain);
    }

    public void processObject(Object object) {
        TexturedModel model = object.getModel();
        List<Object> batch = objects.get(model);
        if(batch!=null) {
            batch.add(object);
        } else {
            List<Object> newBatch = new ArrayList<Object>();
            newBatch.add(object);
            objects.put(model, newBatch);
        }
    }

    private void createProjectionMatrix(){
        float aspectRatio = 800f / 600f;
        float y_scale = (float) ((1f / java.lang.Math.tan(java.lang.Math.toRadians(FOV / 2f))) * aspectRatio);
        float x_scale = y_scale / aspectRatio;
        float frustum_length = FAR_PLANE - NEAR_PLANE;

        projectionMatrix = new Matrix4f();
        projectionMatrix.m00 = x_scale;
        projectionMatrix.m11 = y_scale;
        projectionMatrix.m22 = -((FAR_PLANE + NEAR_PLANE) / frustum_length);
        projectionMatrix.m23 = -1;
        projectionMatrix.m32 = -((2 * NEAR_PLANE * FAR_PLANE) / frustum_length);
        projectionMatrix.m33 = 0;
    }

    public void dispose() {
        shader.dispose();
        terrainShader.dispose();
    }

}
