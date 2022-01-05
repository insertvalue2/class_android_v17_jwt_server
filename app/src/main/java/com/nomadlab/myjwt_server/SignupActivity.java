package com.nomadlab.myjwt_server;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.nomadlab.myjwt_server.repository.JwtService;
import com.nomadlab.myjwt_server.repository.models.request.ReqSignup;
import com.nomadlab.myjwt_server.utils.KeyboardUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        EditText signupEt = findViewById(R.id.signupEt);
        EditText signupPasswordEt = findViewById(R.id.signupPasswordEt);
        EditText checkPasswordEt = findViewById(R.id.checkPasswordEt);
        EditText emailEt = findViewById(R.id.emailEt);

        Button signupBtn = findViewById(R.id.signupBtn);
        TextView moveSignupTv = findViewById(R.id.moveSignupTv);

        signupBtn.setOnClickListener(view -> {
            String id = signupEt.getText().toString();
            String pw = signupPasswordEt.getText().toString();
            String chPw = checkPasswordEt.getText().toString();
            String emil = emailEt.getText().toString();

            if (id.length() > 3 && pw.length() > 3 && emil.length() > 3) {
                KeyboardUtil.hideKeyboard(view.getContext(), view);
                Log.d("TAG", "isValidEmail : " + KeyboardUtil.isValidEmail(emil));
                if (!KeyboardUtil.isValidEmail(emailEt.getText())) {
                    Snackbar.make(view, "이메일 형식이 아닙니다.", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if (pw.equals(chPw)) {
                    JwtService jwtService = JwtService.retrofit.create(JwtService.class);
                    ReqSignup reqSignup = new ReqSignup(id, pw, emil);
                    jwtService.saveMember(reqSignup).enqueue(new Callback<ReqSignup>() {
                        @Override
                        public void onResponse(Call<ReqSignup> call, Response<ReqSignup> response) {
                            Snackbar.make(view, "회원가입 완료", Snackbar.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<ReqSignup> call, Throwable t) {
                            Snackbar.make(view, "회원가입 실패", Snackbar.LENGTH_SHORT).show();
                        }
                    });

                } else {

                    Snackbar.make(view, "비밀번가 달라요", Snackbar.LENGTH_SHORT).show();
                }
            } else {
                Snackbar.make(view, "잘못된 입력입니다.", Snackbar.LENGTH_SHORT).show();
            }
        });

        moveSignupTv.setOnClickListener(view -> {
            finish();
        });
    }

}