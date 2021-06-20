package com.selimhorri.pack;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.selimhorri.pack.constant.RoleEnum;
import com.selimhorri.pack.model.dto.custom.AuthenticationRequest;
import com.selimhorri.pack.service.AuthenticationService;
import com.selimhorri.pack.service.CredentialService;
import com.selimhorri.pack.service.impl.dynmc.AuthenticationServiceDynamicImpl;
import com.selimhorri.pack.service.impl.dynmc.CredentialServiceDynamicImpl;

public class LoginActivity extends AppCompatActivity {

    private final AuthenticationService authenticationService;
    private final CredentialService credentialService;

    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button btnLogin;

    public LoginActivity() {
        this.authenticationService = new AuthenticationServiceDynamicImpl(LoginActivity.this);
        this.credentialService = new CredentialServiceDynamicImpl(LoginActivity.this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.editTextUsername = super.findViewById(R.id.idEditTextUsername);
        this.editTextPassword = super.findViewById(R.id.idEditTextPassword);
        this.btnLogin = super.findViewById(R.id.idBtnLogin);

        this.btnLogin.setOnClickListener(v -> {

            final Bundle extras = super.getIntent().getExtras();
            final String username = this.editTextUsername.getText().toString().trim();
            final String password = this.editTextPassword.getText().toString();

            if (isEmpty(username, password)) {
                Toast.makeText(LoginActivity.this, "username/password is/are empty!", Toast.LENGTH_SHORT).show();
            }
            else {
                // TODO: NOT correctly implemented
                this.authenticationService.authenticate(
                        new AuthenticationRequest(username, password),
                        response -> {
                            this.credentialService.findByUsername(
                                    response.getUsername(),
                                    respCredential -> {
                                        Toast.makeText(LoginActivity.this, respCredential.toString(), Toast.LENGTH_SHORT).show();
                                    },
                                    errCredential -> {
                                        Toast.makeText(LoginActivity.this, errCredential.toString(), Toast.LENGTH_SHORT).show();
                                    }
                            );
                        },
                        error -> {
                            Toast.makeText(LoginActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                        }
                );
            }

        });

    }

    private static boolean isEmpty(final String username, final String password) {
        return username.isEmpty() || password.isEmpty();
    }



}










