package model;

import java.util.Date;

public class Show {
    private final String id;
    private final Movie movie;
    private final Screen screen;
    private final Date showTime;
    private final Integer durationMinutes;

    public Show(String id, Movie movie, Screen screen, Date showTime, Integer durationMinutes) {
        this.id = id;
        this.movie = movie;
        this.screen = screen;
        this.showTime = showTime;
        this.durationMinutes = durationMinutes;
    }

    public String getId() {
        return id;
    }

    public Movie getMovie() {
        return movie;
    }

    public Screen getScreen() {
        return screen;
    }

    public Date getShowTime() {
        return showTime;
    }

    public Integer getDurationMinutes() {
        return durationMinutes;
    }
}
