package id.ac.ui.cs.mobileprogramming.salsahava.journ;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private StoryListFragment storyListFragment = new StoryListFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, storyListFragment)
                .addToBackStack("story_list")
                .commit();
    }
}