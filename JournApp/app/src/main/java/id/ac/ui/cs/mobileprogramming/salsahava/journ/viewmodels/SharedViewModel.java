package id.ac.ui.cs.mobileprogramming.salsahava.journ.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import id.ac.ui.cs.mobileprogramming.salsahava.journ.data.Story;

public class SharedViewModel extends ViewModel {
    private MutableLiveData<Story> selected = new MutableLiveData<>();

    public void setSelected(Story story) {
        selected.setValue(story);
    }

    public MutableLiveData<Story> getSelected() {
        return selected;
    }
}
