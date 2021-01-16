package id.ac.ui.cs.mobileprogramming.salsahava.journ.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.Calendar;

import id.ac.ui.cs.mobileprogramming.salsahava.journ.R;
import id.ac.ui.cs.mobileprogramming.salsahava.journ.viewmodel.AddStoryViewModel;

public class AddStoryActivity extends AppCompatActivity {
    final Calendar myCalendar = Calendar.getInstance();
    private static final String TAG = AddStoryActivity.class.getSimpleName();
    private AddStoryViewModel addStoryViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_story);
    }
}