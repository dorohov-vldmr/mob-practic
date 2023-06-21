package ru.rabiarill.practic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class Reg extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
    }

    public void onCancelClick(View v) {finish();}

    public void on_OkClick(View v)
    {
        EditText txtuser = findViewById(R.id.reg_username);
        EditText txtpass = findViewById(R.id.reg_password);

        String args = "?name=" + txtuser.getText().toString()
                + "&secret=" + txtpass.getText().toString();

        new ApiCall(this, "PUT", "account" + args)
        {
            public void on_ready(String result)
            {
                try {
                    JSONObject answer = new JSONObject(result);
                    if (answer.getString("status").equals("ok"))
                    {
                        finish();
                    }
                    else
                    {
                        on_fail();
                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }

            public void on_fail()
            {
                Toast t = Toast.makeText(Reg.this, "User already exist", Toast.LENGTH_SHORT);
                t.show();
            }
        };
    }
}