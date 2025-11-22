package resumebuilder.model;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Experience {
    private String jobTitle;
    private String companyName;
    private String location;
    private String startDate; // e.g., "Jan 2023"
    private String endDate;   // "Present" or "May 2024"
    private List<String> responsibilities; // Bullet points
}