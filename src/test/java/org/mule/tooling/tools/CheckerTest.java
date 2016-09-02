package org.mule.tooling.tools;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;



public class CheckerTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void checkJarNameInArrayOfIgnoreJarsTest() {
		
		String jarFileName = "fruit.org.mule.tooling.server.3.8.1.ee.jar";
		
		ArrayList<String> jarsToBeIgnored = new ArrayList<String>();
		jarsToBeIgnored.add("org.mule.tooling.server.3.8.1.ee");
		jarsToBeIgnored.add("org.mule.tooling.server.3.8.2.ee");
		Assert.assertTrue(JarFinder.checkJarNameInArrayOfIgnoreJars(jarFileName, jarsToBeIgnored));
	}
	
	
	@Test
	public void checkNoJarNameInArrayOfIgnoreJarsTest() {
		
		String jarFileName = "fruit.org.mule.tooling.server.3.8.3.ee.jar";
		ArrayList<String> jarsToBeIgnored = new ArrayList<String>();
		jarsToBeIgnored.add("org.mule.tooling.server.3.8.1.ee");
		jarsToBeIgnored.add("org.mule.tooling.server.3.8.2.ee");
		Assert.assertFalse(JarFinder.checkJarNameInArrayOfIgnoreJars(jarFileName, jarsToBeIgnored));
	}

		
}
