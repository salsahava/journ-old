package id.ac.ui.cs.mobileprogramming.salsahava.journ;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import id.ac.ui.cs.mobileprogramming.salsahava.journ.data.Converters;
import id.ac.ui.cs.mobileprogramming.salsahava.journ.data.Story;
import id.ac.ui.cs.mobileprogramming.salsahava.journ.viewmodels.StoryViewModel;

public class MainActivity extends AppCompatActivity {
    private StoryViewModel storyViewModel;
    public static final int NEW_STORY_FRAGMENT_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final StoryListAdapter adapter = new StoryListAdapter(new StoryListAdapter.StoryDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        storyViewModel = new ViewModelProvider(this).get(StoryViewModel.class);
//        storyViewModel.getAllStories().observe(this, stories -> {
//            adapter.submitList(stories);
//        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, NewStoryActivity.class);
            startActivityForResult(intent, NEW_STORY_FRAGMENT_REQUEST_CODE);
        });

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_STORY_FRAGMENT_REQUEST_CODE && resultCode == RESULT_OK) {
            Story story = new Story((long)1, data.getStringExtra(NewStoryActivity.EXTRA_REPLY), Converters.toDate((long) 2021), "Nice nice");
            storyViewModel.insert(story);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }
}