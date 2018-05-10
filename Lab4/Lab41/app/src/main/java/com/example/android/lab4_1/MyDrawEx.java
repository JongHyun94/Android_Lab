package com.example.android.lab4_1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import java.util.ArrayList;

public class MyDrawEx extends View {
    // 위치정보를 저장할 배열을 만든다
    ArrayList<Vertex> myList = null;
    private Paint mPaint;

    //초기화
    public void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.BLUE); // 파란색 칠해줌
        mPaint.setStrokeWidth(10);   // 선크기
        mPaint.setAntiAlias(true);   // 둥글게 처리
    }

    public MyDrawEx(Context c) {
        super(c);
        if (myList == null)
            myList = new ArrayList<Vertex>();
        init();
    }

    public MyDrawEx(Context c, AttributeSet a) {
        super(c, a);
        if (myList == null)
            myList = new ArrayList<Vertex>();
        init();
    }

    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN            // 터치되면
                || event.getAction() == MotionEvent.ACTION_MOVE) {  // 터치해서 움직이면

            float x_point = event.getX();
            float y_point = event.getY();

            myList.add(new Vertex(x_point, y_point, false)); // 안때면 false
            invalidate();
        }
        if (event.getAction() == MotionEvent.ACTION_UP) {
            float x_point = event.getX();
            float y_point = event.getY();
            myList.add(new Vertex(x_point, y_point, true));  // 때면 true
        }

        return true;
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw((canvas));
        // 바탕화면 흰색으로 초기화
        canvas.drawColor(Color.WHITE);

        // 그리는 부분 어레이로 부터 가져온 정보로
        for (int i = 0; i < myList.size() - 1; i++) {
            float X = myList.get(i).x;
            float Y = myList.get(i).y;
            float x = myList.get(i + 1).x;
            float y = myList.get(i + 1).y;

            boolean isActionUp = myList.get(i).isActionUp;

            if (!isActionUp)
                canvas.drawLine(X, Y, x, y, mPaint);
            else
                canvas.drawPoint(x, y, mPaint);
        }

    }
    // Vertex 클래스
    public class Vertex {
        float x, y;
        boolean isActionUp;

        public Vertex(float x, float y, boolean isActionUp) {
            this.x = x;
            this.y = y;
            this.isActionUp = isActionUp;
        }
    }
}