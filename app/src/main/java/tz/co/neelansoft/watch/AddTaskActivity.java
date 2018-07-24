package tz.co.neelansoft.watch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.Date;

/**
 * Created by landre on 24/07/2018.
 */

public class AddTaskActivity extends AppCompatActivity {

    private Button btnSaveTask;
    private Button btnDiscard;
    private EditText etTitle;
    private EditText etDescription;
    private EditText etDuration;
    private RadioGroup radioGroup;
    private static ApplicationDatabase sInstance;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_task);

        btnDiscard   = findViewById(R.id.btnDiscard);
        btnSaveTask  = findViewById(R.id.btnSaveTask);

        etTitle      = findViewById(R.id.etTitle);
        etDescription= findViewById(R.id.etDescription);
        etDuration   = findViewById(R.id.etDuration);

        radioGroup   = findViewById(R.id.radioGroup);
        btnDiscard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnSaveTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTask();
            }
        });


    }

    private void saveTask() {
        String title = etTitle.getText().toString();
        String description = etDescription.getText().toString();
        String duration  = etDuration.getText().toString();
        Date date = new Date();

        final Task task = new Task(title,description,Integer.parseInt(duration));
        DateConverter converter = new DateConverter();
        if(isStarting()) task.setEndedAd(converter.toDate(converter.toTimestamp(date) + minutesToMills(Integer.parseInt(duration))));

        TaskExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                sInstance = ApplicationDatabase.getDatabaseInstance(AddTaskActivity.this);
                sInstance.getDao().addTask(task);
                finish();
            }
        });


    }

    private long minutesToMills(int minutes){
        return minutes * 60000;
    }
    private boolean isStarting(){
        switch(radioGroup.getCheckedRadioButtonId()){
            case R.id.radioButtonEnd:
                return false;
            case R.id.radioButtonStart:
                return true;
            default:
                return true;
        }
    }
}
