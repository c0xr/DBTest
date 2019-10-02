package com.cory.dbtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConstraintLayout root=(ConstraintLayout) ((ViewGroup)findViewById(android.R.id.content))
                .getChildAt(0);
        final Button bt=findViewById(R.id.button);
        Button bt2=findViewById(R.id.button2);
        final RecyclerView recyclerView=findViewById(R.id.recycler);
        final SQLiteDatabase db = new DBHelper(this).getWritableDatabase();


        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < 20; i++) {
                    db.execSQL("insert into employee (ssn, name, bdate, address, sex,salary,superssn,dno) " +
                            "values (" +
                            "'"+UUID.randomUUID().toString().substring(0,5)+"'," +
                            "'张三abc', '1990-12-12', 'address', 'f', 8000,'45678','d1');");
                }
            }
        });


        final GridAdapter gridAdapter;

        if(recyclerView.getAdapter()==null) {
            gridAdapter=new GridAdapter(this,8);
            recyclerView.setAdapter(gridAdapter);
            recyclerView.setLayoutManager(new GridLayoutManager(this,8));
            recyclerView.setHasFixedSize(true);
        }else{
            gridAdapter=(GridAdapter)(recyclerView.getAdapter());
        }


        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bt.setEnabled(false);

                String[] keys=new String[]{
                        "ssn","name","bdate","address","sex","salary","superssn","dno"
                };

                for(String key:keys){
                    gridAdapter.mList.add(key);
                    gridAdapter.notifyItemInserted(gridAdapter.mList.size()-1);
                }

                Cursor c = db.rawQuery("select * from employee",null);


                while (c.moveToNext()) {
                    for(int i=0;i<c.getColumnCount();i++){
                        String res = c.getString(i);
                        gridAdapter.mList.add(res);
                        gridAdapter.notifyItemInserted(gridAdapter.mList.size()-1);
                    }
                }
                c.close();
            }
        });


    }
}
