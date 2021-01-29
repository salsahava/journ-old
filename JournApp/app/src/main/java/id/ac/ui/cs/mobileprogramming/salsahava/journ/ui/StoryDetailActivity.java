package id.ac.ui.cs.mobileprogramming.salsahava.journ.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

import com.cloudinary.android.MediaManager;

import id.ac.ui.cs.mobileprogramming.salsahava.journ.R;
import id.ac.ui.cs.mobileprogramming.salsahava.journ.entity.Story;
import id.ac.ui.cs.mobileprogramming.salsahava.journ.viewmodel.StoryDetailViewModel;

public class StoryDetailActivity extends AppCompatActivity {
    private static final String TAG = StoryDetailActivity.class.getSimpleName();
    private StoryDetailViewModel storyDetailViewModel;
    final int REQUEST_CODE_GALLERY = 999;
    final int REQUEST_IMAGE_CAPTURE = 1;
    private Uri selectedUri;
    private Story story;
    private RecyclerView recyclerView;
    // TODO

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_detail);
        try {
            MediaManager.init(StoryDetailActivity.this);
        }
        catch (IllegalStateException e) {

        }

        Intent intent = this.getIntent();
        String id = intent.getStringExtra("storyId");
        Button submit = findViewById(R.id.uploadButton);

    }
}