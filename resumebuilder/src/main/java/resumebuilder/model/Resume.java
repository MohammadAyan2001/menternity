package resumebuilder.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("resumes")
public class Resume {
    @Id
    private String id;

    @NotBlank(message = "Full name is required")
    private String fullName;

    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Phone number is required")
    @Size(min = 10, max = 15, message = "Phone number should be valid")
    private String phone;
    private String linkedIn;
    private String github;
     // New fields
    private String city;
    private String state;
    private String summary;
    private List<String> skills;
     private List<String> softSkills;
    private List<Education> education;
    private List<Experience> experience;
    private List<Project> projects;
    private List<String> achievements;
}
