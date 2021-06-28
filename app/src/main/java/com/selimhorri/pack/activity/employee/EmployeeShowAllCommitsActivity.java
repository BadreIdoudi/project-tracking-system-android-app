package com.selimhorri.pack.activity.employee;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.selimhorri.pack.R;
import com.selimhorri.pack.activity.HomeActivity;
import com.selimhorri.pack.pattern.adapter.EmployeeShowAllCommitsCustomAdapter;
import com.selimhorri.pack.service.AssignmentService;
import com.selimhorri.pack.service.EmployeeService;
import com.selimhorri.pack.service.impl.AssignmentServiceImpl;
import com.selimhorri.pack.service.impl.EmployeeServiceImpl;

public class EmployeeShowAllCommitsActivity extends AppCompatActivity {

    private final EmployeeService employeeService;
    private final AssignmentService assignmentService;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    public EmployeeShowAllCommitsActivity() {
        this.employeeService = new EmployeeServiceImpl(EmployeeShowAllCommitsActivity.this);
        this.assignmentService = new AssignmentServiceImpl(EmployeeShowAllCommitsActivity.this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_show_all_commits);

        this.recyclerView = super.findViewById(R.id.recyclerViewEmployeeShowAllCommits);
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(EmployeeShowAllCommitsActivity.this));

        final Bundle extras = super.getIntent().getExtras();
        final Integer projectId = extras.getInt("projectId");

        final SharedPreferences sp = super.getSharedPreferences("emp", MODE_PRIVATE);
        final String username = sp.getString("username", null);

        this.employeeService.findByUsername(
                username,
                response -> {
                    this.assignmentService.findByProjectId(
                            projectId,
                            list -> {
                                this.recyclerView.setAdapter(
                                        new EmployeeShowAllCommitsCustomAdapter(
                                                EmployeeShowAllCommitsActivity.this,
                                                list.getCollection()
                                        )
                                );
                            },
                            errorList -> Toast.makeText(EmployeeShowAllCommitsActivity.this, errorList.toString(), Toast.LENGTH_SHORT).show()
                    );
                },
                error -> Toast.makeText(EmployeeShowAllCommitsActivity.this, error.toString(), Toast.LENGTH_SHORT).show()
        );

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.getMenuInflater()
                .inflate(R.menu.menu_employee, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.employeeAccountInfo:
                super.startActivity(new Intent(EmployeeShowAllCommitsActivity.this, EmployeeInfoActivity.class));
                return true;
            case R.id.employeeTeam:
                super.startActivity(new Intent(EmployeeShowAllCommitsActivity.this, EmployeeTeamActivity.class));
                return true;
            case R.id.employeeProjects:
                super.startActivity(new Intent(EmployeeShowAllCommitsActivity.this, EmployeeIndexActivity.class));
                return true;
            case R.id.employeeLogout:
                super.getSharedPreferences("emp", MODE_PRIVATE)
                        .edit()
                        .clear()
                        .apply();
                super.startActivity(new Intent(EmployeeShowAllCommitsActivity.this, HomeActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}