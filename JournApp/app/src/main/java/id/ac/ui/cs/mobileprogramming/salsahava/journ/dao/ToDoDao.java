package id.ac.ui.cs.mobileprogramming.salsahava.journ.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.salsahava.journ.entity.ToDo;

@Dao
public interface ToDoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ToDo toDo);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(ToDo toDo);

    @Delete
    void delete(ToDo toDo);

    @Query("SELECT * FROM todo_table")
    LiveData<List<ToDo>> getAllToDos();

    @Query("SELECT * FROM todo_table WHERE id=:id")
    LiveData<ToDo> getToDoById(String id);

    @Query("DELETE FROM todo_table")
    void deleteAllToDos();
}
