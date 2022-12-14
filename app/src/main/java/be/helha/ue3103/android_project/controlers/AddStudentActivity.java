package be.helha.ue3103.android_project.controlers;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import be.helha.ue3103.android_project.R;
import be.helha.ue3103.android_project.models.MPMLab;
import be.helha.ue3103.android_project.models.Student;

public class AddStudentActivity extends AppCompatActivity {

    private Button mAddStudentButton;
    private EditText mStudentListET;
    private MPMLab lab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        mAddStudentButton = findViewById(R.id.add_student_button);
        mStudentListET = findViewById(R.id.text_view_add_student_list);
        lab  =  MPMLab.get(this.getApplicationContext());
        setButtonListener();
    }

    private void setButtonListener() {
        mAddStudentButton.setOnClickListener(event -> {
            String[] studentList = mStudentListET.getText().toString().split("\n");
            for (String c: studentList) {
                Student s = new Student();
                s.setName(c);
                lab.addStudent(s);
                mStudentListET.setText("");
                Intent intent = new Intent(getApplicationContext(), StudentListActivity.class);
                startActivity(intent);
            }
        });
    }
}
