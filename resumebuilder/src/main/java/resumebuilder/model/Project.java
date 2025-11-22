package resumebuilder.model;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    private String title;
    private String description;
    private List<String> technologies;
    private String link; // Optional GitHub/Live link
}