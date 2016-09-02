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
     * Plugin's directory to look for snapshots Jars.
     * @parameter expression="${project.build.directory}"
     * @required
     */
    private String pluginDirectory;
	
    /**
     * Project packaging
     * @parameter expression="${packagingEclipse}"
     * @required
     */
    private String packaging;
    
    /**
     * Plugin's build directory to look for snapshots Jars.
     * @parameter expression="${basedir}"
     * @required
     */
    private String pluginBuildDirectory;
    
    /**
     * Array of jars to ignore the checking.
     * @parameter expression="${ignoreJarList}"
     */
    private ArrayList<String> ignoreJarList;
 
    
        
    @Override
    public void execute()
        throws MojoExecutionException
    {
        try 
        {
        	Log log = getLog();
        	switch (packaging) {
        	 
        	        case "eclipse-plugin":
        	        	log.debug("Checking for jars SNAPSHOT in the plugin:"+ pluginBuildDirectory);
	        	        CheckerResults results = JarFinder.checkJarSnapshots(pluginDirectory,new JarSnapshotFilter(),log,ignoreJarList);
	        	        results.logResults(log);
	        	        
	        	        if(results.hasSnapshots()) {
	        	        	String message = "There are snapshot jars in this plugin!.";
	        				throw new MojoExecutionException(message);
	        	        }else
	        	        	log.debug("There is not any jar SNAPSHOT in the plugin:"+ pluginBuildDirectory);
        	        break;
      
        	        case "eclipse-repository":
        	        	CheckerResults resultsBuilt = JarFinder.checkJarSnapshotsBuilt(pluginBuildDirectory,new JarFilter(),log,ignoreJarList);
        	         	resultsBuilt.logTotalResults(log);
        	         	if(resultsBuilt.hasResults()) {
        	         		String message = "There are snapshot jars in the built project!.";
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
