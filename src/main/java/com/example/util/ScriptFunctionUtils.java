package com.example.util;

import javax.script.Bindings;
import javax.script.Compilable;
import javax.script.CompiledScript;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * java脚本引擎工具类
 * @author OKali
 *
 */
public class ScriptFunctionUtils {
	
	public static void main(String[] args) throws ScriptException {
		String javaStr = "true&&false||(false||false&&(true||false))";
		handleScriptToObject(javaStr);
	}
	
	/**
	 * 通过java脚本引擎将“true&&false||(false&&true)"转换成boolean类型
	 * @param javaStr
	 * @author OKali 默然
	 * @return
	 * @throws Exception 
	 */
	public static Object handleScriptToObject(String javaStr) throws ScriptException {
		//利用java的脚本引擎
		ScriptEngine scriptEngine = new ScriptEngineManager().getEngineByName("javascript");
		if (scriptEngine == null) {
			throw new RuntimeException("无法找到javascript语言引擎");
		}
		if (scriptEngine instanceof Compilable) {
			Compilable compilable = (Compilable) scriptEngine;
			Bindings bindings = scriptEngine.createBindings();
			CompiledScript compiledScript = compilable.compile(javaStr);
			Object jsObject = compiledScript.eval(bindings);
			System.out.println(jsObject);
			return jsObject;
		} else {
			throw new RuntimeException("该脚本引擎不支持编译");
		}
	}

}
