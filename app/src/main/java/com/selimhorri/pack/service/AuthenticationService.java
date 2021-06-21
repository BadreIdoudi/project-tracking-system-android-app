package com.selimhorri.pack.service;

import com.selimhorri.pack.listener.ResponseCallbackListener;
import com.selimhorri.pack.model.collection.DtoCollection;
import com.selimhorri.pack.model.dto.custom.AuthenticationRequest;
import com.selimhorri.pack.model.dto.custom.AuthenticationResponse;

public interface AuthenticationService {

    void authenticate(final AuthenticationRequest authenticationRequest, final ResponseCallbackListener.ResponseCallbackSuccessListener<AuthenticationResponse> resp, final ResponseCallbackListener.ResponseCallbackErrorListener err);

}
