var SceneManager = Java.type("scenes.SceneManager");
var DisplayManager = Java.type("renderer.DisplayManager");
var Keyboard = Java.type("input.Keyboard");
var GLFW = Java.type("org.lwjgl.glfw.GLFW");
var Scripting = Java.type("scenes.Scripting");
var scr = new Scripting();

if(Keyboard.isKeyPressed(GLFW.GLFW_KEY_F1)){
    SceneManager.loadScene("res/scenes/test2/");
}