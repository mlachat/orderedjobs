package dojo.orderedjobs;

public class Job {
	
	private char name;

	public Job(char c) {
		this.name = c;
	}

	public char getName() {
		return name;
	}

	public void setName(char name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + name;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Job other = (Job) obj;
		if (name != other.name)
			return false;
		return true;
	}
}
