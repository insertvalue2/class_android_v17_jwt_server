package com.nomadlab.myjwt_server;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.nomadlab.myjwt_server.repository.JwtService;
import com.nomadlab.myjwt_server.repository.models.request.ReqLogin;
import com.nomadlab.myjwt_server.repository.models.response.ResLogin;
import com.nomadlab.myjwt_server.utils.KeyboardUtil;

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
            jwtService.getLogin(new ReqLogin(loginEt.getText().toString(), passwordEt.getText().toString())).enqueue(new Callback<ResLogin>() {
                @Override
                public void onResponse(Call<ResLogin> call, Response<ResLogin> response) {
                    KeyboardUtil.hideKeyboard(view.getContext(), view);
                    Log.d(TAG, "header : " + response.headers().get("Authorization"));
                    SharedPreferences preferences = getSharedPreferences("token", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("jwt", response.headers().get("Authorization"));
                    editor.apply();
                    ResLogin resLogin = response.body();
                    Snackbar.make(view, resLogin.getMsg(), Snackbar.LENGTH_SHORT).show();

                    Intent intent = new Intent(view.getContext(), MainActivity.class);
                    intent.putExtra("msg", resLogin.getMsg());
                    startActivity(intent);
                }

                @Override
                public void onFailure(Call<ResLogin> call, Throwable t) {

                }
            });
        });
        TextView movePageSignup = findViewById(R.id.moveLoginTv);
        movePageSignup.setOnClickListener(view -> {
            Intent intent = new Intent(this, SignupActivity.class);
            startActivity(intent);
        });
    }



}