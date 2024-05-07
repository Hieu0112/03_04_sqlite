package com.example.a03_04_sqlite.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.a03_04_sqlite.model.Category;
import com.example.a03_04_sqlite.model.item;

import java.util.ArrayList;
import java.util.List;

public class BanHang extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "QLbanhang.db";
    private static final int DATABASE_VERSION = 1;

    public BanHang(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreateDB = "create table categories(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT)";
        db.execSQL(sqlCreateDB);
        sqlCreateDB = "create table items(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "cid INTEGER," +
                "price real," +
                "dateUpdate text," +
                "foreign key (cid) references categories(id))";
        db.execSQL(sqlCreateDB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertCate(Category c) {
        String sql = "insert into categories(name) values(?)";
        String[] args = {c.getName()};
        SQLiteDatabase st = getWritableDatabase();
        st.execSQL(sql, args);
    }

    public void insertCate2(Category c) {
        ContentValues v = new ContentValues();
        v.put("name", c.getName());
        SQLiteDatabase st = getWritableDatabase();
        st.insert("categories", null, v);
    }

    public void insertItem(item c) {
        String sql = "insert into items(name,cid,price,dateUpdate) values(?,?,?,?)";
        String[] args = {c.getName(), String.valueOf(
                c.getCategory().getId()),
                String.valueOf(c.getPrice()),
                c.getDateUpdate()
        };
        SQLiteDatabase st = getWritableDatabase();
        st.execSQL(sql, args);
    }

    public void insertItem2(item c) {
        ContentValues v = new ContentValues();
        v.put("name", c.getName());
        v.put("cid", c.getCategory().getId());
        v.put("price", c.getPrice());
        v.put("dateUpdate", c.getDateUpdate());
        SQLiteDatabase st = getWritableDatabase();
        st.insert("items", null, v);
    }

    public List<Category> getAllCate() {
        List<Category> list = new ArrayList<>();
        SQLiteDatabase st = getReadableDatabase();
        Cursor rs = st.query("categories", null,
                null, null, null, null, null
        );
        while (rs != null && rs.moveToNext()) {
            list.add(new Category(rs.getInt(0),
                    rs.getString(1)
            ));
        }
        return list;
    }


    public List<item> getAllItem() {
        List<item> list = new ArrayList<>();
        String sql = "select t.id,t.name,t.price,t.dateUpdate,c.id as cid" +
                ",c.name as cname from categories c inner join items t on (c.id=t.cid)";
        SQLiteDatabase st = getReadableDatabase();
        Cursor rs = st.rawQuery(sql, null);
        while (rs != null && rs.moveToNext()) {
            list.add(new item(rs.getInt(0),
                    rs.getString(1),
                    rs.getDouble(2),
                    rs.getString(3),
                    new Category(rs.getInt(4), rs.getString(4))
            ));
        }
        return list;
    }
    public List<item> getAllItemByCateID(int cid) {
        List<item> list = new ArrayList<>();
        String sql = "select t.id,t.name,t.price,t.dateUpdate,c.id as cid" +
                ",c.name as cname from categories c inner join items t on (c.id=t.cid) " +
                "where t.cid=?";
        String[] args={Integer.toString(cid)};
        SQLiteDatabase st = getReadableDatabase();
        Cursor rs = st.rawQuery(sql, args);
        while (rs != null && rs.moveToNext()) {
            list.add(new item(rs.getInt(0),
                    rs.getString(1),
                    rs.getDouble(2),
                    rs.getString(3),
                    new Category(rs.getInt(4), rs.getString(4))
            ));
        }
        return list;
    }
}
