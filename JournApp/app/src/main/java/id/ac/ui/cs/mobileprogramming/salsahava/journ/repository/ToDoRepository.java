package id.ac.ui.cs.mobileprogramming.salsahava.journ.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.salsahava.journ.dao.ToDoDao;
import id.ac.ui.cs.mobileprogramming.salsahava.journ.database.JournDatabase;
import id.ac.ui.cs.mobileprogramming.salsahava.journ.entity.ToDo;

public class ToDoRepository {
    private ToDoDao toDoDao;
    private LiveData<List<ToDo>> allToDos;

    public ToDoRepository(Application application) {
        JournDatabase journDatabase = JournDatabase.getInstance(application);
        toDoDao = journDatabase.getToDoDao();
        allToDos = toDoDao.getAllToDos();
    }

    public void insert(ToDo toDo) {
        new InsertToDoAsyncTask(toDoDao).execute(toDo);
    }

    public void update(ToDo toDo) {
        new UpdateToDoAsyncTask(toDoDao).execute(toDo);
    }

    public void delete(ToDo toDo) {
        new DeleteToDoAsyncTask(toDoDao).execute(toDo);
    }

    public void deleteAll() {
        new DeleteAllToDosAsyncTask(toDoDao).execute();
    }

    public LiveData<ToDo> getToDoById(String id) {
        return toDoDao.getToDoById(id);
    }

    public LiveData<List<ToDo>> getAllToDos() {
        return toDoDao.getAllToDos();
    }

    private static class InsertToDoAsyncTask extends AsyncTask<ToDo, Void, Void> {
        private ToDoDao toDoDao;

        private InsertToDoAsyncTask(ToDoDao toDoDao) {
            this.toDoDao = toDoDao;
        }

        @Override
        protected Void doInBackground(ToDo... toDos) {
            toDoDao.insert(toDos[0]);
            return null;
        }
    }

    private static class UpdateToDoAsyncTask extends AsyncTask<ToDo, Void, Void> {
        private ToDoDao toDoDao;

        private UpdateToDoAsyncTask(ToDoDao toDoDao) {
            this.toDoDao = toDoDao;
        }

        @Override
        protected Void doInBackground(ToDo... toDos) {
            toDoDao.update(toDos[0]);
            return null;
        }
    }

    private static class DeleteToDoAsyncTask extends AsyncTask<ToDo, Void, Void> {
        private ToDoDao toDoDao;

        private DeleteToDoAsyncTask(ToDoDao toDoDao) {
            this.toDoDao = toDoDao;
        }

        @Override
        protected Void doInBackground(ToDo... toDos) {
            toDoDao.delete(toDos[0]);
            return null;
        }
    }

    private static class DeleteAllToDosAsyncTask extends AsyncTask<Void, Void, Void> {
        private ToDoDao toDoDao;

        private DeleteAllToDosAsyncTask(ToDoDao toDoDao) {
            this.toDoDao = toDoDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            toDoDao.deleteAllToDos();
            return null;
        }
    }
}
