var SceneManager = Java.type("scenes.SceneManager");
var Object = Java.type("objects.Object");
var ObjectGetter = Java.type("objects.ObjectGetter");
var objMan = new ObjectGetter();
var cube = objMan.getModel("res/scenes/test/objects/cube.obj", "res/scenes/test/objects/brick.png", 0, 0, 0, 1);
var room = objMan.getModel("res/mdl/room.obj", "res/bitmap/testmaphouse.png", 0, 0, 0, 1);
var mario = objMan.getModel("res/scenes/test/objects/mario.obj", "res/scenes/test/objects/mario.png", 0, 0, 0, 4);
var table = objMan.getModel("res/objects/table.obj", "res/bitmap/wood.png", 8, 0.2, 8, 3);


//SceneManager.addObject(cube);
SceneManager.addObject(table);
SceneManager.playSound("res/audio/music/banana.ogg");
