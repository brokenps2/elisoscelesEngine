package renderer;

import models.RawModel;
import models.TexturedModel;
import objects.Object;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjglx.util.vector.Matrix4f;
import org.lwjglx.util.vector.Vector3f;
import shaders.TerrainShader;
import terrain.Terrain;
import textures.ModelTexture;
import toolbox.Math;

import java.util.List;

public class TerrainRenderer {

    private TerrainShader shader;

    public TerrainRenderer(TerrainShader shader, Matrix4f projectionMatrix) {
        this.shader = shader;
        shader.start();
        shader.loadSkyColor(MasterRenderer.RED, MasterRenderer.GREEN, MasterRenderer.BLUE);
        shader.loadProjectionMatrix(projectionMatrix);
        shader.stop();
    }

    public void render(List<Terrain> terrains) {
        for(Terrain terrain:terrains) {
            prepareTerrain(terrain);
            loadModelMatrix(terrain);
            GL11.glDrawElements(GL11.GL_TRIANGLES, terrain.getModel().getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
            unbindTexturedModel();
        }

    }

    private void prepareTerrain(Terrain terrain) {
        RawModel rawModel = terrain.getModel();

        GL30.glBindVertexArray(rawModel.getVaoID());
        GL20.glEnableVertexAttribArray(0); // enable attrib array 0
        GL20.glEnableVertexAttribArray(1); // enable attrib array 1
        GL20.glEnableVertexAttribArray(2); // enable attrib array 2
        ModelTexture texture = terrain.getTexture();

        GL13.glActiveTexture(GL13.GL_TEXTURE0); // activate texture bank 0
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture.getID()); // bind texture
    }

    private void unbindTexturedModel() {

        GL20.glDisableVertexAttribArray(0); // disable attrib array 0
        GL20.glDisableVertexAttribArray(1); // disable attrib array 1
        GL30.glBindVertexArray(0);

    }

    private void loadModelMatrix(Terrain terrain) {

        Matrix4f transformationMatrix = Math.createTransformationMatrix(new Vector3f(terrain.getX(), 0, terrain.getZ()), 0, 0, 0, 1);
        shader.loadTransformationMatrix(transformationMatrix);

    }

}
