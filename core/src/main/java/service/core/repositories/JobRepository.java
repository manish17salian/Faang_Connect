package service.core.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import service.core.models.Job;
import java.util.List;
import java.util.Optional;

public interface JobRepository extends MongoRepository<Job, String> {
    List<Job> findBySkillsIn(List<String> skills);
    Optional<Job> findById(String id);

    List<Job> findByTitleContainingIgnoreCase(String title);
}
