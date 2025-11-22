package menternity.mockinterview.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import menternity.mockinterview.dto.BookingRequest;
import menternity.mockinterview.dto.BookingResponse;
import menternity.mockinterview.service.BookingService;

@RestController
@RequestMapping("/api")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/bookings")
    public ResponseEntity<BookingResponse> bookSlot(@RequestBody BookingRequest request) {
        BookingResponse response = bookingService.bookInterview(request);
        return ResponseEntity.ok(response);
    }
}

