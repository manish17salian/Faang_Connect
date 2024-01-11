package service.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.core.models.Job;
import service.core.repositories.JobRepository;
import java.util.ArrayList;
import java.util.List;

@RestController
public class AzJobFindController {
    private final JobRepository jobRepository;
    public AzJobFindController(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @GetMapping(value="/findJobs", produces="application/json")
    public ResponseEntity<ArrayList<Job>> findAllJobs() {
        return ResponseEntity.status(HttpStatus.OK).body((ArrayList<Job>) jobRepository.findAll());
    }

    @GetMapping(value="/findJobsBySkills", produces="application/json")
    public ResponseEntity<ArrayList<Job>> findJobsBySkills(@RequestParam("skills") List<String> skills) {
        List<Job> jobsBySkill = jobRepository.findBySkillsIn(skills);
        for (Job job : jobsBySkill) {
            if (!job.getSkills().containsAll(skills)) {
                jobsBySkill.remove(job);
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body((ArrayList<Job>) jobsBySkill);
    }

    @GetMapping(value="/findJobsByTitle", produces="application/json")
    public ResponseEntity<ArrayList<Job>> findJobsByTitle(@RequestParam("title") String title) {
        List<Job> jobsByTitle = jobRepository.findByTitleContainingIgnoreCase(title);
        for (Job job : jobsByTitle) {
            System.out.println(job + "Jobs here");
        }
        return ResponseEntity.status(HttpStatus.OK).body((ArrayList<Job>) jobsByTitle);
    }
}