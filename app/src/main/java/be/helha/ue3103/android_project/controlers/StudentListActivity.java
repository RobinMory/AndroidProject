package be.helha.ue3103.android_project.controlers;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import be.helha.ue3103.android_project.R;
import be.helha.ue3103.android_project.models.Student;
import be.helha.ue3103.android_project.models.StudentLab;

public class StudentListActivity extends AppCompatActivity {

    private FloatingActionButton mAddStudentButton;
    private StudentLab lab;
    private LinearLayout mContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);
        mAddStudentButton = findViewById(R.id.floatingActionButtonStudentList);
        mContainer = (LinearLayout) findViewById(R.id.student_list_container);
        lab  = StudentLab.get(this.getApplicationContext());
        setButtonListener();
        updateUI();
    }

    private void setButtonListener() {
        mAddStudentButton.setOnClickListener(event -> {
            Intent intent = new Intent(this, AddStudentActivity.class);
            this.startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        mContainer.removeAllViews();
        for (final Student student : lab.getStudents()) {
            View studentView = getStudentView(student);
            mContainer.addView(studentView);
        }
    }

    private View getStudentView(Student student) {
        View student_list_item = getLayoutInflater().inflate(R.layout.list_item_student, null);
        ((TextView) student_list_item.findViewById(R.id.student_name)).setText(student.getName());
        setClickOnStudentView(student ,student_list_item);
        return student_list_item;
    }

    private void setClickOnStudentView(final Student student, View student_list_item) {
        student_list_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ProjectActivity.class);
                intent.putExtra(ProjectActivity.STUDENT_EXTRA, student.getName());
                startActivity(intent);
            }
        });
    }


}
