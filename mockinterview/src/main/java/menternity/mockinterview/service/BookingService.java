package menternity.mockinterview.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import menternity.mockinterview.dto.BookingRequest;
import menternity.mockinterview.dto.BookingResponse;
import menternity.mockinterview.model.Booking;
import menternity.mockinterview.model.Slot;
import menternity.mockinterview.model.Teacher;
import menternity.mockinterview.repositoy.BookingRepository;
import menternity.mockinterview.repositoy.SlotRepository;
import menternity.mockinterview.repositoy.TeacherRepository;

@Service
public class BookingService {

    @Autowired
    private SlotRepository slotRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private BookingRepository bookingRepository;

    public BookingResponse bookInterview(BookingRequest request) {
        Slot slot = slotRepository.findFirstByDateAndStartTimeAndDeveloperRoleAndInterviewLevelAndIsBookedFalse(
                request.getDate(), request.getPreferredStartTime(),
                request.getDeveloperRole(), request.getInterviewLevel())
                .orElseThrow(() -> new RuntimeException("No available slot found."));

        Teacher teacher = teacherRepository.findFirstByAvailableRolesContainsAndIsAvailableTrue(request.getDeveloperRole())
                .orElseThrow(() -> new RuntimeException("No available teacher found."));

        slot.setBooked(true);
        slot.setTeacherId(teacher.getId());
        slotRepository.save(slot);

        String meetLink = "https://meet.google.com/fake-link"; // Replace with actual integration

        Booking booking = new Booking();
        booking.setStudentEmail(request.getStudentEmail());
        booking.setGoogleMeetLink(meetLink);
        booking.setSlotId(slot.getId());
        booking.setTeacherId(teacher.getId());
        booking.setBookedAt(LocalDateTime.now());
        bookingRepository.save(booking);

        BookingResponse response = new BookingResponse();
        response.setStudentEmail(request.getStudentEmail());
        response.setTeacherName(teacher.getName());
        response.setGoogleMeetLink(meetLink);
        response.setInterviewDateTime(LocalDateTime.of(slot.getDate(), slot.getStartTime()));

        return response;
    }
}

