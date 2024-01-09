package service.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import service.core.models.Job;
import java.util.Collections;
import java.util.List;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import service.models.UserActivity;
import service.repositories.UserRepository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

@RestController
public class JobFindController {

    private final UserRepository userRepository;
    RestTemplate template = new RestTemplate();

    //this is used to find service in eureka server
    @Autowired
    private DiscoveryClient discoveryClient;

    private List<String> serviceURLs;

    @PostConstruct
    private void initServiceUris() {
        int maxRetries = 10; // Maximum retries
        long retryDelay = 50000; // Delay between retries in milliseconds (5 seconds)
        int retryCount = 0;
    
        serviceURLs = new ArrayList<>();
    
        while (retryCount < maxRetries) {
            List<String> serviceIds = discoveryClient.getServices();
            serviceURLs.clear(); // Clear previous entries
    
            for (String serviceId : serviceIds) {
                List<ServiceInstance> instances = discoveryClient.getInstances(serviceId);
                for (ServiceInstance instance : instances) {
                    serviceURLs.add(instance.getUri().toString());
                }
            }
    
            if (serviceURLs.size() >= 5) {
                System.out.println("Found 5 or more services.");
                break;
            } else {
                System.out.println("Waiting for services to register. Attempt " + (retryCount + 1) + "/" + maxRetries);
                try {
                    Thread.sleep(retryDelay); // Wait before retrying
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt(); // Restore the interrupted status
                    throw new IllegalStateException("Interrupted while waiting for services to register", ex);
                }
                retryCount++;
            }
        }
    
        if (serviceURLs.size() < 5) {
            throw new IllegalStateException("Fewer than 5 services registered within the expected time.");
        }
    
        System.out.println("=========================================");
        System.out.println("Registered Services: " + serviceURLs);
        System.out.println("=========================================");
    

    }

    public JobFindController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(value="/findAllJobs", produces="application/json")
    public List<Job> findAllJobs() {
        List<Job> jobs = new ArrayList<>();

        for (String url : serviceURLs) {
            url=url+"/findJobs";
            ResponseEntity<List> response = template.getForEntity(url, List.class);
            if (response.getStatusCode().equals(HttpStatus.OK)) {
                jobs.addAll(response.getBody());
            }
        }
        return jobs;
    }

    @GetMapping(value="/findJobsBySkills", produces="application/json")
    public List<Job> findJobBySkills(@RequestParam("skills") List<String> skills) {
        List<Job> jobs = new ArrayList<>();

        for (String url : serviceURLs) {
            url=url+"/findJobsBySkills";
            UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);
            for (String skill : skills) {
                builder.queryParam("skills", skill);
            }

            ResponseEntity<List> response = template.getForEntity(builder.toUriString(), List.class);
            if (response.getStatusCode().equals(HttpStatus.OK)) {
                jobs.addAll(response.getBody());
            }
        }
        return jobs;
    }


    @GetMapping(value="/findJobsByTitle", produces="application/json")
    public List<Job> findJobByTitle(@RequestParam("title") String title) {
        System.out.println(title);
        List<Job> jobs = new ArrayList<>();

        for (String url : serviceURLs) {
            url=url+"/findJobsByTitle";
            UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);
            builder.queryParam("title", title);

            ResponseEntity<List> response = template.getForEntity(builder.toUriString(), List.class);
            if (response.getStatusCode().equals(HttpStatus.OK)) {
                jobs.addAll(response.getBody());
            }
        }
        return jobs;
    }

    @GetMapping(value="/getAppliedJobs/{email}", produces="application/json")
    public ResponseEntity<?> getAppliedJobs(@PathVariable("email") String email) {
        UserActivity userActivity = userRepository.findByEmail(email);
        if (userActivity == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("success", false));
        }
        return ResponseEntity.status(HttpStatus.OK).body(userActivity.getJobsApplied());
    }
}