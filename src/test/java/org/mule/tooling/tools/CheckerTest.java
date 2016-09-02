package org.mule.tooling.tools;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

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
		
		System.out.println(JarFinder.checkJarNameInArrayOfIgnoreJars(jarFileName, jarsToBeIgnored));
		Assert.assertEquals(true,JarFinder.checkJarNameInArrayOfIgnoreJars(jarFileName, jarsToBeIgnored));
	}

}
