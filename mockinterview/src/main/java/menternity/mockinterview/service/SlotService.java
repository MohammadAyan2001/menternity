package menternity.mockinterview.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import menternity.mockinterview.constant.DeveloperRole;
import menternity.mockinterview.dto.AvailableSlotDTO;
import menternity.mockinterview.model.Slot;
import menternity.mockinterview.repositoy.SlotRepository;

@Service
public class SlotService {

    @Autowired
    private SlotRepository slotRepository;

    public List<AvailableSlotDTO> getAvailableSlotsByRole(DeveloperRole role) {
        List<Slot> slots = slotRepository.findByDeveloperRoleAndIsBookedFalse(role);
        return slots.stream()
                .map(slot -> new AvailableSlotDTO(slot.getDate(), slot.getStartTime()))
                .collect(Collectors.toList());
    }
}