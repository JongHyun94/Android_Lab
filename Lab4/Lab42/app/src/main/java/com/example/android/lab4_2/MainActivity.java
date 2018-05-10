
package com.example.android.lab4_2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
    RelativeLayout layout1;
    RelativeLayout layout2;
    Button openBtn;
    Button closeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 초기화
        layout1 = findViewById(R.id.containO);
        layout2 = findViewById(R.id.containC);
        openBtn = findViewById(R.id.buttonOpen);
        closeBtn = findViewById(R.id.buttonClose);

        //오픈 페이지 버튼 클릭시
        openBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(
                        getApplicationContext(), R.anim.translate);
                // 안보였던 Sliding Area 보이게 하고
                layout2.setVisibility(View.VISIBLE);
                // 애니메이션 작동
                layout2.startAnimation(anim);
                Animation anim2 = AnimationUtils.loadAnimation(
                        getApplicationContext(), R.anim.stop);
                // 버튼의 딜레이를 넣어줌
                openBtn.startAnimation(anim2);
                // 오픈 페이지 버튼 사라짐
                openBtn.setVisibility(View.GONE);
            }
         });

        // 페이지 닫기 버튼 클릭시
        closeBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(
                        getApplicationContext(), R.anim.translate2);
                // 페이지 다시 돌아가는 애니메이션
                layout2.startAnimation(anim);
                // Sliding Area 안보이게 하기
                layout2.setVisibility(View.GONE);
                // 안보였던 오픈 버튼 보이게하기
                openBtn.setVisibility(View.VISIBLE);
            }
        });
     }
}
