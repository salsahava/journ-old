package id.ac.ui.cs.mobileprogramming.salsahava.journ.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.salsahava.journ.dao.StoryDao;
import id.ac.ui.cs.mobileprogramming.salsahava.journ.database.JournDatabase;
import id.ac.ui.cs.mobileprogramming.salsahava.journ.entity.Story;

public class StoryRepository {
    private StoryDao storyDao;
    private LiveData<List<Story>> allStories;

    public StoryRepository(Application application) {
        JournDatabase journDatabase = JournDatabase.getInstance(application);
        storyDao = journDatabase.getStoryDao();
        allStories = storyDao.getAllStories();
    }

    public void insert(Story story) {
        new InsertStoryAsyncTask(storyDao).execute(story);
    }

    public void update(Story story) {
        new UpdateStoryAsyncTask(storyDao).execute(story);
    }

    public void delete(Story story) {
        new DeleteStoryAsyncTask(storyDao).execute(story);
    }

    public void deleteAll() {
        new DeleteAllStoriesAsyncTask(storyDao).execute();
    }

    public LiveData<Story> getStoryById(String id) {
        return storyDao.getStoryById(id);
    }

    public LiveData<List<Story>> getAllStories() {
        return storyDao.getAllStories();
    }

    private static class InsertStoryAsyncTask extends AsyncTask<Story, Void, Void> {
        private StoryDao storyDao;

        private InsertStoryAsyncTask(StoryDao storyDao) {
            this.storyDao = storyDao;
        }

        @Override
        protected Void doInBackground(Story... stories) {
            storyDao.insert(stories[0]);
            return null;
        }
    }

    private static class UpdateStoryAsyncTask extends AsyncTask<Story, Void, Void> {
        private StoryDao storyDao;

        private UpdateStoryAsyncTask(StoryDao storyDao) {
            this.storyDao = storyDao;
        }

        @Override
        protected Void doInBackground(Story... stories) {
            storyDao.update(stories[0]);
            return null;
        }
    }

    private static class DeleteStoryAsyncTask extends AsyncTask<Story, Void, Void> {
        private StoryDao storyDao;

        private DeleteStoryAsyncTask(StoryDao storyDao) {
            this.storyDao = storyDao;
        }

        @Override
        protected Void doInBackground(Story... stories) {
            storyDao.delete(stories[0]);
            return null;
        }
    }

    private static class DeleteAllStoriesAsyncTask extends AsyncTask<Void, Void, Void> {
        private StoryDao storyDao;

        private DeleteAllStoriesAsyncTask(StoryDao storyDao) {
            this.storyDao = storyDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            storyDao.deleteAllStories();
            return null;
        }
    }
}
