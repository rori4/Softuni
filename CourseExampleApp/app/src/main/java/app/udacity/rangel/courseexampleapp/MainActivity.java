package app.udacity.rangel.courseexampleapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {


    public void changeFunction(View view) throws IOException {

        URL url = new URL("http://127.0.0.1:8080/");
            URLConnection uc = url.openConnection();
            String userpass = ":123456";
            String basicAuth = Arrays.toString(Base64.encode(userpass.getBytes(), Base64.DEFAULT));
            uc.setRequestProperty ("Authorization", basicAuth);
            Toast.makeText(this, "YES!!!!!!!", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


}
