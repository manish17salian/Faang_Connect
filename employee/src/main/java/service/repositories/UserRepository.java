package service.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import service.models.UserActivity;

public interface UserRepository extends MongoRepository<UserActivity, String> {
    UserActivity findByEmail(String email);
}
