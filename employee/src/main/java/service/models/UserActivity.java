package service.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Document(collection = "UserActivity")
public class UserActivity {
    @Id
    private String email;
    @Field("jobsApplied")
    private List<Map<String, Object>> jobsApplied;

    public UserActivity(String email, List<Map<String, Object>> jobsApplied) {
        this.email = email;
        this.jobsApplied = jobsApplied != null ? jobsApplied : new ArrayList<>();
    }

    public void setJobsApplied(List<Map<String, Object>> jobsApplied) {
        this.jobsApplied = jobsApplied;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserActivity() {}

    public String getEmail() {
        return email;
    }

    public List<Map<String, Object>> getJobsApplied() {
        return jobsApplied != null ? jobsApplied : new ArrayList<>();
    }
}
