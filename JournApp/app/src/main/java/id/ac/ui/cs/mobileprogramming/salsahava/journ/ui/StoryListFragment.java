package id.ac.ui.cs.mobileprogramming.salsahava.journ.ui;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Calendar;
import java.util.List;

import id.ac.ui.cs.mobileprogramming.salsahava.journ.R;
import id.ac.ui.cs.mobileprogramming.salsahava.journ.adapter.RecyclerViewStoryAdapter;
import id.ac.ui.cs.mobileprogramming.salsahava.journ.entity.Story;
import id.ac.ui.cs.mobileprogramming.salsahava.journ.viewmodel.StoryListViewModel;

public class StoryListFragment extends Fragment {
    private static final String TAG = StoryListFragment.class.getSimpleName();
    private StoryListViewModel storyListViewModel;
    private RecyclerView recyclerView;
    private RecyclerViewStoryAdapter storyAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_story_list, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initRecyclerView();

        storyListViewModel = new ViewModelProvider(this).get(StoryListViewModel.class);
    }

    private void initRecyclerView() {
        storyAdapter = new RecyclerViewStoryAdapter();
        recyclerView = getView().findViewById(R.id.recyclerStory);
        RecyclerView.LayoutManager linearLayout = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayout);
        recyclerView.setAdapter(storyAdapter);
    }

}