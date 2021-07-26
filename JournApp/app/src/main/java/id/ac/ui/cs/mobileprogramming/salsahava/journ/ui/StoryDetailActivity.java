package id.ac.ui.cs.mobileprogramming.salsahava.journ.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cloudinary.android.MediaManager;
import com.cloudinary.android.callback.ErrorInfo;
import com.cloudinary.android.callback.UploadCallback;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import id.ac.ui.cs.mobileprogramming.salsahava.journ.R;
import id.ac.ui.cs.mobileprogramming.salsahava.journ.adapter.RecyclerViewCommentAdapter;
import id.ac.ui.cs.mobileprogramming.salsahava.journ.entity.Comment;
import id.ac.ui.cs.mobileprogramming.salsahava.journ.entity.Story;
import id.ac.ui.cs.mobileprogramming.salsahava.journ.viewmodel.StoryDetailViewModel;

public class StoryDetailActivity extends AppCompatActivity {
    private static final String TAG = StoryDetailActivity.class.getSimpleName();
    private StoryDetailViewModel storyDetailViewModel;
    final int REQUEST_CODE_GALLERY = 999;
    private Uri selectedUri;
    private Story story;
    private RecyclerView recyclerView;
    private RecyclerViewCommentAdapter adapter;
    private List<Comment> comments = new ArrayList<>();
    private int commentSize;

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
        recyclerView = findViewById(R.id.recyclerComment);
        initRecyclerView();
        submit.setOnClickListener(v -> ActivityCompat.requestPermissions(
                StoryDetailActivity.this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                REQUEST_CODE_GALLERY
        ));

        storyDetailViewModel = new ViewModelProvider(this).get(StoryDetailViewModel.class);

        storyDetailViewModel.getAllComments().observe(this, comments -> commentSize = comments.size());

        storyDetailViewModel.getStoryById(id).observe(this, story1 -> {
            setView(story1);
            story = story1;

            if (story1.getCommentId() != null) {
                for (Integer id1 : story1.getCommentId()) {
                    storyDetailViewModel.getCommentById(String.valueOf(id1))
                            .observe(StoryDetailActivity.this, comment -> {
                                if (comment != null) {
                                    comments.add(comment);
                                    adapter.setComments(comments);
                                }
                            });
                }
            }
        });
    }

    private void setView(Story story) {
        String datePattern = "EEEE, dd MMMM yyyy";
        SimpleDateFormat sdfDate = new SimpleDateFormat(datePattern);

        TextView storyTitle = findViewById(R.id.storyTitle);
        TextView storyDate = findViewById(R.id.storyDate);
        TextView storyDesc = findViewById(R.id.storyDesc);

        storyTitle.setText(story.getTitle());
        storyDate.setText(sdfDate.format(story.getDate()));
        storyDesc.setText(story.getDescription());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                          @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE_GALLERY) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALLERY);
            }

            else {
                Toast.makeText(getApplicationContext(),
                        "You do not have permission to access the file location",
                        Toast.LENGTH_SHORT).show();
            }

            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null) {
            selectedUri = data.getData();

            AlertDialog.Builder builder = new AlertDialog.Builder(StoryDetailActivity.this);

            builder
                    .setMessage(getString(R.string.dialogue_confirmation))
                    .setPositiveButton(getString(R.string.yes), (dialog, id) -> submit())
                    .setNegativeButton(getString(R.string.no), (dialog, id) -> dialog.cancel())
                    .show();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void submit() {
        MediaManager.get().upload(selectedUri)
                .unsigned("ud5zpz7a")
                .option("resource_type", "image")
                .callback(new UploadCallback() {
                    @Override
                    public void onStart(String requestId) {

                    }

                    @Override
                    public void onProgress(String requestId, long bytes, long totalBytes) {

                    }

                    @Override
                    public void onSuccess(String requestId, Map resultData) {
                        String cloudinaryUrl = resultData.get("secure_url").toString();
                        Comment comment = new Comment("Salsa Hava", cloudinaryUrl);
                        List<Integer> commentId = story.getCommentId();

                        if (commentId == null) {
                            commentId = new ArrayList<>();
                        }

                        commentId.add(commentSize + 1);
                        story.setCommentId(commentId);
                        storyDetailViewModel.insertComment(comment);
                        storyDetailViewModel.updateStory(story);
                        Log.d(TAG, "onSuccess: ");
                    }

                    @Override
                    public void onError(String requestId, ErrorInfo error) {

                    }

                    @Override
                    public void onReschedule(String requestId, ErrorInfo error) {

                    }
                }).dispatch();
    }

    private void initRecyclerView() {
        adapter = new RecyclerViewCommentAdapter();
        RecyclerView.LayoutManager linearLayout = new LinearLayoutManager(StoryDetailActivity.this);
        recyclerView.setLayoutManager(linearLayout);
        recyclerView.setAdapter(adapter);
    }
}