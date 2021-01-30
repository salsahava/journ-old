package id.ac.ui.cs.mobileprogramming.salsahava.journ.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import id.ac.ui.cs.mobileprogramming.salsahava.journ.dao.UserDao;
import id.ac.ui.cs.mobileprogramming.salsahava.journ.database.JournDatabase;
import id.ac.ui.cs.mobileprogramming.salsahava.journ.entity.User;

public class UserRepository {
    private UserDao userDao;

    public UserRepository(Application application) {
        JournDatabase journDatabase = JournDatabase.getInstance(application);
        userDao = journDatabase.getUserDao();
    }

    public LiveData<User> getUserById(String id) {
        return userDao.getUserById(id);
    }
}
