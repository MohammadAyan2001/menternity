package menternity.mockinterview.config;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import menternity.mockinterview.service.SlotInitializer;

@Configuration
@EnableScheduling
public class SchedulerConfig {
}
