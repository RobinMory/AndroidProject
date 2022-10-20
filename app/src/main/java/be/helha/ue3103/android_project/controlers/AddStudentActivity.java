package be.helha.ue3103.android_project.controlers;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import be.helha.ue3103.android_project.R;
import be.helha.ue3103.android_project.models.Student;
import be.helha.ue3103.android_project.models.StudentLab;

public class AddStudentActivity extends AppCompatActivity {

    private Button mAddStudentButton;
    private EditText mStudentListET;
    private StudentLab lab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        mAddStudentButton = findViewById(R.id.add_student_button);
        mStudentListET = findViewById(R.id.text_view_add_student_list);
        lab  = StudentLab.get(this.getApplicationContext());
        setButtonListener();
    }

    private void setButtonListener() {
        mAddStudentButton.setOnClickListener(event -> {
            String[] studentList = mStudentListET.getText().toString().split("\n");
            for (String c: studentList) {
                Student s = new Student();
                s.setName(c);
                lab.addStudent(s);
                //System.out.println(s.getId());
                //System.out.println(s.getName());
            }
        });
    }
}
