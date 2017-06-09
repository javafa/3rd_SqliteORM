package com.veryworks.android.sqliteorm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHelper helper = new DBHelper(this);
        // 1. 데이터 입력
//        for(int i=0 ; i<10 ; i++) {
//            Memo memo = new Memo();
//            memo.setTitle("제목");
//            memo.setContent("내용");
//            helper.create(memo);
//        }

        // 2. 데이터 한개 읽어오기
//        Memo one = helper.read(3);
//        Log.i("Memo",one.getId()+" : title="+one.getTitle()+", content="+one.getContent());

        // 3. 데이터 전체 읽어오기
        List<Memo> datas = helper.readAll();
        for(Memo one : datas){
            Log.i("Memo",one.getId()+" : title="+one.getTitle()+", content="+one.getContent());
        }
    }
}
