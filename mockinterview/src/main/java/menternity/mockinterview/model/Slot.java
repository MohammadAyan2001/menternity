package menternity.mockinterview.model;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import menternity.mockinterview.constant.DeveloperRole;
import menternity.mockinterview.constant.InterviewLevel;

import java.time.LocalDate;
import java.time.LocalTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "slots")
public class Slot {
    @Id
    private String id;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private boolean isBooked;
    private DeveloperRole developerRole;
    private InterviewLevel interviewLevel;
    private String teacherId;
}
