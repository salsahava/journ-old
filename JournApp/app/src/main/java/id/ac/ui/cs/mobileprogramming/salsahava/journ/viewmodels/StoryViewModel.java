package id.ac.ui.cs.mobileprogramming.salsahava.journ.viewmodels;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.salsahava.journ.data.Story;
import id.ac.ui.cs.mobileprogramming.salsahava.journ.data.StoryRepository;

public class StoryViewModel extends ViewModel {
    private StoryRepository repository;
    private final LiveData<List<Story>> allStories;

    public StoryViewModel (Application application) {
        super();
        repository = new StoryRepository(application);
        allStories = repository.getAllStories();
    }

    public LiveData<List<Story>> getAllStories() {
        return allStories;
    }

    public void insert(Story... stories) {
        repository.insertStory(stories);
    }

    public void update(Story... stories) {
        repository.updateStory(stories);
    }

    public void delete(Story... stories) {
        repository.deleteStory(stories);
    }
}
