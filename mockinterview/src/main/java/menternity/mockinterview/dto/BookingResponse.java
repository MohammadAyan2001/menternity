package menternity.mockinterview.dto;



import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingResponse {
    private String studentEmail;
    private String teacherName;
    private String googleMeetLink;
    private LocalDateTime interviewDateTime;
}
