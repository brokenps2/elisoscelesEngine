var SceneManager = Java.type("scenes.SceneManager");
var Object = Java.type("objects.Object");
var ObjectGetter = Java.type("objects.ObjectGetter");
var objMan = new ObjectGetter();
var plane = objMan.getModel("res/scenes/test/default.obj", "res/scenes/test2/default.png");


SceneManager.playSound("res/audio/music/earthbound.ogg");
