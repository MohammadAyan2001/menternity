package menternity.mockinterview.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import menternity.mockinterview.constant.DeveloperRole;
import menternity.mockinterview.dto.AvailableSlotDTO;
import menternity.mockinterview.dto.SlotRequest;
import menternity.mockinterview.service.SlotService;

@RestController
@RequestMapping("/api")
public class SlotController {

    @Autowired
    private SlotService slotService;

    @PostMapping("/availableSlots")
    public List<AvailableSlotDTO> getAvailableSlots(@RequestBody SlotRequest request) {
        DeveloperRole role = request.getRole();
        return slotService.getAvailableSlotsByRole(role);
    }
}