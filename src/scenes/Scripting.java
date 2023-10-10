package scenes;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class Scripting {

    ScriptEngineManager manager = new ScriptEngineManager();
    ScriptEngine engine = manager.getEngineByName("JavaScript");

    public void exec(String script) {
        try {
            engine.eval(new java.io.FileReader(script));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
