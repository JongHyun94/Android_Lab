package com.example.android.lab6_3;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

    SQLiteDatabase myDB;  //Database
    MySQLiteOpenHelper helper;//Database's helper
    EditText name;      //입력한 이름.
    EditText stdNumber; //입력한 학번.

    Button addButton;    //추가 버튼
    Button deleteButton; //삭제 버튼
    String stringName;   //EditText에서 얻어온 이름 값을 임시 저장하기 위한 변수.
    int stringNumber;    //EditText에서 얻어온 학번 값을 임시 저장하기 위한 변수.
    String [] list;      //지금 까지 기록한 정보들이 저장되기 위한 list
    ListView stdList;    // 학생 정보를 넣을 ListView 변수.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 데이터 베이스 생성
        helper = new MySQLiteOpenHelper(MainActivity.this, "person1.db", null, 1);

        // 초기화
        name = findViewById(R.id.name);
        stdNumber = findViewById(R.id.number);
        stdList = findViewById(R.id.list1);
        addButton = findViewById(R.id.addBtn);
        deleteButton= findViewById(R.id.deleteBtn);

        // Add 버튼 입력시
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(name.getText().toString().equals("") || stdNumber.getText().toString().equals("")) {//이름 또는 학번 둘중 하나라도 입력을 안했다면
                    // 알림 메시지 하나라도 안적은 경우
                    Toast.makeText(getApplicationContext(), "모든 항목을 입력해 주세요", Toast.LENGTH_LONG).show();
                }
                else{
                stringName = name.getText().toString();                          // 이름을 저장 stringName에
                stringNumber = Integer.parseInt(stdNumber.getText().toString()); // 학번을 저장 stringNumber에
                insert(stringName, stringNumber);                                //insert 함수 호출.
                invalidate();
                name.setText("");     // 이름 초기화
                stdNumber.setText("");// 학번 초기화
                }

            }
        });

        // Delete 버튼 입력시
        deleteButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(name.getText().toString().equals("")) { //이름칸이 비어져 있을경우,
                    Toast.makeText(getApplicationContext(), "이름을 입력해 주세요", Toast.LENGTH_LONG).show();
                }
                else{
                    delete(name.getText().toString());  // 등록된 이름과 비교해 delete 함수 호출.
                    invalidate();
                    name.setText("");        // 이름 초기화
                    stdNumber.setText("");   // 학번 초기화
                }
            }
        });
    }

    public void select() {
        myDB=helper.getReadableDatabase(); // 데이터베이스를 호출
        Cursor c = myDB.query("student",null,null,null,null,null,null);
        list = new String[c.getCount()];//table 에 있는 만큼 listView 변수에 저장하기 위해 공간 할당

        int count = 0; // 초기번호
        while(c.moveToNext()){//table 에 있는 만큼 하나하나 String 배열에 저장
            list[count] = c.getString(c.getColumnIndex("name")) + " " + c.getString(c.getColumnIndex("studentNo"));
            count++; // 순서올려준다
        }
        c.close(); // cursor 종료.
    }

    public void insert(String name, int studentNo) {
        // 데이터베이스 호출
        myDB = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",name);
        values.put("studentNo",studentNo);
        myDB.insert("student",null,values);
    }

    public void delete(String name) {
        // 데이터베이스 호출
        myDB = helper.getWritableDatabase();
        // student라는 테이블에 해당 name 의 이름이 있다면 삭제
        myDB.delete("student", "name=?", new String[]{name});
    }

    private void invalidate(){
        select();
        ArrayAdapter<String>adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
        stdList.setAdapter(adapter); //listView에 호출됨
    }

}

