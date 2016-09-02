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
import org.mule.tooling.tools.JarFinder.JarFilter;

import java.io.IOException;

/**
 * Goal which look for snapshot jars
 *
 * @goal check-jar-snapshots-built
 *
 * @phase verify
 */
public class MyMojo2
    extends AbstractMojo
{
    /**
     * Directory to look for snapshots Jars.
     * @parameter expression="${basedir}"
     * @required
     */
    private String builtFolder;
	
    @Override
    public void execute()
    
        throws MojoExecutionException
    {   	
    	 String dir = builtFolder;
    
//    	 try
//         {
//         	Log log = getLog();
//         	
//         	CheckerResults results = JarFinder.checkJarSnapshotsBuilt(dir,new JarFilter(),log);
//         	results.logTotalResults(log);
//         	if(results.hasResults()) {
//         		String message = "There are snapshot jars in this built project";
// 				throw new MojoExecutionException(message);
//         	}
//         	
//         }
//         catch ( IOException e )
//         {
//             throw new MojoExecutionException( "Error processing directory ", e );
//         }
       
        
    }
}
