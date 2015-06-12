package org.arquillian.container.chameleon.controller;

import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandlerFactory;

public class ChameleonURLClassLoader extends URLClassLoader {

	public ChameleonURLClassLoader(URL[] urls, ClassLoader parent) {
		super(urls, parent);
	}
    
	public ChameleonURLClassLoader(URL[] urls) {
		super(urls);
	}

	public ChameleonURLClassLoader(URL[] urls, ClassLoader parent, URLStreamHandlerFactory factory) {
		super(urls, parent, factory);
	}

    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
            synchronized (getClassLoadingLock(name)) {
                // First, check if the class has already been loaded
                Class<?> c = findLoadedClass(name);
                if (c == null) {
                	try {
	                    // Not loaded - loading from the current URL loader
	                    c = findClass(name);
                	} catch (Exception e) {
                		// ignoring all other work is passed to standard loader
                	}
                }
            }
            return super.loadClass(name, resolve);
        }
}