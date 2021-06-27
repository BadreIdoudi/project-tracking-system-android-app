package com.selimhorri.pack.activity.employee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.selimhorri.pack.R;
import com.selimhorri.pack.activity.HomeActivity;

public class EmployeeTeamActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_team);
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
                super.startActivity(new Intent(EmployeeTeamActivity.this, EmployeeInfoActivity.class));
                return true;
            case R.id.employeeTeam:
                return true;
            case R.id.employeeProjects:
                super.startActivity(new Intent(EmployeeTeamActivity.this, EmployeeIndexActivity.class));
                return true;
            case R.id.employeeLogout:
                super.getSharedPreferences("emp", MODE_PRIVATE)
                        .edit()
                        .clear()
                        .apply();
                super.startActivity(new Intent(EmployeeTeamActivity.this, HomeActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}