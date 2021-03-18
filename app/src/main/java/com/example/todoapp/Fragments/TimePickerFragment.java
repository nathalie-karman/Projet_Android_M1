package com.example.todoapp.Fragments;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TimePicker;

import androidx.fragment.app.DialogFragment;

import com.example.todoapp.Utils.Constants;

import java.util.Calendar;

public class TimePickerFragment  extends DialogFragment implements TimePickerDialog.OnTimeSetListener{
    private int timeHour;
    private int timeMinute;
    private Handler handler;

    public TimePickerFragment(Handler handler){
        this.handler = handler;
    }



    /**
     * @param savedInstanceState
     * @return
     *  @NonNull
     *     @Override
     */

    public Dialog onCreateDialog2(Bundle savedInstanceState) {
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        return new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

            }
        }, hour, minute, android.text.format.DateFormat.is24HourFormat(getActivity()));
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        timeHour = bundle.getInt(Constants.HOUR);
        timeMinute = bundle.getInt(Constants.MINUTE);
        TimePickerDialog.OnTimeSetListener listener = new TimePickerDialog.OnTimeSetListener() {
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                timeHour = hourOfDay;
                timeMinute = minute;
                Bundle b = new Bundle();
                b.putInt(Constants.HOUR, timeHour);
                b.putInt(Constants.MINUTE, timeMinute);
                Message msg = new Message();
                msg.setData(b);
                handler.sendMessage(msg);
            }
        };
        return new TimePickerDialog(getActivity(), listener, timeHour, timeMinute, false);
    }
}
