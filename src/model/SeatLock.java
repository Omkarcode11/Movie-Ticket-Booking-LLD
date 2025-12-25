package model;

import java.time.Instant;
import java.util.Date;

public class SeatLock {
    private Seat seat;
    private Show show;
    private Integer timeoutInSeconds;
    private Date lockTime;
    private String lockedBy;

    public SeatLock(Seat seat, Show show, Integer timeoutInSeconds, Date lockTime, String lockedBy) {
        this.seat = seat;
        this.show = show;
        this.timeoutInSeconds = timeoutInSeconds;
        this.lockTime = lockTime;
        this.lockedBy = lockedBy;
    }

    public Seat getSeat() {
        return seat;
    }
    public void setSeat(Seat seat) {
        this.seat = seat;
    }
    public Show getShow() {
        return show;
    }
    public void setShow(Show show) {
        this.show = show;
    }
    public Integer getTimeoutInSeconds() {
        return timeoutInSeconds;
    }
    public void setTimeoutInSeconds(Integer timeoutInSeconds) {
        this.timeoutInSeconds = timeoutInSeconds;
    }
    public Date getLockTime() {
        return lockTime;
    }
    public void setLockTime(Date lockTime) {
        this.lockTime = lockTime;
    }
    public String getLockedBy() {
        return lockedBy;
    }
    public void setLockedBy(String lockedBy) {
        this.lockedBy = lockedBy;
    }

    public boolean isLockExpired() {
        final Instant lockInstant = lockTime.toInstant().plusSeconds(timeoutInSeconds);
        final Instant now = Instant.now();
        return now.isAfter(lockInstant);
    }
}
