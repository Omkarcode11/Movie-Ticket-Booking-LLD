package model;

public class Seat {
    
    private final String id;
    private final String row;
    private final int seat;

    public Seat(String id, String row, int seat) {
        this.id = id;
        this.row = row;
        this.seat = seat;
    }

    public String getId() {
        return id;
    }

    public String getRow() {
        return row;
    }

    public int getSeat() {
        return seat;
    }

}
