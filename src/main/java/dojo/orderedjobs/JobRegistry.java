package dojo.orderedjobs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JobRegistry {
	private Map<Job, List<Job>> registry = new HashMap<>();
	
	public void register(Job job) {
		List<Job> dependendOnList = Optional.ofNullable(registry.get(job)).orElse(new ArrayList<>());
		registry.put(job, dependendOnList);
	}

	public void register(Job job, Job job2) {
		register(job);
		register(job2);
		registry.get(job).add(job2);
	}

	public Map<Job, List<Job>> getRegistry() {
		return registry;
	}
}
