package service;

import java.util.*;
import model.Seat;
import model.Show;
import provider.ISeatLockProvider;

public class SeatAvailabilityService {
    private  final BookingService bookingService;
    private  final  ISeatLockProvider seatLockProvider;

    public SeatAvailabilityService(BookingService bookingService, ISeatLockProvider seatLockProvider) {
        this.bookingService = bookingService;
        this.seatLockProvider = seatLockProvider;
    }

    public List<Seat> getAvailableSeats(Show show){
        List<Seat> allSeats = show.getScreen().getSeats();
        List<Seat> unavailableSeats = getUnavailableSeats(show);

        List<Seat> availableSeats = new ArrayList<>(allSeats);
        availableSeats.removeAll(unavailableSeats);
        return availableSeats;
    }

    private  List<Seat> getUnavailableSeats(Show show){
        List<Seat> unavailableSeats = bookingService.getBookedSeats(show);
        unavailableSeats.addAll(seatLockProvider.getLockedSeats(show));
        return unavailableSeats;
    }
}
