package id.ac.ui.cs.mobileprogramming.salsahava.journ.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface StoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insert(Story... stories);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    int update(Story... stories);

    @Delete
    int delete(Story... stories);

    @Query("SELECT * FROM story WHERE id = id")
    LiveData<Story> getStory();

    @Query("SELECT * FROM story")
    LiveData<List<Story>> getStories();
}
