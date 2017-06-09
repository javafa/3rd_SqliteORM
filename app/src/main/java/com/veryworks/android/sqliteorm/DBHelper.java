package com.veryworks.android.sqliteorm;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by pc on 6/9/2017.
 */

public class DBHelper extends OrmLiteSqliteOpenHelper {
    public static final String DATABASE_NAME = "database.db";
    public static final int DATABASE_VERSION = 3;

    private static DBHelper instance = null;

    // DBHelper 를 메모리에 하나만 있게 해서 효율을 높혀보세~~~~
    // Singleton 으로 구성해보세.
    public static DBHelper getInstance(Context context){
        if(instance == null) {
            instance = new DBHelper(context);
        }
        return instance;
    }


    // 최초 호출될때 database.db 파일을 /data/data/패키지명/database/ 디렉토리 아래에 생성해준다.
    private DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // 최초에 생성되면 버전체크를해서 onCreate 를 호출한다.
    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        // 여기서 테이블 생성해야 한다.
        try {
            TableUtils.createTable(connectionSource, Memo.class);
            TableUtils.createTable(connectionSource, Bbs.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 데이터 베이스 버전이 업그레이드 되면 호출된다.
    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
            // Memo 테이블의 특정 컬럼만 변경되었을 경우
            // 1. 기존 테이블을 백업하기 위한 임시테이블을 생성하고 데이터를 담아둔다.
            //    예) create table temp_memo,   <- 기존데이터
            // 2. Memo 테이블을 삭제하고 다시 생성한다.
            // 3. 백업된 데이터를 다시 입력합니다.
            // 4. 임시 테이블 삭제
        try {
            TableUtils.createTable(connectionSource, Bbs.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Create - 데이터를 입력
    public void create(Memo memo){
        try {
            // 1. 테이블에 연결
            Dao<Memo,Integer> dao = getDao(Memo.class);
            // 2. 데이터를 입력
            dao.create(memo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // Read All
    public List<Memo> readAll(){
        List<Memo> datas = null;
        try {
            // 1. 테이블에 연결
            Dao<Memo,Integer> dao = getDao(Memo.class);
            // 2 데이터 전체 읽어오기
            datas = dao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return datas;
    }
    // read one
    public Memo read(int memoid){
        Memo memo = null;
        try {
            // 1. 테이블에 연결
            Dao<Memo,Integer> dao = getDao(Memo.class);
            // 2 데이터 한개 읽어오기
            memo = dao.queryForId(memoid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return memo;
    }
    // search - 데이터 검색하기
    public List<Memo> search(String word){
        List<Memo> datas = null;
        try {
            // 1. 테이블에 연결
            Dao<Memo,Integer> dao = getDao(Memo.class);
            // 2 데이터 검색하기
            String query = "select * from memo where content like '%"+word+"%'";
            GenericRawResults<Memo> temps = dao.queryRaw(query, dao.getRawRowMapper());
            datas = temps.getResults();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return datas;
    }

    // Update
    public void update(Memo memo){
        try {
            // 1. 테이블에 연결
            Dao<Memo,Integer> dao = getDao(Memo.class);
            // 2. 데이터를 수정
            dao.update(memo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // Delete Object
    public void delete(Memo memo){
        try {
            // 1. 테이블에 연결
            Dao<Memo,Integer> dao = getDao(Memo.class);
            // 2. 데이터를 삭제
            dao.delete(memo);
            // * 참고 : 아이디로 삭제
            // dao.deleteById(3);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // Delete By Id
    public void delete(int id){
        try {
            // 1. 테이블에 연결
            Dao<Memo,Integer> dao = getDao(Memo.class);
            // 2. 데이터를 삭제
            dao.deleteById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
