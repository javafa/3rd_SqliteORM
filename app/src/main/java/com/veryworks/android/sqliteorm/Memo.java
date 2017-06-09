package com.veryworks.android.sqliteorm;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Created by pc on 6/9/2017.
 */
@DatabaseTable(tableName = "memo")
public class Memo {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private String title;
    @DatabaseField
    private String content;
    @DatabaseField
    private Date date;

    public Memo(){
        // OrmLite는 기본생성자가 없으면 동작하지 않습니다.
        setDate();
    }

    public Memo(String title, String content){
        this.title = title;
        this.content = content;
        setDate();
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    private void setDate() {
        Date date = new Date(System.currentTimeMillis());
        this.date = date;
    }
}
