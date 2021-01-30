package id.ac.ui.cs.mobileprogramming.salsahava.journ.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.salsahava.journ.entity.Comment;
import id.ac.ui.cs.mobileprogramming.salsahava.journ.entity.Story;
import id.ac.ui.cs.mobileprogramming.salsahava.journ.repository.CommentRepository;
import id.ac.ui.cs.mobileprogramming.salsahava.journ.repository.StoryRepository;

public class StoryDetailViewModel extends AndroidViewModel {
    private StoryRepository storyRepository;
    private CommentRepository commentRepository;

    public StoryDetailViewModel(@NonNull Application application) {
        super(application);
        storyRepository = new StoryRepository(application);
        commentRepository = new CommentRepository(application);
    }

    public void updateStory(Story story) {
        storyRepository.update(story);
    }

    public void insertComment(Comment comment) {
        commentRepository.insert(comment);
    }

    public LiveData<Story> getStoryById(String id) {
        return storyRepository.getStoryById(id);
    }

    public LiveData<Comment> getCommentById(String id) {
        return commentRepository.getCommentById(id);
    }

    public LiveData<List<Comment>> getAllComments() {
        return commentRepository.getAllComments();
    }
}