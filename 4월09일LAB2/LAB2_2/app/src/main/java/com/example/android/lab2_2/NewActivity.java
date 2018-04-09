package com.example.android.lab2_2;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class NewActivity extends AppCompatActivity {
    public TextView text1;
    public Button goBtn;
    public Button backBtn;
    public String myURL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        text1 = findViewById(R.id.text1);//xml에서 id text1을 text1에 지정한다
        goBtn = findViewById(R.id.button2);//xml에서 id button2을 goBtn에 지정한다
        backBtn =findViewById(R.id.button3);//xml에서 id button3을 backBtn에 지정한다
        Intent newIntent = getIntent();//MainActivity에서 지목한 intent를 가져온다
        myURL = newIntent.getStringExtra("url");//url태크로 넘어온 값을 myURL에 저장한다

        text1.setText(myURL);//넘어온 주소값을 보여준다
        goBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!myURL.equals("")) {//값이 적혀있으면
                    //http:// + 넘겨온 주소값으로 웹페이지가 켜진다
                    Intent intent2 = new Intent(Intent.ACTION_VIEW, Uri.parse("http://" + myURL));
                    startActivity(intent2);
                }
                else {//아무것도 안써있으면
                    //토스트메시지로 "주소를다시입력해주세요"가 나온다
                    Toast.makeText(getApplicationContext(), "주소를다시입력해주세요.", Toast.LENGTH_LONG).show();
                }
            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //back버튼을 누르면 토스트 메시지로 "뒤로가기버튼을눌렀습니다"가 나온고 MainActivity로 간다
                Toast.makeText(getApplicationContext(),"뒤로가기버튼을눌렀습니다.",Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }
}
