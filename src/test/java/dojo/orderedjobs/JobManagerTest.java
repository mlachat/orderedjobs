package dojo.orderedjobs;

import static org.junit.Assert.*;

import org.junit.Test;

import dojo.orderedjobs.JobManager;

public class JobManagerTest {

	
	@Test
	public void duplicateRegistrationIsOK() {
		JobManager jobManager = new JobManager();
		jobManager.register('a');
		jobManager.register('a');
	}
	
	@Test
	public void sortSingleElement() throws Exception {
		JobManager jobManager = new JobManager();
		jobManager.register('a');
		assertEquals("a", jobManager.sort());
	}
	
	@Test
	public void sortZweiAbhaengigeElemente() throws Exception {
		JobManager jobManager = new JobManager();
		jobManager.register('a','b');
		assertEquals("ba", jobManager.sort());
	}

	@Test
	public void duplicateRegistrationIsOK2() throws Exception {
		JobManager jobManager = new JobManager();
		jobManager.register('a');
		jobManager.register('a');
		assertEquals("a", jobManager.sort());
	}
	
	@Test
	public void dreierKetteMitAbhaengigkeiten() throws Exception {
		JobManager jobManager = new JobManager();
		jobManager.register('a', 'b');
		jobManager.register('a', 'c');
		jobManager.register('c', 'b');
		assertEquals("bca", jobManager.sort());
	}
	
	@Test(expected = Exception.class)
	public void sortThrowsExceptionOnCyclicDependency()throws Exception {
		JobManager jobManager = new JobManager();
		jobManager.register('b', 'a');
		jobManager.register('a', 'c');
		jobManager.register('c', 'b');
		assertEquals("bca", jobManager.sort());
	}
	

}
