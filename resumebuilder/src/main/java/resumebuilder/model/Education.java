package resumebuilder.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Education {
    private String institution;
    private String degree;
    private String fieldOfStudy;
    private String startDate; // e.g., "Aug 2020"
    private String endDate;   // e.g., "June 2024"
    private String grade;     // Optional
}