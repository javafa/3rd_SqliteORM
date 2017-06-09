package com.veryworks.android.sqliteorm;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by pc on 6/9/2017.
 */

public class DBHelper extends OrmLiteSqliteOpenHelper {
    public static final String DATABASE_NAME = "database.db";
    public static final int DATABASE_VERSION = 2;

    // 최초 호출될때 database.db 파일을 /data/data/패키지명/database/ 디렉토리 아래에 생성해준다.
    public DBHelper(Context context) {
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
        try {
            // Memo 테이블의 특정 컬럼만 변경되었을 경우
            // 1. 기존 테이블을 백업하기 위한 임시테이블을 생성하고 데이터를 담아둔다.
            //    예) create table temp_memo,   <- 기존데이터
            // 2. Memo 테이블을 삭제하고 다시 생성한다.
            // 3. 백업된 데이터를 다시 입력합니다.
            // 4. 임시 테이블 삭제

            TableUtils.createTable(connectionSource, Bbs.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
