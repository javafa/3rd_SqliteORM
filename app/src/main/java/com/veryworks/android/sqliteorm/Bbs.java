package com.veryworks.android.sqliteorm;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by pc on 6/9/2017.
 */

@DatabaseTable(tableName = "bbs")
public class Bbs {

    @DatabaseField
    private int id;
}
