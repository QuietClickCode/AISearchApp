package aisearch.zjj.com.aisearchapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import aisearch.zjj.com.aisearchapp.pojo.Item;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SecondActivity extends AppCompatActivity {

    String keyword;

    ListView responseText;

    private List<Item> itemList = new ArrayList<>();
    String[] stringsTitle = null;
    String[] stringsId = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        Intent intent = getIntent();
        Bundle data = intent.getBundleExtra("data");
        keyword = (String) data.get("keyword");
        sendRequestWithOkHttp(keyword);


    }

    //这个地方的handler最好封装 防止内存泄漏
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            ListView listView = findViewById(R.id.response_text);
            ItemAdapter itemAdapter = new ItemAdapter(SecondActivity.this, R.layout.list_item, itemList);

            listView.setAdapter(itemAdapter);
        }
    };

    private void sendRequestWithOkHttp(String keyword) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    /*Request request = new Request.Builder().url("https://www.myznsh.com/searchcsdn?wd=%E7%88%B1%E6%83%85").build();*/
                    Request request = new Request.Builder().url("https://www.myznsh.com/searchcsdn?wd=" + keyword).build();
                    Call call = client.newCall(request);
                    call.enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            System.out.println("Fail");
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {

                            String responseData = response.body().string();
                            JSONObject jsonObject = null;
                            try {
                                jsonObject = (JSONObject) JSONObject.parse(responseData);
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
                                ArrayList<String> arrayListId = new ArrayList<>();
                                JSONObject jsonObject1;

                                for (String s : strings) {
                                    jsonObject1 = (JSONObject) JSONObject.parse(s);
                                    String title = (String) jsonObject1.get("title");
                                    String id = (String) jsonObject1.get("id");
                                    String content = (String) jsonObject1.get("content");
                                    Item item = new Item(id, title, content);
                                    itemList.add(item);
                                    arrayListTitle.add(title);
                                    arrayListId.add(id);
                                }
                                int sizeTitle = arrayListTitle.size();

                                stringsTitle = arrayListTitle.toArray(new String[sizeTitle]);
                                int sizeId = arrayListId.size();

                                stringsId = arrayListTitle.toArray(new String[sizeId]);
                                Message msg = new Message();
                                handler.sendMessage(msg);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    });


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
                    ArrayList<String> arrayListId = new ArrayList<>();
                    JSONObject jsonObject1;

                    for (String s : strings) {
                        jsonObject1 = (JSONObject) JSONObject.parse(s);
                        String title = (String) jsonObject1.get("title");
                        String id = (String) jsonObject1.get("id");
                        arrayListTitle.add(title);
                        arrayListId.add(id);
                    }
                    int sizeTitle = arrayListTitle.size();

                    stringsTitle = arrayListTitle.toArray(new String[sizeTitle]);
                    int sizeId = arrayListId.size();

                    stringsId = arrayListTitle.toArray(new String[sizeId]);
                    Message msg = new Message();
                    handler.sendMessage(msg);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                /*responseText.setText(response);*/
                /*responseText.setText(keyword);*/
            }
        });
    }

}
