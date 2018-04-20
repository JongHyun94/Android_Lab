package com.example.android.lab_3_1;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import static android.graphics.Color.*;

public class MainActivity extends Activity {
    Button mBtn;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtn = findViewById(R.id.button);
        registerForContextMenu(mBtn);
    }
    //Context Menu 만들기
    public void onCreateContextMenu (ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        //Context Menu 의 제목부분을 Button Menu로 지정한다.
        menu.setHeaderTitle("Button Menu");
        //차례로 빨-1 초-2 파-3 를 채워준다.
        menu.add(0,1,0,"Red");
        menu.add(0,2,0,"Green");
        menu.add(0,3,0,"Blue");
    }
    //메뉴의 아이템을 눌렀을때
    public boolean onContextItemSelected (MenuItem item) {
        switch (item.getItemId()) {
            case 1://Red를 눌렀을때
                mBtn.setTextColor(RED);
                return true;
            case 2://Green을 눌렀을때
                mBtn.setTextColor(GREEN);
                return true;
            case 3://Blue를 눌렀을때
                mBtn.setTextColor(BLUE);
                return true;
        }
        return true;
    }
}
