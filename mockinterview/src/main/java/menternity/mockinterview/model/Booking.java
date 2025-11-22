package menternity.mockinterview.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "bookings")
public class Booking {
    @Id
    private String id;
    private String studentEmail;
    private String googleMeetLink;
    private String slotId;
    private String teacherId;
    private LocalDateTime bookedAt;

    // Getters and Setters
}