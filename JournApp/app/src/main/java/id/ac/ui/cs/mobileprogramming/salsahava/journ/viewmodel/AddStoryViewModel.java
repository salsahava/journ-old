package id.ac.ui.cs.mobileprogramming.salsahava.journ.viewmodel;

import android.app.Application;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import id.ac.ui.cs.mobileprogramming.salsahava.journ.entity.Story;
import id.ac.ui.cs.mobileprogramming.salsahava.journ.repository.StoryRepository;

public class AddStoryViewModel extends AndroidViewModel {
    private static final String TAG = AddStoryViewModel.class.getSimpleName();
    public MutableLiveData<String> title = new MutableLiveData<>();
    public MutableLiveData<String> day = new MutableLiveData<>();
    public MutableLiveData<String> description = new MutableLiveData<>();
    private MutableLiveData<Story> storyMutableLiveData;
    private StoryRepository storyRepository;

    public StoryRepository getStoryRepository() {
        return storyRepository;
    }

    public AddStoryViewModel(@NonNull Application application) {
        super(application);
        storyRepository = new StoryRepository(application);
    }

    public void insert(Story story) {
        storyRepository.insert(story);
    }

    public MutableLiveData<Story> getStory() {
        if (storyMutableLiveData == null) {
            storyMutableLiveData = new MutableLiveData<>();
        }
        return storyMutableLiveData;
    }

    private Date getDate() {
        return new Date();
    }

    public void OnClick(View view) {
        Date date = null;

        if(!TextUtils.isEmpty(day.getValue())) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                date = sdf.parse(day.getValue());
            }
            catch (ParseException e) {

            }
        }

        Story story = new Story(title.getValue(), date, description.getValue());

        storyMutableLiveData.setValue(story);
    }
}
