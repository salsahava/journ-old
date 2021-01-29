package id.ac.ui.cs.mobileprogramming.salsahava.journ.util;

import id.ac.ui.cs.mobileprogramming.salsahava.journ.entity.Story;
import id.ac.ui.cs.mobileprogramming.salsahava.journ.entity.Trip;

public interface Communicator {
    public void displayDetails(Story story);
    public void displayTripDetails(Trip trip);
}
