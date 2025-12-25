package provider;
import exceptions.SeatTemporaryUnavailableException;
import java.util.*;
import model.*;

public class InMemorySeatLockProvider implements ISeatLockProvider {
    private final Integer lockTimeoutSeconds;
    private final Map<Show, Map<Seat, SeatLock>> locks;   


    public InMemorySeatLockProvider(Integer lockTimeoutSeconds) {
        this.lockTimeoutSeconds = lockTimeoutSeconds;
        this.locks = new HashMap<>();
    }

    @Override
    synchronized  public void lockSeats(Show show, List<Seat> seats, String user) {
        
        for(Seat seat : seats) {
            if(isSeatLocked(show, seat)) {
                try {
                    throw new SeatTemporaryUnavailableException("Seat " + seat.getId() + " is already locked for Show " + show.getId());
                } catch (SeatTemporaryUnavailableException ex) {
                    System.getLogger(InMemorySeatLockProvider.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                }
            }
        }

        for(Seat seat : seats) {
            lockSeat(show,seat,user);
        }
    }

    @Override
    public void unlockSeats(Show show, List<Seat> seats, String user){
        for(Seat seat : seats){
            if(validateLock(show, seat, user)){
                unlockSeats(show, seats, user);
            }
        }
    }

    @Override
    public boolean validateLock(Show show, Seat seat, String user){
        return isSeatLocked(show, seat) && locks.get(show).get(seat).getLockedBy().equals(user);
    }

    public boolean isSeatLocked(Show show, Seat seat) {
        return locks.containsKey(show) &&  locks.get(show).containsKey(seat) && 
               !locks.get(show).get(seat).isLockExpired();
    }

    @Override
    public List<Seat> getLockedSeats(Show show){
        if(!locks.containsKey(show)){
            return new ArrayList<>();
        }

        List<Seat> lockedSeats = new ArrayList<>();

        for(Seat seat : locks.get(show).keySet()){
            if(isSeatLocked(show, seat)){
                lockedSeats.add(seat);
            }
        }

        return lockedSeats;
    }

    public void unlockSeat(Show show, Seat seat){
        if(locks.containsKey(show)){
            locks.get(show).remove(seat);
        }

        locks.get(show).remove(seat);
    }

    public void lockSeat(Show show, Seat seat, String user) {
        locks.putIfAbsent(show, new HashMap<>());
        SeatLock seatLock = new SeatLock(seat, show, lockTimeoutSeconds, new Date(), user);
        locks.get(show).put(seat, seatLock);
    }

}