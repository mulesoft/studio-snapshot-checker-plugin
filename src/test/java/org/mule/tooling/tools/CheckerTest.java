package org.mule.tooling.tools;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.logging.Log;
import org.junit.Assert;
import org.junit.Test;
import org.mule.tooling.tools.JarFinder.JarFilter;
import org.mule.tooling.tools.JarFinder.JarSnapshotFilter;

public class CheckerTest extends AbstractMojo {
 
	
	
	@Test
	public void checkJarNameInArrayOfIgnoreJarsTest() {
		
		String jarFileName = "fruit.org.mule.tooling.server.3.8.1.ee.jar";
		String jarFileName2 = "org.mule.tooling.server.jar";
	
		ArrayList<String> jarsToBeIgnored = new ArrayList<String>();
		jarsToBeIgnored.add("org.mule.tooling.server.3.8.1.ee");
		jarsToBeIgnored.add("org.mule.tooling.server.3.8.2.ee");
		jarsToBeIgnored.add("org.mule.tooling.server");
		Assert.assertTrue(JarFinder.checkJarNameInArrayOfIgnoreJars(jarFileName, jarsToBeIgnored));
		Assert.assertTrue(JarFinder.checkJarNameInArrayOfIgnoreJars(jarFileName2, jarsToBeIgnored));
	}
	
	@Test
	public void checkNoJarNameInArrayOfIgnoreJarsTest() {
		String jarFileName = "fruit.org.mule.tooling.server.3.8.3.ee.jar";
		String jarFileName2 = "tooling.server.3.8.3.ee.jar";
		ArrayList<String> jarsToBeIgnored = new ArrayList<String>();
		jarsToBeIgnored.add("org.mule.tooling.server.3.8.1.ee");
		jarsToBeIgnored.add("org.mule.tooling.server.3.8.2.ee");
		Assert.assertFalse(JarFinder.checkJarNameInArrayOfIgnoreJars(jarFileName, jarsToBeIgnored));
		Assert.assertFalse(JarFinder.checkJarNameInArrayOfIgnoreJars(jarFileName2, jarsToBeIgnored));
	}
	
	
	@Test
	public void checkJarNameInArrayOfIgnoreJarsEmptyListTest() {
		
		String jarFileName = "fruit.org.mule.tooling.server.3.8.3.ee.jar";
		String jarFileName2 = "tooling.server.3.8.3.ee.jar";
		ArrayList<String> jarsToBeIgnored = new ArrayList<String>();
		Assert.assertFalse(JarFinder.checkJarNameInArrayOfIgnoreJars(jarFileName, jarsToBeIgnored));
		Assert.assertFalse(JarFinder.checkJarNameInArrayOfIgnoreJars(jarFileName2, jarsToBeIgnored));
	}
	
	@Test
	public void checkJarNameInArrayOfIgnoreJarsNullListTest() {
		
		String jarFileName = "fruit.org.mule.tooling.server.3.8.3.ee.jar";
		String jarFileName2 = "tooling.server.3.8.3.ee.jar";
		ArrayList<String> jarsToBeIgnored = new ArrayList<String>();
		Assert.assertFalse(JarFinder.checkJarNameInArrayOfIgnoreJars(jarFileName, jarsToBeIgnored));
		Assert.assertFalse(JarFinder.checkJarNameInArrayOfIgnoreJars(jarFileName2, jarsToBeIgnored));
	}

	
	@Test
	public void getJarsAmountTest() {
		Log log = getLog();
		String dir = "src/main/resources";
		Collection<String> jarBuildList = JarFinder.getJars(dir,new JarFilter());
		Assert.assertEquals(5, jarBuildList.size());
	}
	
	@Test
	public void checkSnapshotInPropertiesFileTest() throws IOException {
		
		Path tempDir = Paths.get("src/main/resources");
		JarEntry jarEntry = new JarEntry("org.mule.tooling-SNAPSHOT.jar");
		Assert.assertFalse(JarFinder.checkSnapshotInPropertiesFile(tempDir,jarEntry,getLog()));
	}
	
	@Test
	public void checkSnapshotInPropertiesFileFailTest() throws IOException {
		Path tempDir = Paths.get("src/main/resources");
		JarEntry jarEntry = new JarEntry("WithoutSnaptshot.jar");
		Assert.assertFalse(JarFinder.checkSnapshotInPropertiesFile(tempDir,jarEntry,getLog()));
	}

	
	@Test
	public void searchForEntryTest() throws IOException {
		JarFile jarFile  = new JarFile("src/main/resources/org.mule.tooling-SNAPSHOT.jar");
		ArrayList<JarEntry> results = JarFinder.searchForEntry(jarFile, "META-INF/maven/.*pom.properties");
		Assert.assertEquals(1, results.size());	
	}
	
	@Test
	public void searchForEntryFailTest() throws IOException {
		JarFile jarFile  = new JarFile("src/main/resources/org.mule.tooling-SNAPSHOT.jar");
		ArrayList<JarEntry> results = JarFinder.searchForEntry(jarFile, "META-INFO/maven/.*pom.properties");
		Assert.assertEquals(null, results);	
	}
	
	
	
	@Test
	public void checkJarSnapshotsTest() throws IOException {
		String pluginBuildDirectory = "src/main/resources";
		ArrayList<String> jarsToBeIgnored = new ArrayList<String>();
		jarsToBeIgnored.add("jaxb-impl");
		jarsToBeIgnored.add("jaxb-xjc");
		CheckerResults results = new CheckerResults();
		results = JarFinder.checkJarSnapshots(pluginBuildDirectory,new JarSnapshotFilter(),getLog(),jarsToBeIgnored);
		Assert.assertEquals(1, results.getResults().size());
	}
	
	
	@Test
	public void checkJarSnapshotsBuiltTest() throws IOException {
		String pluginBuildDirectory = "src/main/resources";
		ArrayList<String> jarsToBeIgnored = new ArrayList<String>();
		jarsToBeIgnored.add("org.mule.tooling.server.3.8.1.ee");
		jarsToBeIgnored.add("org.mule.tooling.server.3.8.2.ee");
		CheckerResults results = new CheckerResults();
		results = JarFinder.checkJarSnapshotsBuilt(pluginBuildDirectory,new JarFilter(),getLog(),jarsToBeIgnored);
		Assert.assertEquals(2, results.getTotalResults().get(0).getResults().size());
	}

	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		// TODO Auto-generated method stub
	}
	
	
}
