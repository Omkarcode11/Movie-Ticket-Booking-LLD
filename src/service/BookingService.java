package service;

import exceptions.BadRequestException;
import exceptions.NotFoundException;
import exceptions.SeatPermanentlyUnavailable;
import java.util.*;
import java.util.stream.Collectors;
import model.Booking;
import model.Seat;
import model.Show;
import provider.ISeatLockProvider;

public class BookingService {
    private  final Map<String, Booking> showBookings;
    private final ISeatLockProvider seatLockProvider;

    public BookingService(ISeatLockProvider seatLockProvider){
        this.seatLockProvider = seatLockProvider;
        this.showBookings = new HashMap<>();
    }

    public Booking getBooking(String bookingId){
        try {
            if(!showBookings.containsKey(bookingId)){
                throw new NotFoundException("booking not found");
            }

            return showBookings.get(bookingId);

        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
        return null;
    }

    public List<Booking> getAllBookings(Show show){
        List<Booking> res = new ArrayList<>();
        for(Booking booking : showBookings.values()){
            if(booking.getShow().equals(show)){
                res.add(booking);
            }
        }

        return res;
    }

    public Booking createBooking(String userId, Show show, List<Seat> seats) throws SeatPermanentlyUnavailable{           
            if(isAnySeatAlreadyBooked(show,seats)){
                throw new SeatPermanentlyUnavailable("seat is not avaiable");
            }
            return null;
    }

    public List<Seat> getBookedSeats(Show show){
        return getAllBookings(show).stream()
                .filter(Booking:: isConfirmed)
                .map(Booking:: getSeatsBooked)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    public void confirmBooking(Booking booking, String user) throws BadRequestException { 
        if (!booking.getUser().equals(user)) { 
            throw new BadRequestException("user is not matching with booking");
        }

        for(Seat seat : booking.getSeatsBooked()){
            if(!seatLockProvider.validateLock(booking.getShow(), seat, user)){
                throw new BadRequestException("Seat is Not available");
            }
        }

        booking.confirmBooking();
    }

    public boolean  isAnySeatAlreadyBooked(Show show, List<Seat> seats){
        List<Seat> bookedSeats = getBookedSeats(show);
        for(Seat seat : seats){
            if(bookedSeats.contains(seat)){
                return true;
            }
        }
        return false;
    }
}
