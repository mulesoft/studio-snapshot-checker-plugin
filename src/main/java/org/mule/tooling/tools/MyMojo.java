package org.mule.tooling.tools;

/*
 * Copyright 2001-2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.logging.Log;
import org.mule.tooling.tools.JarFinder.JarSnapshotFilter;
import org.mule.tooling.tools.JarFinder.JarFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Goal which look for snapshot jars
 *
 * @goal check-jar-snapshots
 *
 * @phase verify
 */
public class MyMojo
    extends AbstractMojo
{
    /**
     * Directory to look for snapshots Jars.
     * @parameter expression="${project.build.directory}"
     * @required
     */
    private String searchDirectory;
	
    /**
     * Project packaging
     * @parameter expression="${packagingEclipse}"
     * @required
     */
    private String packaging;
    
    /**
     * Directory to look for snapshots Jars.
     * @parameter expression="${basedir}"
     * @required
     */
    private String builtFolder;
    
    /**
     * Directory to look for snapshots Jars.
     * @parameter expression="${myList}"
     */
    private ArrayList<String> myList;
 
    
        
    @Override
    public void execute()
        throws MojoExecutionException
    {
        String dir = searchDirectory;
        String packagingFolder = packaging;
        String dirFolder = builtFolder;
        ArrayList<String> ignoreJarCheck = new ArrayList<String>();
        Log log = getLog();
        try 
        {
      
        	switch (packagingFolder) {
        	 
        	        case "eclipse-plugin":
        	        	log.debug("Checking for jars SNAPSHOT in the plugin:"+ dirFolder);
	        	        CheckerResults results = JarFinder.checkJarSnapshots(dir,new JarSnapshotFilter(),log,ignoreJarCheck);
	        	        results.logResults(log);
	        	        
	        	        if(results.hasSnapshots()) {
	        	        	String message = "There are snapshot jars in this project's results";
	        				throw new MojoExecutionException(message);
	        	        }else
	        	        	log.debug("There is not any jar SNAPSHOT in the plugin:"+ dirFolder);
        	        break;
      
        	        case "eclipse-repository":
        	        	CheckerResults resultsBuilt = JarFinder.checkJarSnapshotsBuilt(dirFolder,new JarFilter(),log,ignoreJarCheck);
        	         	resultsBuilt.logTotalResults(log);
        	         	if(resultsBuilt.hasResults()) {
        	         		String message = "There are snapshot jars in this built project";
        	 				throw new MojoExecutionException(message);
        	         	}
        	        break;   
        	        default:
        	        	log.info("This pom does not have to be tested");
        	        break;
        	 }
        }
        catch ( IOException e )
        {
            throw new MojoExecutionException( "Error processing plugin directory ", e );
        }
        
    }
}
