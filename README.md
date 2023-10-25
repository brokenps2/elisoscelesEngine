<h1>elisoscelesEngine</h1>
  <h4>internal game engine based on <a href="https://www.lwjgl.org/">LWJGL</a></h4>

  <hr>

  <img src="example.png">

  <hr>

  <h3>Structure</h3>
  <p>The main difference between the new version & the old version is that the scenes system (which is arguably the most important part of a game engine) was based on JavaScript, which was a flawed system for a number of reasons. It was using deprecated libraries, Java.type calls, & was overall a bad way of loading & updating scenes.</p>
  <br>
  <p>The new version simply uses java classes as Scenes. Java classes are a lot less modifiable & has the issue of having to recompile every time you edit a scene, but in my opinion the pros outweigh the cons.</p>
  <br>
  <p>In the new version, the scenes systems works through a SceneManager class, which loads Scene classes. There are 3 subtypes of the scene class, StaticScene, RenderedScene, & PhysicalScene. StaticScene is the simplest type of scene. It only allows for UI & images, no 3D. RenderedScene is a scene that allows for OpenGL rendering of .obj files, textures & shaders. The renderer still needs work, such as multitexturing, improved lighting, & more camera types, but the engine is still very unfinished, & i'll add that stuff later. The final scene type is PhysicalScene, which is the exact same as RenderedScene, but with the capability for physics to be added once I get it added. All three scene types make calls to the UIManager & SceneManager which in turn play audio & render objects, which gets displayed on the screen through DisplayManager.</p>

<hr>

  <h3>Using</h3>
  <p>The engine is packaged as an intelliJ project, all you have to do is clone the repo, add the lwjgl libraries, open the project in intelliJ, and add the libraries to the project.</p>

<hr>
  <p>- Main class</p>
  <p>  - DisplayManager creates window</p>
  <p>    - SceneManager loads scene</p>
  <p>    - SceneManager updates scene, window, & renders scene objects</p>
  <p>    - SceneManager disposes scene</p>
  <p>    - DisplayManager closes window</p>

  <hr>

  <h3>TO-DO</h3>
  <p>  - physics (IN PROGRESS)</p>
  <p>  - Save files</p>
  <p>  - UI (IN PROGRESS)</p>
  <p>  - Spatial audio</p>
