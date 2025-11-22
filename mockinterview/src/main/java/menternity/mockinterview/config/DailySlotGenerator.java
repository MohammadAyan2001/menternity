package menternity.mockinterview.config;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import menternity.mockinterview.service.SlotInitializer;

@Service
public class DailySlotGenerator {

    @Autowired
    private SlotInitializer slotInitializer;

    @Scheduled(cron = "0 0 0 * * ?") // Every midnight
    public void generateNextDaySlots() {
        LocalDate nextDay = LocalDate.now().plusDays(1);
        slotInitializer.generateSlotsForDate(nextDay);
    }
}
