package org.mule.tooling.tools;
import org.apache.maven.plugin.logging.Log;
import org.mule.tooling.tools.JarFinder.JarFilter;
import org.mule.tooling.tools.JarFinder.JarSnapshotFilter;

public class main {

	public static void main(String[] args) throws Exception {
		 
		String dir = "/Users/agustincelentano/Desktop/testmavenplugin/org.mule.tooling.studio.product";
		String dirDos = "/Users/agustincelentano/Desktop/testmavenplugin/son2/target";
		Log log = new Log() {
			
			@Override
			public void warn(CharSequence arg0, Throwable arg1) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void warn(Throwable arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void warn(CharSequence arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public boolean isWarnEnabled() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean isInfoEnabled() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean isErrorEnabled() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean isDebugEnabled() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public void info(CharSequence arg0, Throwable arg1) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void info(Throwable arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void info(CharSequence arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void error(CharSequence arg0, Throwable arg1) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void error(Throwable arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void error(CharSequence arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void debug(CharSequence arg0, Throwable arg1) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void debug(Throwable arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void debug(CharSequence arg0) {
				// TODO Auto-generated method stub
				
			}
		};
		CheckerResults resultsBuilt = JarFinder.checkJarSnapshotsBuilt(dir, new JarFilter(), log);
		resultsBuilt.logTotalResults(log);
		System.out.println("---------------------");
		CheckerResults results = JarFinder.checkJarSnapshots(dirDos,new JarSnapshotFilter(),log);
		results.logResults(log);
	}
		
	}


