package be.helha.ue3103.android_project.controlers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.UUID;

import be.helha.ue3103.android_project.R;
import be.helha.ue3103.android_project.models.MPMLab;
import be.helha.ue3103.android_project.models.Project;
import be.helha.ue3103.android_project.models.Student;

public class ProjectActivity extends AppCompatActivity {

    public static final String STUDENT_EXTRA = "STUDENT_EXTRA";

    private MPMLab lab;
    private Student student;
    private UUID mStudentId;

    private TextView mStudentName;
    private FloatingActionButton mAddProject;
    private LinearLayout mContainer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);
        mStudentName = findViewById(R.id.textView_project_student_name);
        mAddProject = findViewById(R.id.floatingActionButtonProjectList);
        mContainer = (LinearLayout) findViewById(R.id.project_list_container);
        lab =  MPMLab.get(this.getApplicationContext());
        setProjectWindow();
        setButtonListener();
        updateUI();
    }

    private void updateUI() {
        mContainer.removeAllViews();
        for (final Project project : lab.getProjects(mStudentId)) {
            View projectView = getProjectView(project);
            mContainer.addView(projectView);
        }
    }

    private View getProjectView(Project project) {
        View project_list_item = getLayoutInflater().inflate(R.layout.list_item_project, null);
        ((TextView) project_list_item.findViewById(R.id.project_name)).setText(project.getName());
        ((TextView) project_list_item.findViewById(R.id.project_average)).setText(Integer.toString(project.getAverage()) + "/20");
        setClickOnProjectView(project ,project_list_item);
        return project_list_item;
    }

    private void setClickOnProjectView(Project project, View project_list_item) {
        project_list_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), StepListActivity.class);
                intent.putExtra(StepListActivity.PROJECT_EXTRA, project.getId());
                startActivity(intent);
            }
        });
    }

    private void setProjectWindow() {
        mStudentId = (UUID) this.getIntent().getSerializableExtra(STUDENT_EXTRA);
        lab =  MPMLab.get(this.getApplicationContext());
        student = lab.getStudent(mStudentId);
        mStudentName.setText(student.getName());
    }

    private void setButtonListener() {
        mAddProject.setOnClickListener(event -> {
            Project new_project = new Project();
            new_project.setStudentId(mStudentId);
            new_project.setName("Nouveau Projet");
            new_project.setAverage(0);
            lab.addProject(new_project);
            View new_project_view = getNewProjectView(new_project);
            mContainer.addView(new_project_view);
        });
    }

    private View getNewProjectView(Project project) {
        View project_list_item = getLayoutInflater().inflate(R.layout.list_item_project, null);
        ((TextView) project_list_item.findViewById(R.id.project_name)).setText(project.getName());
        ((TextView) project_list_item.findViewById(R.id.project_average)).setText(project.getAverage() + "/20");
        setClickOnProjectView(project ,project_list_item);
        return project_list_item;
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateUI();
    }
}