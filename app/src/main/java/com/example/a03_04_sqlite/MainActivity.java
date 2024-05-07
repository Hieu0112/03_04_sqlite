package com.example.a03_04_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.a03_04_sqlite.database.BanHang;
import com.example.a03_04_sqlite.model.Category;
import com.example.a03_04_sqlite.model.item;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Spinner sp;
    TextView tv;
    Button bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sp=findViewById(R.id.sp);
        tv=findViewById(R.id.txt);
        bt=findViewById(R.id.bt);
        BanHang db=new BanHang(getApplicationContext());
//        db.insertCate(new Category("Sua"));
//        db.insertCate(new Category("Keo"));
//        db.insertCate(new Category("Cafe"));
//        db.insertCate(new Category("Banh"));
        List<Category>list=db.getAllCate();
        String[] a=new String[list.size()];
        for (int i=0;i<list.size();i++)
                a[i]=list.get(i).getId()+"-"+list.get(i).getName();
        ArrayAdapter<String>adapter=new ArrayAdapter<>(this,R.layout.item,a);
        sp.setAdapter(adapter);
//        db.insertItem(new item(
//                "Sua 1",120,"10/09/2022",
//                new Category(1,"Sua")
//        ));
//        db.insertItem(new item(
//                "Sua 2",150,"10/8/2022",
//                new Category(1,"Sua")
//        ));
//        db.insertItem(new item(
//                "Keo 1",20,"06/02/2022",
//                new Category(2,"Keo")
//        ));
//        db.insertItem(new item(
//                "Cafe",110,"09/02/2022",
//                new Category(3,"Cafe2")
//        ));
//        db.insertItem(new item(
//                "Banh 1",60,"06/04/2022",
//                new Category(4,"Banh")
//        ));
//        List<item> list2=db.getAllItem();
//        int number=(int)sp.getSelectedItemId()+1;
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int number=(int)sp.getSelectedItemId();
                List<item>list2=db.getAllItemByCateID(number+1);
                String res="Danh sach san pham "+"\n";
                for(item x:list2){
                    res=res+x.getId()+" - "+x.getName()+" - "+x.getPrice()+"\n";
                }
                tv.setText(res);
            }
        });
    }
}