package com.example.android.lab5_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ImageView imageView1;
    ImageView imageView2;
    EditText editText;
    Button button;
    Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 초기화부분
        setContentView(R.layout.activity_main);
        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);

        // 동작변경 버튼 누를시
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DogThread thread1 = new DogThread(0);
                thread1.start(); // 쓰레드1 시작
                DogThread thread2 = new DogThread(1);
                thread2.start(); // 쓰레드2 시작
            }
        });
    }
    class DogThread extends Thread {
        int dogIndex;
        int stateIndex;
        ArrayList<Integer> images = new ArrayList<Integer>();

        public DogThread(int index) {
            dogIndex = index; // 0 or 1 인덱스 가진다.
            // 이미지 소스 추가
            images.add(R.drawable.dog_eating);
            images.add(R.drawable.dog_standing);
            images.add(R.drawable.dog_study);
        }

        public void run() {
            stateIndex = 0;
            for (int i = 0; i < 9; i++) { // 9번
                final String msg = "dog #" + dogIndex + " state: " + stateIndex;
                handler.post(new Runnable() {
                    public void run() {
                        editText.append(msg + "\n");
                        if (dogIndex == 0) {
                            imageView1.setImageResource(images.get(stateIndex));
                        } else if (dogIndex == 1) {
                            imageView2.setImageResource(images.get(stateIndex));
                        }
                    }
                });
                try {
                    int sleepTime = getRandomTime(500, 3000); //최소 500ms ~ 최대 3000 ms
                    Thread.sleep(sleepTime); // sleepTime만큼 슬립
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                stateIndex++;
                if (stateIndex >= images.size()) {
                    stateIndex = 0; // 초기화
                }
            }
        }
    }
    // 랜덤시간 주어짐
    public int getRandomTime(int min, int max){
        return min+(int)(Math.random() * (max-min));
    }
}