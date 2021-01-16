package id.ac.ui.cs.mobileprogramming.salsahava.journ.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.salsahava.journ.entity.Trip;

@Dao
public interface TripDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Trip trip);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(Trip trip);

    @Delete
    void delete(Trip trip);

    @Query("SELECT * FROM trip_table")
    LiveData<List<Trip>> getAllTrips();

    @Query("SELECT * FROM trip_table WHERE id=:id")
    LiveData<Trip> getTripById(String id);

    @Query("DELETE FROM trip_table")
    void deleteAllTrips();
}
