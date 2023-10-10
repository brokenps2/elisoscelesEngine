var SceneManager = Java.type("scenes.SceneManager");
var Keyboard = Java.type("input.Keyboard");
var GLFW = Java.type("org.lwjgl.glfw.GLFW");




if(Keyboard.isKeyPressed(GLFW.GLFW_KEY_F1)){
    SceneManager.loadScene("res/scenes/test/");
}