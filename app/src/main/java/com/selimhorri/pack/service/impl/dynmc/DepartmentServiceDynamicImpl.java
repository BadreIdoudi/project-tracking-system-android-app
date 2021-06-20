package com.selimhorri.pack.service.impl.dynmc;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.selimhorri.pack.constant.BackendApiUrlConstant;
import com.selimhorri.pack.listener.ResponseCallbackListener;
import com.selimhorri.pack.model.collection.DtoCollection;
import com.selimhorri.pack.model.dto.Department;
import com.selimhorri.pack.pattern.QueuePattern;
import com.selimhorri.pack.service.DepartmentService;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DepartmentServiceDynamicImpl implements DepartmentService {

    private static final String API_URL = BackendApiUrlConstant.DepartmentBackendUrl.DEPARTMENT_API_URL;
    private final Context context;

    public DepartmentServiceDynamicImpl(final Context context) {
        this.context = context;
    }

    @Override
    public void findAll(final ResponseCallbackListener.ResponseCallbackSuccessListener<DtoCollection<Department>> resp, final ResponseCallbackListener.ResponseCallbackErrorListener err) {

        final JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                API_URL,
                null,
                response -> resp.onResponse(new Gson().fromJson(response.toString(), new TypeToken<DtoCollection<Department>>() {}.getType())),
                error -> err.onError(error.toString())
        );
        QueuePattern.getInstance(this.context).addToRequestQueue(request);

    }

    @Override
    public void findById(Integer departmentId, ResponseCallbackListener.ResponseCallbackSuccessListener<Department> resp, ResponseCallbackListener.ResponseCallbackErrorListener err) {

        final JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                API_URL + "/" + departmentId,
                null,
                response -> resp.onResponse(new Gson().fromJson(response.toString(), Department.class)),
                error -> err.onError(error.toString())
        );
        QueuePattern.getInstance(this.context).addToRequestQueue(request);

    }

    @Override
    public void save(Department department, ResponseCallbackListener.ResponseCallbackSuccessListener<Department> resp, ResponseCallbackListener.ResponseCallbackErrorListener err) {

        final Map<String, Object> map = new HashMap<>();
        map.put("departmentName", department.getDepartmentName());
        map.put("location", department.getLocation());

        final JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                API_URL,
                new JSONObject(map),
                response -> resp.onResponse(new Gson().fromJson(response.toString(), Department.class)),
                error -> err.onError(error.toString())
        );
        QueuePattern.getInstance(this.context).addToRequestQueue(request);

    }

    @Override
    public void update(Department department, ResponseCallbackListener.ResponseCallbackSuccessListener<Department> resp, ResponseCallbackListener.ResponseCallbackErrorListener err) {

        final Map<String, Object> map = new HashMap<>();
        map.put("departmentId", department.getDepartmentId());
        map.put("departmentName", department.getDepartmentName());
        map.put("location", department.getLocation());

        final JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.PUT,
                API_URL,
                new JSONObject(map),
                response -> resp.onResponse(new Gson().fromJson(response.toString(), Department.class)),
                error -> err.onError(error.toString())
        );
        QueuePattern.getInstance(this.context).addToRequestQueue(request);

    }

    @Override
    public void deleteById(Integer departmentId, ResponseCallbackListener.ResponseCallbackSuccessListener<Boolean> resp, ResponseCallbackListener.ResponseCallbackErrorListener err) {

        final JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.DELETE,
                API_URL + "/" + departmentId,
                null,
                response -> resp.onResponse(new Gson().fromJson(response.toString(), Boolean.class)),
                error -> err.onError(error.toString())
        );
        QueuePattern.getInstance(this.context).addToRequestQueue(request);

    }



}
