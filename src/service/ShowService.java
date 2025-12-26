package service;

import exceptions.NotFoundException;
import exceptions.ScreenAlreadyOccupiedException;
import java.util.*;
import model.*;

public class ShowService {

    private final Map<String, Show> shows;

    public ShowService() {
        this.shows = new HashMap<>();
    }

    public Show getShow(String showId) throws NotFoundException {
        if (!shows.containsKey(showId)) {
            throw new NotFoundException("Show not found");
        }

        return shows.get(showId);
    }

    public Show createShow(Movie movie, Screen screen, Date startTime, Integer durationTimeSeconds) throws ScreenAlreadyOccupiedException {
        if (checkIfShowCreationAllowed(screen, startTime, durationTimeSeconds)) {
            throw new ScreenAlreadyOccupiedException("Show creation is not allowed");
        }

        String showId = UUID.randomUUID().toString();
        Show show = new Show(showId, movie, screen, startTime, durationTimeSeconds);
        this.shows.put(showId, show);
        return show;
    }

    public List<Show> getShowsForScreens(Screen screen){
        List<Show> res = new ArrayList<>();
        for(Show show : shows.values()){
            if(show.getScreen().equals(screen)){
                res.add(show);
            }
        }
        return res;
    }

    public boolean checkIfShowCreationAllowed(Screen screen, Date startTime, Integer durationInSecond) {
        // TODO: implment this this method will retuen wheter the screen is free at a perticual time for spefic duaration this fucnti onwill be help in checki whtether the hso can be scheudle in that slow or not 
        return true;
    }

}
