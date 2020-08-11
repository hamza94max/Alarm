package hamaza.hamaza;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

import hamaza.larntech.R;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    TimePicker picker;
    Intent intent;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        picker=findViewById(R.id.picker);
        picker.setIs24HourView(true);



         intent = new Intent(this, NotificationReceiver.class);

        Button button =findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                // Notification

                PendingIntent pendingIntent = (PendingIntent) PendingIntent.getBroadcast(getBaseContext(), 1,
                        intent, PendingIntent.FLAG_UPDATE_CURRENT);
                if (alarmManager != null) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeInMillis(System.currentTimeMillis());
                    calendar.set(Calendar.HOUR_OF_DAY, picker.getHour());
                    calendar.set(Calendar.MINUTE,picker.getMinute());
                    alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                            5000, pendingIntent);
                }

            }
        });


    }}