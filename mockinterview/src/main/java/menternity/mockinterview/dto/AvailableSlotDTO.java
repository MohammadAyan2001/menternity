package menternity.mockinterview.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
public class AvailableSlotDTO {
    private LocalDate date;
    private LocalTime startTime;
}