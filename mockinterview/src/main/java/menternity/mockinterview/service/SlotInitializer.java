package menternity.mockinterview.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import menternity.mockinterview.constant.DeveloperRole;
import menternity.mockinterview.constant.InterviewLevel;
import menternity.mockinterview.model.Slot;
import menternity.mockinterview.repositoy.SlotRepository;

@Service
public class SlotInitializer {

    @Autowired
    private SlotRepository slotRepository;

    @PostConstruct
    public void seedInitialSlots() {
        LocalDate today = LocalDate.now();
        for (int i = 0; i < 3; i++) {
            LocalDate date = today.plusDays(i);
            generateSlotsForDate(date);
        }
    }

    public void generateSlotsForDate(LocalDate date) {
        List<LocalTime> times = getTimeSlotsForDay(date.getDayOfWeek());

        for (DeveloperRole role : DeveloperRole.values()) {
            for (InterviewLevel level : InterviewLevel.values()) {
                for (LocalTime time : times) {
                    boolean exists = slotRepository.existsByDateAndStartTimeAndDeveloperRoleAndInterviewLevel(
                            date, time, role, level
                    );
                    if (!exists) {
                        Slot slot = new Slot();
                        slot.setDate(date);
                        slot.setStartTime(time);
                        slot.setDeveloperRole(role);
                        slot.setInterviewLevel(level);
                        slot.setBooked(false);
                        slotRepository.save(slot);
                    }
                }
            }
        }
    }

    private List<LocalTime> getTimeSlotsForDay(DayOfWeek dayOfWeek) {
        List<LocalTime> slots = new ArrayList<>();

        // Weekends: add 2 PM - 5 PM
        if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
            for (int i = 14; i < 17; i++) {
                slots.add(LocalTime.of(i, 0));
            }
        }

        // Daily: add 7 PM - 10 PM
        for (int i = 19; i < 22; i++) {
            slots.add(LocalTime.of(i, 0));
        }

        return slots;
    }
}
