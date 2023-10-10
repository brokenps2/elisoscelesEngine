<h1>elisoscelesEngine</h1>
  <h4>internal game engine based on <a href="https://www.lwjgl.org/">LWJGL</a></h4>

  <hr>

  <img src="example.png">

  <hr>

  <h3>Structure</h3>
  <p>The engine works using "scene" subfolders within the /res folder. each scene folder has a map.obj, map.png, and any other assets required by the scene, as well as three scripts. these scripts are written in JS and directly interface with the engine through Java.type() calls. Each scene has a start, update, & dispose js file and they're used in their respective ways throughout the program</p>

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
  <p>  - UI</p>
  <p>  - Spatial audio</p>
