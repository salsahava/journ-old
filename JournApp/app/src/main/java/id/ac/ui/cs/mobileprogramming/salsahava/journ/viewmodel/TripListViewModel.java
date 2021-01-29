package id.ac.ui.cs.mobileprogramming.salsahava.journ.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.salsahava.journ.entity.Trip;
import id.ac.ui.cs.mobileprogramming.salsahava.journ.repository.TripRepository;

public class TripListViewModel extends AndroidViewModel {
    private static final String TAG = TripListViewModel.class.getSimpleName();
    private LiveData<List<Trip>> trips;
    private TripRepository tripRepository;


    public TripListViewModel(@NonNull Application application) {
        super(application);
        tripRepository = new TripRepository(application);
        trips = tripRepository.getAllTrips();
    }

    public LiveData<List<Trip>> getAllTrips() {
        return trips;
    }
}