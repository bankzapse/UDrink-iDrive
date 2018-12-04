package co.th.udrinkidrive;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.NumberPicker;

import com.takisoft.datetimepicker.DatePickerDialog;

import java.util.Calendar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class MonthYearPickerDialog extends DialogFragment {

    private DatePickerDialog.OnDateSetListener listener;
    private int daysOfMonth = 31;

    private NumberPicker monthPicker;
    private NumberPicker yearPicker;
    private NumberPicker dayPicker;

    private Calendar cal = Calendar.getInstance();

    public static final String MONTH_KEY = "monthValue";
    public static final String DAY_KEY = "dayValue";
    public static final String YEAR_KEY = "yearValue";

    int monthVal = -1 , dayVal = -1 , yearVal =-1 ;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = getArguments();
        if(extras != null){
            monthVal = extras.getInt(MONTH_KEY , -1);
            dayVal = extras.getInt(DAY_KEY , -1);
            yearVal = extras.getInt(YEAR_KEY , -1);
        }
    }

    public static MonthYearPickerDialog newInstance(int monthIndex , int daysIndex , int yearIndex) {
        MonthYearPickerDialog f = new MonthYearPickerDialog();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt(MONTH_KEY, monthIndex);
        args.putInt(DAY_KEY, daysIndex);
        args.putInt(YEAR_KEY, yearIndex);
        f.setArguments(args);

        return f;
    }

    public void setListener(DatePickerDialog.OnDateSetListener listener) {
        this.listener = listener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        //getDialog().setTitle("Add Birthday");

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View dialog = inflater.inflate(R.layout.month_year_picker, null);
        monthPicker = (NumberPicker) dialog.findViewById(R.id.picker_month);
        yearPicker = (NumberPicker) dialog.findViewById(R.id.picker_year);
        dayPicker = (NumberPicker) dialog.findViewById(R.id.picker_day);

        monthPicker.setMinValue(1);
        monthPicker.setMaxValue(12);

        if(monthVal != -1)// && (monthVal > 0 && monthVal < 13))
            monthPicker.setValue(monthVal);
        else
            monthPicker.setValue(cal.get(Calendar.MONTH) + 1);

        monthPicker.setDisplayedValues(new String[]{"Jan","Feb","Mar","Apr","May","June","July","Aug","Sep","Oct","Nov","Dec"});

        dayPicker.setMinValue(1);
        dayPicker.setMaxValue(daysOfMonth);

        if(dayVal != -1)
            dayPicker.setValue(dayVal);
        else
            dayPicker.setValue(cal.get(Calendar.DAY_OF_MONTH));

        monthPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                switch (newVal){
                    case 1:case 3:case 5:
                    case 7:case 8:case 10:
                    case 12:
                        daysOfMonth = 31;
                        dayPicker.setMaxValue(daysOfMonth);
                        break;
                    case 2:
                        daysOfMonth = 28;
                        dayPicker.setMaxValue(daysOfMonth);
                        break;

                    case 4:case 6:
                    case 9:case 11:
                        daysOfMonth = 30;
                        dayPicker.setMaxValue(daysOfMonth);
                        break;
                }

            }
        });

        int maxYear = cal.get(Calendar.YEAR);//2016
        final int minYear = 1916;//1997;
        int arraySize = maxYear - minYear;

        String[] tempArray = new String[arraySize];
        tempArray[0] = "---";
        int tempYear = minYear+1;

        for(int i=0 ; i < arraySize; i++){
            if(i != 0){
                tempArray[i] = " " + tempYear + "";
            }
            tempYear++;
        }
        Log.i("", "onCreateDialog: " + tempArray.length);
        yearPicker.setMinValue(minYear+1);
        yearPicker.setMaxValue(maxYear);
        yearPicker.setDisplayedValues(tempArray);

        if(yearVal != -1)
            yearPicker.setValue(yearVal);
        else
            yearPicker.setValue(tempYear -1);

        yearPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                try {
                    if(isLeapYear(picker.getValue())){
                        daysOfMonth = 29;
                        dayPicker.setMaxValue(daysOfMonth);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });


        builder.setView(dialog)
                // Add action buttons
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        int year = yearPicker.getValue();
                        if(year == (minYear+1)){
                            year = 1904;
                        }
                        listener.onDateSet(null, year, monthPicker.getValue(), dayPicker.getValue());
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MonthYearPickerDialog.this.getDialog().cancel();
                    }
                });

        return builder.create();
    }

    public static boolean isLeapYear(int year) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        return cal.getActualMaximum(Calendar.DAY_OF_YEAR) > 365;
    }
}
