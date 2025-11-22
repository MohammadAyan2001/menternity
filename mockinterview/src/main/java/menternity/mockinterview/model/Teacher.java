package menternity.mockinterview.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import menternity.mockinterview.constant.DeveloperRole;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "teachers")
public class Teacher {
    @Id
    private String id;
    private String name;
    private String email;
    private List<DeveloperRole> availableRoles;
    private boolean isAvailable;

    // Getters and Setters
}