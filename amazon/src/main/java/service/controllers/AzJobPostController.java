package service.controllers;

import java.util.Collections;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import service.core.models.Job;
import service.core.repositories.JobRepository;

@RestController
public class AzJobPostController {
    private final JobRepository jobRepository;
    public AzJobPostController(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }
    @PostMapping(value="/postJob", consumes="application/json")
    public ResponseEntity<?> createJob(
            @RequestBody Job info) {
        try {
            jobRepository.save(info);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(Collections.singletonMap("success", true));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("success", false));
        }
    }
}