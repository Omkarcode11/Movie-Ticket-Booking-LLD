package model;

import exceptions.InvalidStateException;
import java.util.*;

public class Booking {

    private String id;
    private final Show show;
    private final List<Seat> seatsBooked;
    private final String user;
    private BookingStatus status;

    public Booking(String id, Show show, List<Seat> seatsBooked, String user, BookingStatus status) {
        this.id = id;
        this.show = show;
        this.seatsBooked = seatsBooked;
        this.user = user;
        this.status = BookingStatus.CREATED;

    }

    public String getId() {
        return id;
    }

    public Show getShow() {
        return show;
    }

    public List<Seat> getSeatsBooked() {
        return seatsBooked;
    }

    public String getUser() {
        return user;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public boolean isConfirmed(){
        return this.status == BookingStatus.CONFIRM;
    }

    public void confirmBooking(){
        if(this.status != BookingStatus.CREATED){
            throw new InvalidStateException("Booking state is " + this.status);
        }

        this.status = BookingStatus.CONFIRM;
    }

    public void expireBooking(){
        if(this.status != BookingStatus.CREATED){
            throw new InvalidStateException("Booking status is " + this.status);
        }

        this.status = BookingStatus.EXPIRED;
    }

}
