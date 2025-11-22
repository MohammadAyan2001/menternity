package menternity.mockinterview.dto;


import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import menternity.mockinterview.constant.DeveloperRole;
import menternity.mockinterview.constant.InterviewLevel;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingRequest {
    private String studentEmail;
    private DeveloperRole developerRole;
    private InterviewLevel interviewLevel;
    private LocalDate date;
    private LocalTime preferredStartTime;
}
