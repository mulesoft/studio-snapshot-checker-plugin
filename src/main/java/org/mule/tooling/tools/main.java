package org.mule.tooling.tools;

import java.io.IOException;
import java.util.ArrayList;
import org.apache.maven.plugin.logging.Log;

import org.mule.tooling.tools.JarFinder.JarFilter;

//Main to test locally the maven plugin.
public class main {

	public static void main(String[] args) throws IOException {
		
		
		ArrayList<String> ignoreJarList = new ArrayList<String>();
		ignoreJarList.add("Hello");

	 	Log log = getLog();
		CheckerResults resultsBuilt = JarFinder.checkJarSnapshotsBuilt("/Users/agustin.celentano/gitDevelop/studio-snapshot-checker-plugin/testFolder/org.mule.tooling.product/repository/plugins",new JarFilter(),log,ignoreJarList);
     	resultsBuilt.logTotalResults(log);
		
     	
     	
     	
		
	}

	private static Log getLog() {
		// TODO Auto-generated method stub
		return null;
	}

}
