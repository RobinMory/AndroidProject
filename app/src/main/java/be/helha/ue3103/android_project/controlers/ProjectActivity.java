package be.helha.ue3103.android_project.controlers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import be.helha.ue3103.android_project.R;

public class ProjectActivity extends AppCompatActivity {

    public static final String STUDENT_EXTRA = "STUDENT_EXTRA";
    private TextView mStudentName;
    private String mStudentId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);
        mStudentName = findViewById(R.id.textView_project_student_name);
        mStudentId = this.getIntent().getStringExtra(STUDENT_EXTRA);
        mStudentName.setText(mStudentId);
    }
}