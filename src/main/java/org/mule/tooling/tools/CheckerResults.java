package org.mule.tooling.tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.maven.plugin.logging.Log;

public class CheckerResults {

	private Map<String, List<String>> results = new HashMap<String,  List<String>>();
	private List<CheckerResults> totalResults = new ArrayList<CheckerResults>();

	public void addResult(String jarFileName, String jarEntryName) {	
		if(results.containsKey(jarFileName)){
			List<String> currentList = results.get(jarFileName);
			currentList.add(jarEntryName);
			results.put(jarFileName, currentList);
		}else{
			List<String> newList = new ArrayList<>();
			newList.add(jarEntryName);
			results.put(jarFileName, newList);		
		}
		
	}
		

	public Map<String, List<String>> getResults() {
		return results;
	}

	public List<CheckerResults> getTotalResults() {
		return totalResults;
	} 
	public void logTotalResults(Log log) {
		if (this.hasResults()) {
			for (CheckerResults result : totalResults ){
				result.logResults(log);
			}			
		}
	}
	
	public void logResults(Log log) {
		if (this.hasSnapshots()) {
			for (Map.Entry<String, List<String>> entry : results.entrySet()) {
				log.error("(" + entry.getKey() + ")" + " --- " + entry.getValue());				
			}
		}
	}

	public boolean hasResults(){
		return !totalResults.isEmpty();
	}
	public boolean hasSnapshots() {
		return !results.isEmpty();
	}


	public void addCheckResult(CheckerResults resultsEachFolder) {
		totalResults.add(resultsEachFolder);
	}
}
