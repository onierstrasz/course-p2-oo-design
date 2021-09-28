package annotations;

import java.io.*;
import java.lang.reflect.*;
import java.util.*;

public class Deserializer {

	private static final int NOT_FOUND = -1;  
	private BufferedReader reader;
	
	public Deserializer(InputStream in) {
		reader = new BufferedReader(new InputStreamReader(in));
	}
	
	public Deserializer(String in) {
		reader = new BufferedReader(new StringReader(in));
	}
	
	public Object deserialize() {
		Object o = this.readInstance();
		Map<String,String> map = this.readProperties();
		for (Method m : o.getClass().getMethods()) {
			if (m.isAnnotationPresent(SetProperty.class)) {
				this.writeStringProperty(o, m, map);
			}
		}
		return o;
	}
	
	private void writeStringProperty(Object o, Method m, Map<String,String> map) {
		try {
			String key = m.getAnnotation(SetProperty.class).value();
			m.invoke(o, map.get(key));
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		} 
	}

	private Map<String,String> readProperties() {
		Map<String,String> map = new TreeMap<String,String>();
		while (true) {
			String line = this.nextLine();
			int index = line.indexOf('=');
			if (index == NOT_FOUND) break;
			map.put(line.substring(0,index).trim(), line.substring(index+1).trim());
		}
		return map;
	}
	
	private Object readInstance() {
		try {
			String line = this.nextLine();
			assert line.endsWith(" {");
			String name = line.substring(0, line.indexOf(' '));
			return Class.forName(name).newInstance();
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}
	
	private String nextLine() {
		try {
			String line = reader.readLine();
			line = line.trim();
			return line.length() == 0 ? nextLine() : line;
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}
	
	public void close() {
		try {
			reader.close();
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}
	
}
