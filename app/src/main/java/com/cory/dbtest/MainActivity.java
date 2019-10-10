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

import java.util.Arrays;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    private GridAdapter mGridAdapter;
    private SQLiteDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button bt=findViewById(R.id.button);
        Button bt2=findViewById(R.id.button2);
        final RecyclerView recyclerView=findViewById(R.id.recycler);
        mDb= new DBHelper(this).getWritableDatabase();


        if(recyclerView.getAdapter()==null) {
            mGridAdapter=new GridAdapter(this,8);
            recyclerView.setAdapter(mGridAdapter);
            recyclerView.setLayoutManager(new GridLayoutManager(this,8));
            recyclerView.setHasFixedSize(true);
        }else{
            mGridAdapter=(GridAdapter)(recyclerView.getAdapter());
        }


        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] keys=new String[]{
                        "ssn","name","bdate","address","sex","salary","superssn","dno"
                };
                Cursor c = mDb.rawQuery("select * from employee",null);
                updateTable(keys,c);
            }
        });


    }
    
    private void updateTable(String[] keys,Cursor c){
        mGridAdapter.mList.clear();
        mGridAdapter.mList.addAll(Arrays.asList(keys));
        while (c.moveToNext()) {
            for(int i=0;i<c.getColumnCount();i++){
                String res = c.getString(i);
                mGridAdapter.mList.add(res);
                mGridAdapter.notifyItemInserted(mGridAdapter.mList.size()-1);
            }
        }
        c.close();
    }
}
