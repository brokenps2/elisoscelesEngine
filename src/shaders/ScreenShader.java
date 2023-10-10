package shaders;

public class ScreenShader extends ShaderProgram{

    public static final String vertexFile = "src/shaders/terrainVertexShader.glsl";
    public static final String fragmentFile = "src/shaders/terrainFragmentShader.glsl";

    public ScreenShader(String vertexFile, String fragmentFile) {
        super(vertexFile, fragmentFile);
    }

    public ScreenShader() {
        super(vertexFile, fragmentFile);
    }

    @Override
    protected void getAllUniformLocations() {

        super.getUniformLocation("transformationMatrix");

    }

    @Override
    protected void bindAttributes() {

        super.bindAttribute(1, "texCoords");

    }
}
