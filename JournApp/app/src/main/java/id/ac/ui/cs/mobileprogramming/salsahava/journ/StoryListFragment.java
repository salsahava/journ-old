package id.ac.ui.cs.mobileprogramming.salsahava.journ;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import id.ac.ui.cs.mobileprogramming.salsahava.journ.databinding.FragmentStoryListBinding;

public class StoryListFragment extends Fragment {
    private FragmentStoryListBinding binding;
    private StoryDetailsFragment storyDetailsFragment = new StoryDetailsFragment();

    public StoryListFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_story_list, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SharedViewModel viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        List<Story> list = new ArrayList<>();
        list.add(new Story("Day 1 - Louvre Museum", "Fri, 8 Jan 2021", "Lab 4 + Lab Report"));
        list.add(new Story("Day 2 - Eiffel Tower", "Sat, 9 Jan 2021", "Topic: recurrences"));
        list.add(new Story("Day 3 - Notre-Dame Cathedral", "Sun, 10 Jan 2021", "Deadline: 9 PM"));

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(list);
        binding.recyclerView.setAdapter(adapter);
        adapter.setListener((v, position) -> {
            viewModel.setSelected(adapter.getItemAt(position));
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.container, storyDetailsFragment)
                    .addToBackStack(null)
                    .commit();
        });
    }
}