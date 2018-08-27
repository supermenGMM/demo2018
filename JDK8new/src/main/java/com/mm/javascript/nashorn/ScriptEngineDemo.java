package com.mm.javascript.nashorn;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class ScriptEngineDemo {
    public static void main(String[] args) throws ScriptException {
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        ScriptEngine nashorn = scriptEngineManager.getEngineByName("nashorn");
        String name
                = "runoob";
        nashorn.eval("print('" + name + "')");
        Integer result= (Integer) nashorn.eval("10+2");

        System.out.println(result);


    }
}
