package com.example.jaanfdo.myfinalproject.Students;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jaanfdo.myfinalproject.R;

import java.util.List;

public class StudentsActivity extends AppCompatActivity {

    TextView textViewStudentItem;
    TextView locationItem;
    Button buttonCreateStudent;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students);

        buttonCreateStudent = (Button) findViewById(R.id.buttonCreateStudent);

        buttonCreateStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Context context = view.getRootView().getContext();

                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View formElementsView = inflater.inflate(R.layout.student_input_form, null, false);

                final EditText editTextStudentFirstname = (EditText) formElementsView.findViewById(R.id.editTextStudentFirstname);
                final EditText editTextStudentEmail = (EditText) formElementsView.findViewById(R.id.editTextStudentEmail);

                new AlertDialog.Builder(context)
                        .setView(formElementsView)
                        .setTitle("Create Student")
                        .setPositiveButton("Add",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {

                                        String studentFirstname = editTextStudentFirstname.getText().toString();
                                        String studentEmail = editTextStudentEmail.getText().toString();

                                        ObjectStudent objectStudent = new ObjectStudent();
                                        objectStudent.firstname= studentFirstname;
                                        objectStudent.email= studentEmail;

                                        boolean createSuccessful = new TableControllerStudent(context).create(objectStudent);

                                        if(createSuccessful){
                                            Toast.makeText(context, "Student information was saved.", Toast.LENGTH_SHORT).show();
                                        }else{
                                            Toast.makeText(context, "Unable to save student information.", Toast.LENGTH_SHORT).show();
                                        }

                                        countRecords();
                                        readRecords();
                                    }

                                }).show();


            }
        });

        countRecords();
        readRecords();
    }

    public void countRecords() {
        int recordCount = new TableControllerStudent(this).count();

        TextView textViewRecordCount = (TextView) findViewById(R.id.textViewRecordCount);
        textViewRecordCount.setText(recordCount + " records found.");
    }
    public void readRecords() {
        LinearLayout linearLayoutRecords = (LinearLayout) findViewById(R.id.linearLayoutRecords);
        linearLayoutRecords.removeAllViews();

        List<ObjectStudent> students = new TableControllerStudent(this).read();

        if (students.size() > 0) {
            for (ObjectStudent obj : students) {
                int id = obj.id;
                String studentFirstname = obj.firstname;
                String studentEmail = obj.email;

                String textViewContents = studentFirstname + " - " + studentEmail;

                textViewStudentItem = new TextView(this);
                textViewStudentItem.setPadding(0, 10, 0, 10);
                textViewStudentItem.setText(textViewContents);
                textViewStudentItem.setTag(Integer.toString(id));

                textViewStudentItem.setOnLongClickListener(new View.OnLongClickListener() {
                    String id;
                    @Override
                    public boolean onLongClick(View view) {
                        context = view.getContext();
                        id = view.getTag().toString();

                        final CharSequence[] items = { "Edit", "Delete" };

                        new AlertDialog.Builder(context).setTitle("Student Record")
                                .setItems(items, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int item) {

                                        if (item == 0) {
                                            editRecord(Integer.parseInt(id));
                                        }
                                        else if (item == 1) {

                                            boolean deleteSuccessful = new TableControllerStudent(context).delete(Integer.parseInt(id));

                                            if (deleteSuccessful){
                                                Toast.makeText(context, "Student record was deleted.", Toast.LENGTH_SHORT).show();
                                            }else{
                                                Toast.makeText(context, "Unable to delete student record.", Toast.LENGTH_SHORT).show();
                                            }

                                            countRecords();
                                            readRecords();
                                        }
                                        dialog.dismiss();
                                    }
                                }).show();
                        return false;
                    }
                });

                linearLayoutRecords.addView(textViewStudentItem);
            }
        }
        else {
            locationItem = new TextView(this);
            locationItem.setPadding(8, 8, 8, 8);
            locationItem.setText("No records yet.");

            linearLayoutRecords.addView(locationItem);
        }
    }
    public void editRecord(final int studentId) {
        final TableControllerStudent tableControllerStudent = new TableControllerStudent(context);
        ObjectStudent objectStudent = tableControllerStudent.readSingleRecord(studentId);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View formElementsView = inflater.inflate(R.layout.student_input_form, null, false);

        final EditText editTextStudentFirstname = (EditText) formElementsView.findViewById(R.id.editTextStudentFirstname);
        final EditText editTextStudentEmail = (EditText) formElementsView.findViewById(R.id.editTextStudentEmail);

        editTextStudentFirstname.setText(objectStudent.firstname);
        editTextStudentEmail.setText(objectStudent.email);

        new AlertDialog.Builder(context)
                .setView(formElementsView)
                .setTitle("Edit Record")
                .setPositiveButton("Save Changes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                ObjectStudent objectStudent = new ObjectStudent();
                                objectStudent.id = studentId;
                                objectStudent.firstname = editTextStudentFirstname.getText().toString();
                                objectStudent.email = editTextStudentEmail.getText().toString();

                                boolean updateSuccessful = tableControllerStudent.update(objectStudent);

                                if(updateSuccessful){
                                    Toast.makeText(context, "Student record was updated.", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(context, "Unable to update student record.", Toast.LENGTH_SHORT).show();
                                }

                                countRecords();
                                readRecords();

                                dialog.cancel();
                            }

                        }).show();
    }

}
