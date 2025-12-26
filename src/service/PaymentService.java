package service;
import exceptions.BadRequestException;
import  java.util.*;
import model.Booking;
import provider.ISeatLockProvider;

public class PaymentService {
    Map<Booking, Integer> bookingFailures;
    private final Integer allowedRetries;
    private final ISeatLockProvider seatLockProvider;
    
    public PaymentService(Integer allowedRetries,
            ISeatLockProvider seatLockProvider) {
        this.bookingFailures = new HashMap<>();
        this.allowedRetries = allowedRetries;
        this.seatLockProvider = seatLockProvider;
    }

    public void processPaymentFailed(Booking booking , String user) throws  BadRequestException {
        if(!booking.getUser().equals(user)){
            throw new BadRequestException("user is not matching booking user: " + booking.getUser() + " Payment user is : " + user);
        }

        if(!bookingFailures.containsKey(booking)){
            bookingFailures.put(booking,0);
        }

        Integer currentFailureCount = bookingFailures.get(booking);
        Integer newFailureCount = currentFailureCount+1;
        bookingFailures.put(booking,newFailureCount);
        if(newFailureCount > allowedRetries){
            seatLockProvider.unlockSeats(booking.getShow(), booking.getSeatsBooked(), booking.getUser());
        }
    }

    
}
