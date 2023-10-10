package objects;

import com.bulletphysics.collision.shapes.*;
import models.RawModel;
import models.TexturedModel;
import obj.ModelData;
import obj.OBJLoader;
import org.lwjglx.util.vector.Vector;
import org.lwjglx.util.vector.Vector3f;
import renderer.Loader;
import textures.ModelTexture;

public class ObjectGetter {

    static Loader loader = new Loader();

    public static Object getModel(String modelPath, String texturePath) {
        ModelData modelData = OBJLoader.loadOBJ(modelPath);
        RawModel rawModel = loader.loadToVAO(modelData.getVertices(), modelData.getTextureCoords(), modelData.getNormals(), modelData.getIndices());
        ModelTexture modelTexture = new ModelTexture(loader.loadTexture(texturePath));
        TexturedModel texturedModel = new TexturedModel(rawModel, modelTexture);

        return new Object(texturedModel, new Vector3f(0,0,0), 0, 0, 0, 1);
    }

    public static Object getModel(String modelPath, String texturePath, float x, float y, float z, float scale) {
        ModelData modelData = OBJLoader.loadOBJ(modelPath);
        RawModel rawModel = loader.loadToVAO(modelData.getVertices(), modelData.getTextureCoords(), modelData.getNormals(), modelData.getIndices());
        ModelTexture modelTexture = new ModelTexture(loader.loadTexture(texturePath));
        TexturedModel texturedModel = new TexturedModel(rawModel, modelTexture);
        Vector3f position = new Vector3f(x,y,z);

        return new Object(texturedModel, position, 0, 0, 0, scale);
    }

    public static Object getModel(String modelPath, String texturePath, float scale) {
        ModelData modelData = OBJLoader.loadOBJ(modelPath);
        RawModel rawModel = loader.loadToVAO(modelData.getVertices(), modelData.getTextureCoords(), modelData.getNormals(), modelData.getIndices());
        ModelTexture modelTexture = new ModelTexture(loader.loadTexture(texturePath));
        TexturedModel texturedModel = new TexturedModel(rawModel, modelTexture);

        return new Object(texturedModel, new Vector3f(0,0,0), 0, 0, 0, scale);
    }

    public static Object getModel(String modelPath, String texturePath, float x, float y, float z, char colliderType) {
        ModelData modelData = OBJLoader.loadOBJ(modelPath);
        RawModel rawModel = loader.loadToVAO(modelData.getVertices(), modelData.getTextureCoords(), modelData.getNormals(), modelData.getIndices());
        ModelTexture modelTexture = new ModelTexture(loader.loadTexture(texturePath));
        TexturedModel texturedModel = new TexturedModel(rawModel, modelTexture);
        Vector3f position = new Vector3f(x,y,z);

        return new Object(texturedModel, position, 0, 0, 0, 1);
    }

    public static Object getModel(String modelPath, String texturePath, float x, float y, float z, float scale, char colliderType) {
        ModelData modelData = OBJLoader.loadOBJ(modelPath);
        RawModel rawModel = loader.loadToVAO(modelData.getVertices(), modelData.getTextureCoords(), modelData.getNormals(), modelData.getIndices());
        ModelTexture modelTexture = new ModelTexture(loader.loadTexture(texturePath));
        TexturedModel texturedModel = new TexturedModel(rawModel, modelTexture);
        Vector3f position = new Vector3f(x,y,z);

        return new Object(texturedModel, position, 0, 0, 0, 1);
    }


}
