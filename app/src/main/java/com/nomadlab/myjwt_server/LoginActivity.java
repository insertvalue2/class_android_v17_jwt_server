package com.nomadlab.myjwt_server;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.nomadlab.myjwt_server.repository.JwtService;
import com.nomadlab.myjwt_server.repository.models.request.ReqLogin;
import com.nomadlab.myjwt_server.repository.models.response.Login;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText loginEt = findViewById(R.id.loginEt);
        EditText passwordEt = findViewById(R.id.passwordEt);
        Button loginBtn = findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(view -> {
            JwtService jwtService = JwtService.retrofit.create(JwtService.class);
            jwtService.getLogin(new ReqLogin(loginEt.getText().toString(), passwordEt.getText().toString())).enqueue(new Callback<Login>() {
                @Override
                public void onResponse(Call<Login> call, Response<Login> response) {
                    hideKeyboard(view.getContext(), view);
                    Log.d(TAG, "header : " + response.headers().get("Authorization"));
                    SharedPreferences preferences = getSharedPreferences("token", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("key", response.headers().get("Authorization"));
                    editor.apply();
                    Login login = response.body();
                    Snackbar.make(view, login.getMsg(), Snackbar.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<Login> call, Throwable t) {

                }
            });
        });
        TextView movePageSignup = findViewById(R.id.moveSignupTv);
        movePageSignup.setOnClickListener(view -> {
            Intent intent = new Intent(this, SignupActivity.class);
            startActivity(intent);
        });
    }

    public void hideKeyboard(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


}