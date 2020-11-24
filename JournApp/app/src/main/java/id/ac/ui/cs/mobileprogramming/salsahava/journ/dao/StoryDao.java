package id.ac.ui.cs.mobileprogramming.salsahava.journ.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.salsahava.journ.entity.Story;

@Dao
public interface StoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Story story);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(Story story);

    @Delete
    void delete(Story story);

    @Query("SELECT * FROM story_table")
    LiveData<List<Story>> getAllStories();

    @Query("SELECT * FROM story_table WHERE id=:id")
    LiveData<Story> getStoryById(String id);

    @Query("DELETE FROM story_table")
    void deleteAllStories();
}
