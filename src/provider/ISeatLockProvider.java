package provider;

import java.util.List;
import model.Seat;
import model.Show;

public interface ISeatLockProvider {

    void lockSeats(Show show, List<Seat> seat, String user);

    void unlockSeats(Show show, List<Seat> seat, String user);

    boolean validateLock(Show show, Seat seat, String user);

    List<Seat> getLockedSeats(Show show);
}
