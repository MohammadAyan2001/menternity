package menternity.mockinterview.repositoy;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import menternity.mockinterview.constant.DeveloperRole;
import menternity.mockinterview.model.Teacher;

public interface TeacherRepository extends MongoRepository<Teacher, String> {
    Optional<Teacher> findFirstByAvailableRolesContainsAndIsAvailableTrue(DeveloperRole developerRole);
    
}