package service.notification.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Notification {
	@JsonProperty("jobId")
	private String jobId;
	 @JsonProperty("companyName")
	private String companyName;
	 @JsonProperty("email")
	private String email;
	
	public Notification() {
        // Default constructor
    }

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	@Override
    public String toString() {
        return "Notification{" +
                "jobId='" + jobId + '\'' +
                ", email='" + email + '\'' +
                ", companyName='" + companyName + '\'' +
                '}';
    }

}
