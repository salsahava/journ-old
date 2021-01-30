package id.ac.ui.cs.mobileprogramming.salsahava.journ.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.salsahava.journ.entity.Comment;

@Dao
public interface CommentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Comment comment);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(Comment comment);

    @Delete
    void delete(Comment comment);

    @Query("SELECT * FROM comment_table")
    LiveData<List<Comment>> getAllComments();

    @Query("SELECT * FROM comment_table WHERE id=:id")
    LiveData<Comment> getCommentById(String id);

    @Query("DELETE FROM comment_table")
    void deleteAllComments();
}
