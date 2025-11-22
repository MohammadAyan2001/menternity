package menternity.mockinterview.repositoy;

import org.springframework.data.mongodb.repository.MongoRepository;

import menternity.mockinterview.model.Booking;

public interface BookingRepository extends MongoRepository<Booking, String> {
}