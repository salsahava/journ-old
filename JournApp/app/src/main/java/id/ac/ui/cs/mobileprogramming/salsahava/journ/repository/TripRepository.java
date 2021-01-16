package id.ac.ui.cs.mobileprogramming.salsahava.journ.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.salsahava.journ.dao.TripDao;
import id.ac.ui.cs.mobileprogramming.salsahava.journ.database.JournDatabase;
import id.ac.ui.cs.mobileprogramming.salsahava.journ.entity.Trip;

public class TripRepository {
    private TripDao tripDao;
    private LiveData<List<Trip>> allTrips;

    public TripRepository(Application application) {
        JournDatabase journDatabase = JournDatabase.getInstance(application);
        tripDao = journDatabase.getTripDao();
        allTrips = tripDao.getAllTrips();
    }

    public void insert(Trip trip) {
        new InsertTripAsyncTask(tripDao).execute(trip);
    }

    public void update(Trip trip) {
        new UpdateTripAsyncTask(tripDao).execute(trip);
    }

    public void delete(Trip trip) {
        new DeleteTripAsyncTask(tripDao).execute(trip);
    }

    public void deleteAll() {
        new DeleteAllTripsAsyncTask(tripDao).execute();
    }

    public LiveData<Trip> getTripById(String id) {
        return tripDao.getTripById(id);
    }

    public LiveData<List<Trip>> getAllTrips() {
        return tripDao.getAllTrips();
    }

    private static class InsertTripAsyncTask extends AsyncTask<Trip, Void, Void> {
        private TripDao tripDao;

        private InsertTripAsyncTask(TripDao tripDao) {
            this.tripDao = tripDao;
        }

        @Override
        protected Void doInBackground(Trip... trips) {
            tripDao.insert(trips[0]);
            return null;
        }
    }

    private static class UpdateTripAsyncTask extends AsyncTask<Trip, Void, Void> {
        private TripDao tripDao;

        private UpdateTripAsyncTask(TripDao tripDao) {
            this.tripDao = tripDao;
        }

        @Override
        protected Void doInBackground(Trip... trips) {
            tripDao.update(trips[0]);
            return null;
        }
    }

    private static class DeleteTripAsyncTask extends AsyncTask<Trip, Void, Void> {
        private TripDao tripDao;

        private DeleteTripAsyncTask(TripDao tripDao) {
            this.tripDao = tripDao;
        }

        @Override
        protected Void doInBackground(Trip... trips) {
            tripDao.delete(trips[0]);
            return null;
        }
    }

    private static class DeleteAllTripsAsyncTask extends AsyncTask<Void, Void, Void> {
        private TripDao tripDao;

        private DeleteAllTripsAsyncTask(TripDao tripDao) {
            this.tripDao = tripDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            tripDao.deleteAllTrips();
            return null;
        }
    }
}
