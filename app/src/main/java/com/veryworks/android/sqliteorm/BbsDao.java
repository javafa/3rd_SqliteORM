package com.veryworks.android.sqliteorm;

import android.content.Context;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by pc on 6/9/2017.
 */

public class BbsDao {
    private static BbsDao bbsDao = null;

    static Dao<Bbs,Integer> dao;

    public static BbsDao getInstance(Context context) {
        if(bbsDao == null)
            bbsDao = new BbsDao(context);
        return bbsDao;
    }

    private BbsDao(Context context){
        // 데이터베이스 연결
        DBHelper helper = DBHelper.getInstance(context);
        try {
            dao = helper.getDao(Bbs.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void create(Bbs bbs){
        try {
            dao.create(bbs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Bbs> read(){
        List<Bbs> datas = null;
        try {
            datas = dao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return datas;
    }

    public void update(Bbs bbs){

    }

    public void delete(Bbs bbs){

    }
}
