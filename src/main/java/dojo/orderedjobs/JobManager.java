package dojo.orderedjobs;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class JobManager {
	
	private JobRegistry registry = new JobRegistry();

	public void register(char a, char b) {
		registry.register(new Job(a), new Job(b));
	}

	public void register(char job) {
		registry.register(new Job(job));	
	}
	
	public String sort() throws Exception {
		Map<Job, List<Job>> jobs = registry.getRegistry();
		LinkedList<Job> sortierteJobs = new LinkedList<>(jobs.keySet());
		
		int i = 0;
		while( i < 1000000){
			i++;
			if(sortiere(sortierteJobs)) {
				return buildString(sortierteJobs);
			}
		}
		
		throw new Exception();
	}

	private String buildString(LinkedList<Job> sortierteJobs) {
		StringBuilder b = new StringBuilder();
		sortierteJobs.forEach(o -> b.append(o.getName()));
		return b.toString();
	}
	
	private boolean sortiere(LinkedList<Job> sortierteJobs) {

		boolean istSortiert = false;
		int indexJob = 0;
		Job depJob = null;
		for (Job job : sortierteJobs) {
			List<Job> dependsOnList = registry.getRegistry().get(job);
			for (Job dependentJob : dependsOnList) {
				if (sortierteJobs.indexOf(dependentJob) > sortierteJobs.indexOf(job)) {
					istSortiert = true;
					indexJob = sortierteJobs.indexOf(job);
					depJob = dependentJob;
					break;
				}
			}
		}
		if (istSortiert) {
			int i = sortierteJobs.indexOf(depJob);
			sortierteJobs.remove(i);
			sortierteJobs.add(indexJob, depJob);
		}
		
		return !istSortiert;
	}

	public String sortALT() {
		Map<Job, List<Job>> jobs = registry.getRegistry();
		LinkedList<Job> sortierteJobs = new LinkedList<>();
		
		
		for (Job job : jobs.keySet()) {
			List<Job> dependsOnList = jobs.get(job);
			int index = 0;
			for (Job dependentJob : dependsOnList) {
				index = Math.max(index, sortierteJobs.indexOf(dependentJob));
			}
			sortierteJobs.add(index, job);
		}
		
		return buildString(sortierteJobs);
	}
	
	public boolean hatWeitereAbhaengigkeit(){
		return false;
	}
}
