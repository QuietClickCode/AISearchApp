package aisearch.zjj.com.aisearchapp;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class FirstActivity extends AppCompatActivity {

    EditText editText;
    private TextToSpeech mTextToSpeech;
    private void speakChina(){
        //设置朗读语言
        mTextToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {

            @Override
            public void onInit(int status) {
                mTextToSpeech.setSpeechRate(1);
                mTextToSpeech.setPitch(1);
                mTextToSpeech.speak("\n" +
                        " \n" +
                        " \n" +
                        "12米以下，这个数字比较的合理。一般来说，板式多层或小\n" +
                        "10.5米至12米之间比较理想。 \n" +
                        "楼间距 \n" +
                        "品质就越高，私密性就越好。许多楼盘的沙盘模型并没有呈现\n" +
                        "\n" +
                        "1小时（房子最底层窗户）为标准。间距是用建筑物室外坪至房屋檐口的高度/tan(a) a-\n" +
                        " \n" +
                        " \n" +
                        "80%以上，高层由于电梯间和消防通道的面积大，一般在70%左右。\n" +
                        "得房率越高越好，但公摊如果太小，生活舒适度会降低，因为这样的项目一般会\n" +
                        " \n" +
                        " \n" +
                        "80平方米左右的住宅，其\n" +
                        "3.6米~3.9米左右；120平方米的住宅，客厅开间在3.9米~4.2米之间。 \n" +
                        " \n" +
                        "如阳台。阳台分为封闭和半封闭两种，封闭阳台要\n" +
                        " \n" +
                        "18大诀窍\n" +
                        "\n" +
                        "开盘绝对没有好房子，好的房子全部被保留，然后每个月推出几套，但是单价升得很\n" +
                        " \n" +
                        "大家购房所付的保险费其实是可以打八五折的，不要在售楼处买保单，外面的保险公\n" +
                        " \n" +
                        "广告没有一个是真的，千万别相信其中的外立面的颜色，很多造出来比画的难看多了。 \n" +
                        "售楼员会用许多的方法来逼你买房，让你无时无刻感到紧张，这时你千万要冷静。要\n" +
                        " \n" +
                        "别以为高层中的九到十一楼不错，那你大错了，这些楼层正好是扬灰层，脏空气到这\n" +
                        " \n" +
                        "别对景观抱太大希望，树和草是在交房前一个月从外地买来直接插土进去的，所以能\n" +
                        " \n" +
                        "别以为面砖的外墙是好的，其实面砖漏水比涂料漏水的几率大的多了，在国外都是用\n" +
                        " \n" +
                        "别以为实测面积是对的，其实测绘局都被买通的，少你一个平米你也看不出来，但是\n" +
                        " \n" +
                        "绿化率，容积率大多都是与实际不相符的，千万别相信。 \n" +
                        "漏水和外立面的材料根本没关系，你们要关心的是桩有多深，因为新房漏水大多是\n" +
                        " \n" +
                        "开盘的时候售楼处会有许多四五十岁的人在模型边上说这个房子好，千万别信，这\n" +
                        " \n" +
                        "注意，有人代理公司来代理的楼盘他们通常花样最多，比如排队买号等，但是开发\n" +
                        " \n" +
                        "为何所有凸窗看上去很大，但是能通风的只有很小一扇，说是为了安全，其实是整\n" +
                        " \n" +
                        "如果报纸广告上的哪个楼盘单独印了一个房型，那你千万不要去买这个房型，不是\n" +
                        " \n" +
                        "到售楼处后直接问售楼员看一样叫销控的本子，这样你才可以确切的知道哪些房子\n" +
                        " \n" +
                        "一个好的施工单位关键是看他的工地是否干净，建材堆放是否井井有条 \n" +
                        "不要相信物业管理会是广告上的外资单位来管理，通常只买他们的一个名字，然后\n" +
                        "这些外资物业公司通常只为单价在一万以上的楼盘来服务，别的其实都由本地\n" +
                        " \n" +
                        "不要相信建筑设计是什么美国或加拿大的公司，这也是假的，国家规定外资设计单\n" +
                        "而必须是外加一个国内设计公司来共同设计，但是真\n" +
                        " \n",TextToSpeech.QUEUE_FLUSH,null);
                if (status==TextToSpeech.SUCCESS) {
                    //设置朗读语言
                    int supported= mTextToSpeech.setLanguage(Locale.US);
                    if ((supported!=TextToSpeech.LANG_AVAILABLE)&&(supported!=TextToSpeech.LANG_COUNTRY_AVAILABLE)) {
                        Toast.makeText(FirstActivity.this, "不支持当前语言！", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout);
        Button button1 = findViewById(R.id.button_1);
        editText = findViewById(R.id.aisearch_edittext);
        /*button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(FirstActivity.this, "You clicked Button 1", Toast.LENGTH_SHORT).show();
            }
        });*/
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Bundle bundle = new Bundle();
                String text = editText.getText().toString();
                bundle.putString("keyword", text);
                Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
                intent.putExtra("data", bundle);
                startActivity(intent);*/
                speakChina();
                mTextToSpeech.speak("这个填你想要转成语音的文字", TextToSpeech.QUEUE_FLUSH, null);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_item:
                Toast.makeText(this, "You clicked Add", Toast.LENGTH_SHORT).show();
                break;
            case R.id.remove_item:
                Toast.makeText(this, "You clicked Remove", Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }
}
