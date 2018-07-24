package tz.co.neelansoft.watch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextClock;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final String EXTRA_TIME_KEY = "time";
    private TextView tvDate;
    private TextClock tvTime;
    private Button btnRecordEvent;

    private static final String DATE_FORMAT = "dd-MM-yyyy HH:mm:ss";
    private SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDate         = findViewById(R.id.tvDate);
        tvTime         = findViewById(R.id.textClock);
        btnRecordEvent = findViewById(R.id.btnRecordEvent);

        //get current time
        Date currentDate = new Date();
        String displayDate = DateFormat.getDateInstance(DateFormat.MEDIUM,Locale.getDefault()).format(currentDate);
        tvDate.setText(displayDate);
        tvTime.getFormat12Hour();

        //add button clieck listener
        btnRecordEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddTask();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.activity_main,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.action_view:
                startActivity(new Intent(getApplicationContext(),TaskListActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //private method to launch activity
    private void openAddTask(){
        String time = String.valueOf(tvTime.getFormat12Hour());
        startActivity(new Intent(getApplicationContext(),AddTaskActivity.class));
    }
}
