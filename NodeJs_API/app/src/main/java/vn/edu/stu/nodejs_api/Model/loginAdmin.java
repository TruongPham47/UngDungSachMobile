package vn.edu.stu.nodejs_api.Model;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import vn.edu.stu.nodejs_api.DAO.VolleyUtils;
import vn.edu.stu.nodejs_api.R;

public class loginAdmin extends AppCompatActivity {
    EditText user, pass;
    Button btnlog;
    VolleyUtils volleyUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_admin);
        volleyUtils = new VolleyUtils(loginAdmin.this, "http://192.168.110.15:3000/");
        addcontrol();
        addEvent();
    }

    private void addcontrol() {
        user = findViewById(R.id.editTextUsername);
        pass = findViewById(R.id.editTextPassword);
        btnlog = findViewById(R.id.buttonLogin);
    }

    private void addEvent() {
        btnlog.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(loginAdmin.this, "token", Toast.LENGTH_SHORT).show();
                JSONObject params = new JSONObject();
                try {
                    params.put("username", user.getText().toString());
                    params.put("password", pass.getText().toString());
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

                volleyUtils.makeJsonRequest("auth/login", params, new VolleyUtils.VolleyResponseListener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            String token = response.getString("token");
//                            Toast.makeText(loginAdmin.this, token, Toast.LENGTH_SHORT).show();
                            if (!token.equals("Invalid credentials")) {

                                Intent intent = new Intent(loginAdmin.this, menu.class);
                                intent.putExtra("token", token);
                                startActivity(intent);
                            } else {
                                Toast.makeText(loginAdmin.this, "Sai thông tin đăng nhập.", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(loginAdmin.this, menu.class);
                                startActivity(intent);
                            }
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }

                    }

                    @Override
                    public void onError(String errorMessage) {

                    }
                });
            }
        });
    }
}