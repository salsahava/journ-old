package id.ac.ui.cs.mobileprogramming.salsahava.journ.data;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import java.util.List;

public class StoryRepository {
    private StoryDao storyDao;
    private LiveData<List<Story>> allStories;

    public StoryRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        storyDao = db.getStoryDao();
        allStories = storyDao.getStories();
    }

    /**
     * Retrieve the story with the provided story id
     *
     * @return {@link LiveData<Story>}
     * */
    public LiveData<Story> getStory() {
        return storyDao.getStory();
    }

    /**
     * Retrieve the available stories
     *
     * @return the list of {@link Story}
     * */
    public LiveData<List<Story>> getAllStories() {
        return allStories;
    }

    /**
     * Adds a new {@link Story}
     *
     * @param stories the {@link Story to add}
     * */
    public void insertStory(@NonNull Story... stories) {
        AppDatabase.databaseWriteExecutor.execute(() -> storyDao.insert(stories));
    }

    /**
     * Update {@link Story} entry
     *
     * @param stories the {@link Story} to update
     * */
    public void updateStory(@NonNull Story... stories) {
        AppDatabase.databaseWriteExecutor.execute(() -> storyDao.update(stories));
    }

    /**
     * Delete a {@link Story} entry
     *
     * @param stories the {@link Story} to delete
     * */
    public void deleteStory(@NonNull Story... stories) {
        AppDatabase.databaseWriteExecutor.execute(() -> storyDao.delete(stories));
    }
}
