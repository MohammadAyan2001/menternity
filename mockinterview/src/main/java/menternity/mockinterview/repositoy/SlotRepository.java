package menternity.mockinterview.repositoy;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import menternity.mockinterview.constant.DeveloperRole;
import menternity.mockinterview.constant.InterviewLevel;
import menternity.mockinterview.model.Slot;

public interface SlotRepository extends MongoRepository<Slot, String> {
    Optional<Slot> findFirstByDateAndStartTimeAndDeveloperRoleAndInterviewLevelAndIsBookedFalse(
        LocalDate date, LocalTime startTime, DeveloperRole developerRole, InterviewLevel interviewLevel);

    boolean existsByDateAndStartTimeAndDeveloperRoleAndInterviewLevel(LocalDate date, LocalTime time,
            DeveloperRole role, InterviewLevel level);

                List<Slot> findByDeveloperRoleAndIsBookedFalse(DeveloperRole role);

}
