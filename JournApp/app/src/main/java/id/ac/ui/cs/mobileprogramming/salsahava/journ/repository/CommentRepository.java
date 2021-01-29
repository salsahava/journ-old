package id.ac.ui.cs.mobileprogramming.salsahava.journ.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.salsahava.journ.dao.CommentDao;
import id.ac.ui.cs.mobileprogramming.salsahava.journ.database.JournDatabase;
import id.ac.ui.cs.mobileprogramming.salsahava.journ.entity.Comment;

public class CommentRepository {
    private CommentDao commentDao;
    private LiveData<List<Comment>> allComments;

    public CommentRepository(Application application) {
        JournDatabase journDatabase = JournDatabase.getInstance(application);
        commentDao = journDatabase.getCommentDao();
        allComments = commentDao.getAllComments();
    }

    public void insert(Comment comment) {
        new InsertCommentAsyncTask(commentDao).execute(comment);
    }

    public LiveData<List<Comment>> getAllComments() {
        return allComments;
    }

    public LiveData<Comment> getCommentById(String id) {
        return commentDao.getCommentById(id);
    }

    private static class InsertCommentAsyncTask extends AsyncTask<Comment, Void, Void> {
        private CommentDao commentDao;

        private InsertCommentAsyncTask(CommentDao commentDao) {
            this.commentDao = commentDao;
        }

        @Override
        protected Void doInBackground(Comment... comments) {
            commentDao.insert(comments[0]);
            return null;
        }
    }
}
