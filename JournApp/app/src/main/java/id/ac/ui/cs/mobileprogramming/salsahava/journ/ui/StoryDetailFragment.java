package id.ac.ui.cs.mobileprogramming.salsahava.journ.ui;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;

import id.ac.ui.cs.mobileprogramming.salsahava.journ.R;
import id.ac.ui.cs.mobileprogramming.salsahava.journ.entity.Story;
import id.ac.ui.cs.mobileprogramming.salsahava.journ.util.Communicator;
import id.ac.ui.cs.mobileprogramming.salsahava.journ.viewmodel.StoryDetailViewModel;

public class StoryDetailFragment extends Fragment implements Communicator {
    private static final String TAG = StoryDetailFragment.class.getSimpleName();
    private StoryDetailViewModel mViewModel;
    private TextView storyTitle;
    private TextView storyDate;
    private TextView storyDesc;

    public static StoryDetailFragment newInstance() {
        return new StoryDetailFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_story_detail, container, false);
        this.storyTitle = view.findViewById(R.id.storyTitle);
        this.storyDate = view.findViewById(R.id.storyDate);
        this.storyDesc = view.findViewById(R.id.storyDesc);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(StoryDetailViewModel.class);
    }

    @Override
    public void displayDetails(Story mStory) {
        String datePattern = "EEEE, dd MMMM yyyy";
        SimpleDateFormat sdfDate = new SimpleDateFormat(datePattern);

        this.storyTitle.setText(mStory.getTitle());
        this.storyDate.setText(sdfDate.format(mStory.getDate()));
        this.storyDesc.setText(mStory.getDescription());
    }
}