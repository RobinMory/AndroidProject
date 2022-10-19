package be.helha.ue3103.android_project.controlers;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import be.helha.ue3103.android_project.R;

public class StudentListActivity extends AppCompatActivity {

    private FloatingActionButton mAddStudentButton;
    private static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);
        mAddStudentButton = findViewById(R.id.floatingActionButtonStudentList);
        setButtonListener();
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
        //Affichage de la liste
    }
}
