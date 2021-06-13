package com.selimhorri.pack.service;

import com.selimhorri.pack.listener.ResponseCallbackListener;
import com.selimhorri.pack.model.collection.DtoCollection;
import com.selimhorri.pack.model.dto.Assignment;
import com.selimhorri.pack.model.id.AssignmentId;

import java.time.LocalDateTime;

public interface AssignmentService {

    void findAll(final ResponseCallbackListener.ResponseCallbackSuccessListener<DtoCollection<Assignment>> resp, final ResponseCallbackListener.ResponseCallbackErrorListener err);
    void findById(final AssignmentId assignmentId, final ResponseCallbackListener.ResponseCallbackSuccessListener<Assignment> resp, final ResponseCallbackListener.ResponseCallbackErrorListener err);
    void save(final Assignment assignment, final ResponseCallbackListener.ResponseCallbackSuccessListener<Assignment> resp, final ResponseCallbackListener.ResponseCallbackErrorListener err);
    void update(final Assignment assignment, final ResponseCallbackListener.ResponseCallbackSuccessListener<Assignment> resp, final ResponseCallbackListener.ResponseCallbackErrorListener err);
    void deleteById(final AssignmentId assignmentId, final ResponseCallbackListener.ResponseCallbackSuccessListener<Boolean> resp, final ResponseCallbackListener.ResponseCallbackErrorListener err);

}
