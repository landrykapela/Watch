package tz.co.neelansoft.watch;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by landre on 24/07/2018.
 */

@Dao
public interface TaskDao {

    @Query("SELECT * FROM task")
    LiveData<List<Task>> getAllTasks();

    @Query("SELECT * FROM task WHERE id = :id")
    Task getTaskById(int id);

    @Insert
    void addTask(Task task);

    @Delete
    void removeTask(Task task);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateTask(Task task);
}
