package com.example.jaanfdo.myfinalproject.AlertDialog;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.support.v7.app.AppCompatActivity;
import com.example.jaanfdo.myfinalproject.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ActivityAlertDialog2 extends AppCompatActivity implements DatePickerFragment.DateDialogListener, TimePickerFragment.TimeDialogListener {

    private static final String DIALOG_DATE = "ActivityAlertDialog2.DateDialog";
    private static final String DIALOG_TIME = "ActivityAlertDialog2.TimeDialog";
    private static final String DIALOG_SINGLE_BUTTON = "ActivityAlertDialog2.SingleButtonDialog";
    private static final String DIALOG_TWO_BUTTON = "ActivityAlertDialog2.TwoButtonDialog";
    private static final String DIALOG_THREE_BUTTON = "ActivityAlertDialog2.ThreeButtonDialog";
    private static final String DIALOG_SINGLE_CHOICE_LIST = "ActivityAlertDialog2.SingleChoiceListDialog";
    private static final String DIALOG_SINGLE_CHOICE_RADIO_BUTTON_LIST = "ActivityAlertDialog2.SingleChoiceRadioButtonListDialog";
    private static final String DIALOG_MULTIPLE_CHOICE_CHECK_BOX_LIST = "ActivityAlertDialog2.MultipleChoiceCheckBoxListDialog";


    private Button singleButtonAlertDialog;
    private Button twoButtonAlertDialog;
    private Button threeButtonAlertDialog;
    private Button datePickerAlertDialog;
    private Button datePickerAlertDialog2;
    private Button timePickerAlertDialog;
    private Button singleChoiceListAlertDialog;
    private Button singleChoiceRadioButtonAlertDialog;
    private Button multipleChoiceCheckBoxAlertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_dialog2);

        singleButtonAlertDialog = (Button)findViewById(R.id.alert_dialog_one_button);
        twoButtonAlertDialog = (Button)findViewById(R.id.alert_dialog_two_button);
        threeButtonAlertDialog = (Button)findViewById(R.id.alert_dialog_three_button);
        datePickerAlertDialog = (Button)findViewById(R.id.alert_dialog_date_picker);
        datePickerAlertDialog2 = (Button)findViewById(R.id.alert_dialog_date_picker2);
        timePickerAlertDialog = (Button)findViewById(R.id.alert_dialog_time_picker);
        singleChoiceListAlertDialog = (Button)findViewById(R.id.alert_dialog_single_choice_list_button);
        singleChoiceRadioButtonAlertDialog = (Button)findViewById(R.id.alert_dialog_single_choice_radio_button_list_button);
        multipleChoiceCheckBoxAlertDialog = (Button)findViewById(R.id.alert_dialog_multiple_choice_check_box_list_button);

        singleButtonAlertDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SingleButtonFragment dialog = new SingleButtonFragment();
                dialog.show(getSupportFragmentManager(), DIALOG_SINGLE_BUTTON);

            }
        });
        twoButtonAlertDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TwoButtonFragment dialog = new TwoButtonFragment();
                dialog.show(getSupportFragmentManager(), DIALOG_TWO_BUTTON);

            }
        });
        threeButtonAlertDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ThreeButtonFragment dialog = new ThreeButtonFragment();
                dialog.show(getSupportFragmentManager(), DIALOG_THREE_BUTTON);

            }
        });
        datePickerAlertDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerFragment dialog = new DatePickerFragment();
                dialog.show(getSupportFragmentManager(), DIALOG_DATE);
            }
        });
        timePickerAlertDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TimePickerFragment dialog = new TimePickerFragment();
                dialog.show(getSupportFragmentManager(), DIALOG_TIME);
            }
        });
        singleChoiceListAlertDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SingleChoiceListFragment dialog = new SingleChoiceListFragment();
                dialog.show(getSupportFragmentManager(), DIALOG_SINGLE_CHOICE_LIST);
            }
        });

        singleChoiceRadioButtonAlertDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SingleChoiceRadioButtonListFragment dialog = new SingleChoiceRadioButtonListFragment();
                dialog.show(getSupportFragmentManager(), DIALOG_SINGLE_CHOICE_RADIO_BUTTON_LIST);
            }
        });
        multipleChoiceCheckBoxAlertDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MultipleChoiceCheckBoxListFragment dialog = new MultipleChoiceCheckBoxListFragment();
                dialog.show(getSupportFragmentManager(), DIALOG_MULTIPLE_CHOICE_CHECK_BOX_LIST);
            }
        });

        datePickerAlertDialog2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(ActivityAlertDialog2.this, ActivityAlertDialog.class);
                startActivity(i);
            }
        });

    }


    @Override
    public void onFinishDialog(Date date) {
        Toast.makeText(this, "Selected Date :"+ formatDate(date), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFinishDialog(String time) {
        Toast.makeText(this, "Selected Time : "+ time, Toast.LENGTH_SHORT).show();
    }

    public String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String hireDate = sdf.format(date);
        return hireDate;
    }
}




