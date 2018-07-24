package tz.co.neelansoft.watch;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.List;

/**
 * Created by landre on 24/07/2018.
 */

public class TaskListActivity extends AppCompatActivity implements TaskRecyclerViewAdapter.ItemClickListener{

    private RecyclerView mRecyclerView;
    private TaskRecyclerViewAdapter mAdapter;

    private static ApplicationDatabase sDatabaseInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_task_list);

        mRecyclerView  = findViewById(R.id.recyclerView);

        sDatabaseInstance = ApplicationDatabase.getDatabaseInstance(this);


        mAdapter = new TaskRecyclerViewAdapter(TaskListActivity.this,this);
        LiveData<List<Task>> liveTasks = sDatabaseInstance.getDao().getAllTasks();
        //call observer on live data

        liveTasks.observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(@Nullable List<Task> tasks) {
                mAdapter.setTasks(tasks);

            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onItemClickListener(int itemId){
        Toast.makeText(this,"Task id: "+String.valueOf(itemId),Toast.LENGTH_SHORT).show();
    }
}
