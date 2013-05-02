package ps.system.api;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class SimClassLoader {
	

	private String classpath;
	private String classname;
	
	private File directory;
	private Object classinstance;
	
	public SimClassLoader(String classpath, String classname) {
		this.classpath = classpath;
		this.classname = classname;
		
		directory = new File(this.classpath);
	}

	public void loadClassInstance() {

		try {
			// Convert File to a URL
			URL url = directory.toURI().toURL();
			URL[] urls = new URL[] { url };

			// Create a new class loader with the directory
			ClassLoader cl = new URLClassLoader(urls);
			Class<?> cls = cl.loadClass(classname);
			
			classinstance = cls.newInstance();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public Object getClassInstance() {
		return classinstance;
	}
	
	
}
