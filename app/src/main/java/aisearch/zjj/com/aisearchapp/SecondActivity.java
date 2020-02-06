package aisearch.zjj.com.aisearchapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SecondActivity extends AppCompatActivity {

    String keyword;

    ListView responseText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent intent = getIntent();
        Bundle data = intent.getBundleExtra("data");
        keyword = (String) data.get("keyword");
        sendRequestWithOkHttp(keyword);


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

    //这儿转换好复杂,还是js直接点点点过去好
    private void showResponse(final String response) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                JSONObject jsonObject = null;
                try {
                    jsonObject = (JSONObject) JSONObject.parse(response);
                    jsonObject = (JSONObject) jsonObject.get("data");
                    JSONArray list = jsonObject.getJSONArray("list");
                    Object[] objects = list.toArray();
                    ArrayList<String> arrayList = new ArrayList<>();
                    for (Object s : objects) {
                        arrayList.add(s.toString());
                    }

                    int size = arrayList.size();

                    String[] strings = arrayList.toArray(new String[size]);
                    ArrayList<String> arrayListTitle = new ArrayList<>();
                    JSONObject jsonObject1;
                    for (String s : strings) {
                        jsonObject1 = (JSONObject) JSONObject.parse(s);
                        String title = (String) jsonObject1.get("content");
                        arrayListTitle.add(title);
                    }
                    int sizeTitle = arrayListTitle.size();

                    String[] stringsTitle = arrayListTitle.toArray(new String[sizeTitle]);
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(SecondActivity.this, android.R.layout.simple_list_item_1, stringsTitle);
                    responseText = findViewById(R.id.response_text);
                    responseText.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                /*responseText.setText(response);*/
                /*responseText.setText(keyword);*/
            }
        });
    }

}
