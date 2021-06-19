package com.example.timertask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.timertask.adapter.MainAdapter;
import com.example.timertask.model.TaskModel;
import com.ikovac.timepickerwithseconds.MyTimePickerDialog;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    ArrayList<TaskModel> models;
    RecyclerView recyclerView;
    MainAdapter mainAdapter;

    EditText entrtask,etEnterTime;
    TaskModel taskModel;
    int position;
    String SaveTask,SaveTime;
    int sec,minInt,hourInt,minToSec,hourTosce,totalTime;
    Handler handler;
     Button add;
    Runnable runnable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar();
        recyclerView=findViewById(R.id.rv_list_item);

        handler= new Handler();
        models= new ArrayList<TaskModel>();
        add = findViewById(R.id.tv_add);
        entrtask = findViewById(R.id.task);
       etEnterTime = findViewById(R.id.Main_time);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        mainAdapter= new MainAdapter(this,models);
        recyclerView.setAdapter(mainAdapter);

     etEnterTime.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
//             Calendar mcurrentTime = Calendar.getInstance();
//             int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
//             int minute = mcurrentTime.get(Calendar.MINUTE);
//             int second=mcurrentTime.get(Calendar.SECOND);
//             TimePickerDialog mTimePicker;
//             mTimePicker = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
//                 @Override
//                 public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute,int selectedSecond) {
//                     etEnterTime.setText( selectedHour + ":" + selectedMinute);
//                 }
//             }, hour, minute, second true);//Yes 24 hour time
//             mTimePicker.setTitle("Select Time");
//             mTimePicker.show();



          //manish code
             Calendar now = Calendar.getInstance();
             MyTimePickerDialog mTimePicker = new MyTimePickerDialog(MainActivity.this, new MyTimePickerDialog.OnTimeSetListener() {

                 @Override
                 public void onTimeSet(com.ikovac.timepickerwithseconds.TimePicker view, int hourOfDay, int minute, int seconds) {
                     etEnterTime.setText( hourOfDay + ":" + minute+":"+seconds);
                 }

//                 @Override
//                 public void onTimeSet(TimePicker view, int hourOfDay, int minute, int seconds) {
//                     // TODO Auto-generated method stub
//				/*time.setText(getString(R.string.time) + String.format("%02d", hourOfDay)+
//						":" + String.format("%02d", minute) +
//						":" + String.format("%02d", seconds));	*/
//                 }
             }, now.get(Calendar.HOUR_OF_DAY), now.get(Calendar.MINUTE), now.get(Calendar.SECOND), true);
             mTimePicker.show();
         }
     });






            add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "touch", Toast.LENGTH_SHORT).show();
                taskModel= new TaskModel();
                SaveTask= entrtask.getText().toString();
                SaveTime = etEnterTime.getText().toString();
                taskModel.setTaskName(SaveTask);
                taskModel.setTaskTime(SaveTime);
                models.add(taskModel);

                Collections.sort(models, new Comparator<TaskModel>() {
                    @Override
                    public int compare(TaskModel lhs, TaskModel rhs) {
                        return lhs.getTaskTime().compareTo(rhs.getTaskTime());
                    }
                });

                mainAdapter.notifyDataSetChanged();

              //

                try {
                    getCurrentTime();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                showaleart();

// Yeh Wala Runnable ka Code Work kr raha tha
                /*Runnable runnableCode = new Runnable() {
                    @Override
                    public void run() {
                        // Do something here on the main thread
                        Log.d("Handlers", "Called on main thread");
                        Toast.makeText(MainActivity.this, "Handlers", Toast.LENGTH_SHORT).show();
                        // Repeat this the same runnable code block again another 2 seconds

                        // 'this' is referencing the Runnable object
                        handler.postDelayed(this, totalTime*1000);
                    }
                };
// Start the initial runnable task by posting through the handler
                handler.post(runnableCode);*/




               /* handler.postDelayed(runnable= new Runnable() {
                    @Override
                    public void run() {
                        handler.postDelayed(runnable,2000);
                        Toast.makeText(MainActivity.this, "done baby", Toast.LENGTH_SHORT).show();
                    }
                }, 2000);*/



            }
        });



    }
// show aleart
    private void showaleart() {

        int i=totalTime;

        Intent intent = new Intent(this, MyBroadcastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                this.getApplicationContext(), 234324243, intent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()
                + (i * 1000), pendingIntent);
        Toast.makeText(this, "Task set in " + i + " seconds",Toast.LENGTH_LONG).show();
    }

    private void getCurrentTime()throws Exception
    {
//        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
//        Date date = new Date();
//        String currenttime=formatter.format(date);
//        Timestamp currenttimestamp= Timestamp.valueOf(currenttime);
//        Timestamp usertime=Timestamp.valueOf(SaveTime);
//        long milisec=currenttimestamp-usertime;
//
//        Toast.makeText(this, currenttime, Toast.LENGTH_SHORT).show();


//
       /* SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        String currenttime=formatter.format(date);
        Date date1 = formatter.parse(SaveTime);
        Date date2 = formatter.parse(currenttime);
        long differenceInMilliSeconds
                = Math.abs(date1.getTime() - date2.getTime());
        long differenceInSeconds
                = (differenceInMilliSeconds / 1000) % 60;
        long differenceInMinutes
                = (differenceInMilliSeconds / (60 * 1000)) % 60;


        String s=String.valueOf(differenceInMinutes);
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
*/

        //String time1 = "18:00:00";
        //String time2 = "7:30:50";

        // Creating a SimpleDateFormat object
        // to parse time in the format HH:MM:SS



        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        String currenttime=simpleDateFormat.format(date);


        // Parsing the Time Period
        Date date1 = simpleDateFormat.parse(SaveTime);
        Date date2 = simpleDateFormat.parse(currenttime);

        // Calculating the difference in milliseconds
        long differenceInMilliSeconds
                = Math.abs(date1.getTime() - date2.getTime());

        // Calculating the difference in Hours
        long differenceInHours
                = (differenceInMilliSeconds / (60 * 60 * 1000))
                % 24;

        // Calculating the difference in Minutes
        long differenceInMinutes
                = (differenceInMilliSeconds / (60 * 1000)) % 60;

        // Calculating the difference in Seconds
        long differenceInSeconds
                = (differenceInMilliSeconds / 1000) % 60;

        sec=(int)differenceInSeconds;
        minInt=(int)differenceInMinutes;
        hourInt=(int)differenceInHours;

        minToSec=minInt*60;
        hourTosce=hourInt*3600;
        totalTime=hourTosce+minToSec+sec;
        String s=String.valueOf(differenceInMinutes);
        String s1=String.valueOf(differenceInHours);
        String s2=String.valueOf(differenceInSeconds);

        Toast.makeText(this,"Minitue"+ s, Toast.LENGTH_SHORT).show();
        Toast.makeText(this,"hours"+ s1, Toast.LENGTH_SHORT).show();
        Toast.makeText(this,"second"+ s2, Toast.LENGTH_SHORT).show();



    }


}