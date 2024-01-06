package service.notification.models;

public class JobApplicationRequest {
    private String jobId;
    private String companyName;
    private String email;

    public JobApplicationRequest(String jobId, String companyName, String email) {
        this.jobId = jobId;
        this.companyName = companyName;
        this.email = email;
    }

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
    
    
}
