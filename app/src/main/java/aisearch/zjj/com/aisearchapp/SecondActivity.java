package aisearch.zjj.com.aisearchapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SecondActivity extends AppCompatActivity {
    TextView responseText;

    String keyword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent intent = getIntent();
        Bundle data = intent.getBundleExtra("data");
        keyword = (String) data.get("keyword");
        sendRequestWithOkHttp(keyword);
        responseText = findViewById(R.id.response_text);
    }

    private void sendRequestWithOkHttp(String keyword) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    /*Request request = new Request.Builder().url("https://www.myznsh.com/searchcsdn?wd=%E7%88%B1%E6%83%85").build();*/
                    Request request = new Request.Builder().url("https://www.myznsh.com/searchcsdn?wd=" + keyword).build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    showResponse(responseData);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void showResponse(final String response) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                responseText.setText(response);
                /*responseText.setText(keyword);*/
            }
        });
    }

}
