package id.ac.ui.cs.mobileprogramming.salsahava.journ.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.res.Configuration;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Locale;

import id.ac.ui.cs.mobileprogramming.salsahava.journ.R;
import id.ac.ui.cs.mobileprogramming.salsahava.journ.entity.Story;
import id.ac.ui.cs.mobileprogramming.salsahava.journ.entity.Trip;
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
                R.id.navigation_home, R.id.navigation_stories, R.id.navigation_todos)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
        // TODO
    }

    @Override
    public void displayDetails(Story story) {

    }
}