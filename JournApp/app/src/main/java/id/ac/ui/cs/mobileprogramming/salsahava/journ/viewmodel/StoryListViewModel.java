package id.ac.ui.cs.mobileprogramming.salsahava.journ.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.salsahava.journ.entity.Story;
import id.ac.ui.cs.mobileprogramming.salsahava.journ.repository.StoryRepository;

public class StoryListViewModel extends AndroidViewModel {
    private static final String TAG = StoryListViewModel.class.getSimpleName();
    private LiveData<List<Story>> stories;
    private StoryRepository storyRepository;

    public StoryListViewModel(@NonNull Application application) {
        super(application);
        storyRepository = new StoryRepository(application);
        stories = storyRepository.getAllStories();
    }

    public LiveData<List<Story>> getAllStories() {
        return stories;
    }
}