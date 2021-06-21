package com.selimhorri.pack.service.impl.dynmc;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.selimhorri.pack.constant.BackendApiUrlConstant;
import com.selimhorri.pack.listener.ResponseCallbackListener;
import com.selimhorri.pack.model.collection.DtoCollection;
import com.selimhorri.pack.model.dto.Employee;
import com.selimhorri.pack.model.dto.custom.EmployeeProjectData;
import com.selimhorri.pack.pattern.singleton.GsonSingletonPattern;
import com.selimhorri.pack.pattern.singleton.QueueSingletonPattern;
import com.selimhorri.pack.service.EmployeeService;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class EmployeeServiceDynamicImpl implements EmployeeService {

    private static final String API_URL = BackendApiUrlConstant.EmployeeBackendUrl.EMPLOYEE_API_URL;
    private static final Gson gson = GsonSingletonPattern.getInstance().configDeserialization("dd/MM/yyyy");
    private final Context context;

    public EmployeeServiceDynamicImpl(final Context context) {
        this.context = context;
    }

    @Override
    public void findAll(final ResponseCallbackListener.ResponseCallbackSuccessListener<DtoCollection<Employee>> resp, final ResponseCallbackListener.ResponseCallbackErrorListener err) {

        final JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                API_URL,
                null,
                response -> resp.onResponse(gson.fromJson(response.toString(), new TypeToken<DtoCollection<Employee>>() {}.getType())),
                error -> err.onError(error.getMessage())
        );
        QueueSingletonPattern.getInstance(this.context).addToRequestQueue(request);

    }

    @Override
    public void findById(Integer employeeId, ResponseCallbackListener.ResponseCallbackSuccessListener<Employee> resp, ResponseCallbackListener.ResponseCallbackErrorListener err) {

        final JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                API_URL + "/" + employeeId,
                null,
                response -> resp.onResponse(gson.fromJson(response.toString(), Employee.class)),
                error -> err.onError(error.getMessage())
        );
        QueueSingletonPattern.getInstance(this.context).addToRequestQueue(request);

    }

    @Override
    public void save(Employee employee, ResponseCallbackListener.ResponseCallbackSuccessListener<Employee> resp, ResponseCallbackListener.ResponseCallbackErrorListener err) {

        final Map<String, Object> map = new HashMap<>();
        map.put("firstName", employee.getFirstName());
        map.put("lastName", employee.getLastName());
        map.put("email", employee.getEmail());
        map.put("phone", employee.getPhone());
        map.put("hiredate", employee.getHiredate());
        map.put("job", employee.getJob());
        map.put("salary", employee.getSalary());
        map.put("manager", employee.getManager());
        map.put("department", employee.getDepartment());
        map.put("credential", employee.getUserCredential());

        final JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                API_URL,
                new JSONObject(map),
                response -> resp.onResponse(gson.fromJson(response.toString(), Employee.class)),
                error -> err.onError(error.getMessage())
        );
        QueueSingletonPattern.getInstance(this.context).addToRequestQueue(request);

    }

    @Override
    public void update(Employee employee, ResponseCallbackListener.ResponseCallbackSuccessListener<Employee> resp, ResponseCallbackListener.ResponseCallbackErrorListener err) {

        final Map<String, Object> map = new HashMap<>();
        map.put("employeeId", employee.getEmployeeId());
        map.put("firstName", employee.getFirstName());
        map.put("lastName", employee.getLastName());
        map.put("email", employee.getEmail());
        map.put("phone", employee.getPhone());
        map.put("hiredate", employee.getHiredate());
        map.put("job", employee.getJob());
        map.put("salary", employee.getSalary());
        map.put("manager", employee.getManager());
        map.put("department", employee.getDepartment());
        map.put("credential", employee.getUserCredential());

        final JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.PUT,
                API_URL,
                new JSONObject(map),
                response -> resp.onResponse(gson.fromJson(response.toString(), Employee.class)),
                error -> err.onError(error.getMessage())
        );
        QueueSingletonPattern.getInstance(this.context).addToRequestQueue(request);

    }

    @Override
    public void deleteById(Integer employeeId, ResponseCallbackListener.ResponseCallbackSuccessListener<Boolean> resp, ResponseCallbackListener.ResponseCallbackErrorListener err) {

        final JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.DELETE,
                API_URL + "/" + employeeId,
                null,
                response -> resp.onResponse(gson.fromJson(response.toString(), Boolean.class)),
                error -> err.onError(error.getMessage())
        );
        QueueSingletonPattern.getInstance(this.context).addToRequestQueue(request);

    }

    @Override
    public void findByUsername(String username, ResponseCallbackListener.ResponseCallbackSuccessListener<Employee> resp, ResponseCallbackListener.ResponseCallbackErrorListener err) {

        final JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                API_URL + "/username/" + username,
                null,
                // response -> resp.onResponse(new Gson().fromJson(response.toString(), Employee.class)),
                response -> resp.onResponse(gson.fromJson(response.toString(), Employee.class)),
                error -> err.onError(error.getMessage())
        );
        QueueSingletonPattern.getInstance(this.context).addToRequestQueue(request);

    }

    @Override
    public void findByEmployeeId(Integer employeeId, ResponseCallbackListener.ResponseCallbackSuccessListener<DtoCollection<EmployeeProjectData>> resp, ResponseCallbackListener.ResponseCallbackErrorListener err) {

        final JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                API_URL + "/data/employee-project-data/" + employeeId,
                null,
                response -> resp.onResponse(gson.fromJson(response.toString(), new TypeToken<DtoCollection<EmployeeProjectData>>() {}.getType())),
                error -> err.onError(error.getMessage())
        );
        QueueSingletonPattern.getInstance(this.context).addToRequestQueue(request);
    }


}
