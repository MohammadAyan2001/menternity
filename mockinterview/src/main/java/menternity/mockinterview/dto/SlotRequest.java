package menternity.mockinterview.dto;


import lombok.Data;
import menternity.mockinterview.constant.DeveloperRole;

@Data
public class SlotRequest {
    private DeveloperRole role;
}