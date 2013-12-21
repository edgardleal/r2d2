/**
 * 
 */
package br.com.r2d2.infra;

import java.io.File;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;

/**
 * Última compilação: 01/01/2013 00:00
 * 
 * @author Edgard Leal
 * @since 26/11/2013 14:18:49
 * @version 0.0.0.0
 * 
 */
@Component
@ApplicationScoped
public class Config {

	private static String HOME = System.getenv("HOME");
	private static String M2_HOME = String.format("%s/.m2", HOME);
	private static String REMOTE_SERVER = "http://repo1.maven.org/maven2/";
	private static String REPOSITORY_HOME = String.format("%s/repository",
			M2_HOME);

	private static String debug = System.getenv("debug");

	public File repositoryFolder() {
		return new File(REPOSITORY_HOME);
	}

	public static boolean isDebug() {
		return debug != null && debug.equalsIgnoreCase("true");
	}

	public static String getRemoveServer() {
		return REMOTE_SERVER;
	}
}
