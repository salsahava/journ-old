package id.ac.ui.cs.mobileprogramming.salsahava.journ.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View.OnClickListener;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Locale;

import id.ac.ui.cs.mobileprogramming.salsahava.journ.R;
import id.ac.ui.cs.mobileprogramming.salsahava.journ.entity.Story;
import id.ac.ui.cs.mobileprogramming.salsahava.journ.util.Communicator;

public class MainActivity extends AppCompatActivity implements Communicator {
    private BottomNavigationView navView;
    private static final String TAG = MainActivity.class.getSimpleName();
    private boolean isLandscape;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Locale en = new Locale("en", "ID");
        Locale.setDefault(en);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navView = findViewById(R.id.nav_view);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_trips)
                .build();

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        NavController navController = navHostFragment.getNavController();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
        findViewById(R.id.add_button).setOnClickListener(moveToAddStory);
    }

    @Override
    public void displayDetails(Story story) {
        if (isLandscape && story != null) {
            NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
            Fragment storyFragment = navHostFragment.getChildFragmentManager().getFragments().get(0);
            StoryDetailFragment storyDetailFragment = (StoryDetailFragment) storyFragment
                    .getChildFragmentManager()
                    .findFragmentById(R.id.storyDetailFragment);
            storyDetailFragment.displayDetails(story);
        }
        else {
            Intent intent = new Intent(this, StoryDetailActivity.class);
            intent.putExtra("storyId", String.valueOf(story.getId()));
            startActivity(intent);
        }
    }

    public OnClickListener moveToAddStory = v -> {
        Intent intent = new Intent(MainActivity.this, AddStoryActivity.class);
        startActivity(intent);
    };
}