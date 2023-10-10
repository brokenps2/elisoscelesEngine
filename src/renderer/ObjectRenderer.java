package renderer;

import models.RawModel;
import models.TexturedModel;
import objects.Object;
import org.lwjgl.opengl.*;
import org.lwjglx.util.vector.Matrix4f;
import shaders.StaticShader;
import toolbox.Math;

import java.util.List;
import java.util.Map;

public class ObjectRenderer {
    private StaticShader shader;

    public ObjectRenderer(StaticShader shader, Matrix4f projectionMatrix) {
        this.shader = shader;
        shader.start();
        shader.loadProjectionMatrix(projectionMatrix);
        shader.stop();
    }


    public void render(Map<TexturedModel, List<Object>> objects) {
        for(TexturedModel model:objects.keySet()) {
            prepareTexturedModel(model);
            List<Object> batch = objects.get(model);
            for(Object object:batch) {
                prepareInstance(object);
                GL11.glDrawElements(GL11.GL_TRIANGLES, model.getRawModel().getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
            }
            unbindTexturedModel();
        }
    }

    private void prepareTexturedModel(TexturedModel model) {
        RawModel rawModel = model.getRawModel();

        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);

        GL30.glBindVertexArray(rawModel.getVaoID());
        GL20.glEnableVertexAttribArray(0); // enable attrib array 0
        GL20.glEnableVertexAttribArray(1); // enable attrib array 1
        GL20.glEnableVertexAttribArray(2); // enable attrib array 2

        GL13.glActiveTexture(GL13.GL_TEXTURE0); // activate texture bank 0
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, model.getTexture().getID()); // bind texture
    }

    private void unbindTexturedModel() {

        GL20.glDisableVertexAttribArray(0); // disable attrib array 0
        GL20.glDisableVertexAttribArray(1); // disable attrib array 1
        GL30.glBindVertexArray(0);

    }

    private void prepareInstance(Object object) {

        Matrix4f transformationMatrix = Math.createTransformationMatrix(object.getPosition(), object.getRotX(),
                object.getRotY(), object.getRotZ(), object.getScale());
        shader.loadTransformationMatrix(transformationMatrix);

    }



}
