package com.cory.dbtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private GridAdapter mGridAdapter;
    private SQLiteDatabase mDb;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button bt=findViewById(R.id.button);
        Button bt2=findViewById(R.id.button2);
        mRecyclerView =findViewById(R.id.recycler);
        mDb= new DBHelper(this).getWritableDatabase();


        if(mRecyclerView.getAdapter()==null) {
            mGridAdapter=new GridAdapter(this, mRecyclerView);
            mRecyclerView.setAdapter(mGridAdapter);
            mRecyclerView.setHasFixedSize(true);
        }else{
            mGridAdapter=(GridAdapter)(mRecyclerView.getAdapter());
        }


        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor c = mDb.rawQuery("select * from dependent",null);
                updateView(c);
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
            }
        });
    }
    
    private void updateView(Cursor c){
        mGridAdapter.mList.clear();
        String[] keys=c.getColumnNames();
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,keys.length));
        mGridAdapter.mList.addAll(Arrays.asList(keys));
        while (c.moveToNext()) {
            for(int i=0;i<c.getColumnCount();i++){
                String res = c.getString(i);
                mGridAdapter.mList.add(res);
            }
        }
        mGridAdapter.notifyDataSetChanged();
        c.close();
    }

    private void insertData(){
        mDb.execSQL("delete from employee");
        mDb.execSQL("delete from department");
        mDb.execSQL("delete from depart_location");
        mDb.execSQL("delete from project");
        mDb.execSQL("delete from works_on");
        mDb.execSQL("delete from dependent");
        /*
        * employee
        * */
        mDb.execSQL("insert into employee('ssn','name','bdate','address','sex','salary','superssn','dno')" +
                "values('230101198009081234','张三','1980-09-08','哈尔滨道里区十二道街','男',3125,'23010119751201312X','d1')");

        mDb.execSQL("insert into employee('ssn','name','bdate','address','sex','salary','superssn','dno')" +
                "values('230101198107023736','李四','1981-07-02','哈尔滨道外区三道街','男',2980,'23010119751201312X','d1')");

        mDb.execSQL("insert into employee('ssn','name','bdate','address','sex','salary','superssn','dno')" +
                "values('23010119751201312X','张红','1975-12-01','哈尔滨南岗区三十道街','男',4260,'23010119751201312X','d1')");

        mDb.execSQL("insert into employee('ssn','name','bdate','address','sex','salary','superssn','dno')" +
                "values('230101198204078121','王二','1982-04-07','哈尔滨动力区六十道街','男',2890,'23010119751201312X','d1')");

        mDb.execSQL("insert into employee('ssn','name','bdate','address','sex','salary','superssn','dno')" +
                "values('23010119950101XXXX','灰太狼','1995-01-01','青青草原狼堡','男',1200,'23010119960101XXXX','d2')");

        mDb.execSQL("insert into employee('ssn','name','bdate','address','sex','salary','superssn','dno')" +
                "values('23010119960101XXXX','红太狼','1996-01-01','青青草原狼堡','女',3600,'23010119960101XXXX','d2')");

        mDb.execSQL("insert into employee('ssn','name','bdate','address','sex','salary','superssn','dno')" +
                "values('23010120050101XXXX','喜羊羊','2005-01-01','青青草原大肥羊学校','男',1000,'23010120050101XXXX','d3')");

        mDb.execSQL("insert into employee('ssn','name','bdate','address','sex','salary','superssn','dno')" +
                "values('XXXXXXXXXXXXXXXXXX','超人','3000-01-01','外星','男',100000,'23010120050101XXXX','d4')");
        /*
        * department
        * */
        mDb.execSQL("insert into department('dname','dnumber','mgrssn','mgrstartdate')" +
                "values('研发部','d1','23010119751201312X','2008-01-01')");

        mDb.execSQL("insert into department('dname','dnumber','mgrssn','mgrstartdate')" +
                "values('捕羊部','d2','23010119960101XXXX','2006-01-01')");

        mDb.execSQL("insert into department('dname','dnumber','mgrssn','mgrstartdate')" +
                "values('防狼部','d3','23010120050101XXXX','2006-01-01')");

        mDb.execSQL("insert into department('dname','dnumber','mgrssn','mgrstartdate')" +
                "values('全能部','d4','XXXXXXXXXXXXXXXXXX','3000-01-01')");
        /*
        depart_location
        * */
        mDb.execSQL("insert into depart_location('dnumber','dlocation')" +
                "values('d1','哈尔滨')");

        mDb.execSQL("insert into depart_location('dnumber','dlocation')" +
                "values('d2','青青草原')");

        mDb.execSQL("insert into depart_location('dnumber','dlocation')" +
                "values('d3','青青草原')");

        mDb.execSQL("insert into depart_location('dnumber','dlocation')" +
                "values('d4','地球')");
        /*
        * project
        * */
        mDb.execSQL("insert into project('pname','pnumber','plocation','dnum')" +
                "values('研究项目','p1','哈尔滨','d1')");

        mDb.execSQL("insert into project('pname','pnumber','plocation','dnum')" +
                "values('哈同公路','p2','哈尔滨','d1')");

        mDb.execSQL("insert into project('pname','pnumber','plocation','dnum')" +
                "values('立交桥','p3','哈尔滨','d1')");

        mDb.execSQL("insert into project('pname','pnumber','plocation','dnum')" +
                "values('机场建设','p4','哈尔滨','d1')");

        mDb.execSQL("insert into project('pname','pnumber','plocation','dnum')" +
                "values('抓羊','p5','青青草原','d2')");

        mDb.execSQL("insert into project('pname','pnumber','plocation','dnum')" +
                "values('吃羊','p6','青青草原','d2')");

        mDb.execSQL("insert into project('pname','pnumber','plocation','dnum')" +
                "values('防狼','p7','青青草原','d3')");
        /*
        * works_on
        * */
        mDb.execSQL("insert into works_on('essn','pno','hours')" +
                "values('23010119751201312X','p1',100)");

        mDb.execSQL("insert into works_on('essn','pno','hours')" +
                "values('23010119751201312X','p2',90)");

        mDb.execSQL("insert into works_on('essn','pno','hours')" +
                "values('23010119751201312X','p3',85)");

        mDb.execSQL("insert into works_on('essn','pno','hours')" +
                "values('23010119751201312X','p4',100)");

        mDb.execSQL("insert into works_on('essn','pno','hours')" +
                "values('230101198009081234','p1',65)");

        mDb.execSQL("insert into works_on('essn','pno','hours')" +
                "values('230101198009081234','p2',76)");

        mDb.execSQL("insert into works_on('essn','pno','hours')" +
                "values('230101198009081234','p3',67)");

        mDb.execSQL("insert into works_on('essn','pno','hours')" +
                "values('230101198107023736','p2',89)");

        mDb.execSQL("insert into works_on('essn','pno','hours')" +
                "values('230101198107023736','p3',79)");

        mDb.execSQL("insert into works_on('essn','pno','hours')" +
                "values('230101198204078121','p2',23)");

        mDb.execSQL("insert into works_on('essn','pno','hours')" +
                "values('230101198204078121','p3',36)");

        mDb.execSQL("insert into works_on('essn','pno','hours')" +
                "values('23010119950101XXXX','p2',11)");

        mDb.execSQL("insert into works_on('essn','pno','hours')" +
                "values('23010119950101XXXX','p5',100)");

        mDb.execSQL("insert into works_on('essn','pno','hours')" +
                "values('23010119950101XXXX','p6',100)");

        mDb.execSQL("insert into works_on('essn','pno','hours')" +
                "values('23010119960101XXXX','p5',100)");

        mDb.execSQL("insert into works_on('essn','pno','hours')" +
                "values('23010119960101XXXX','p6',100)");

        mDb.execSQL("insert into works_on('essn','pno','hours')" +
                "values('23010120050101XXXX','p7',100)");

        mDb.execSQL("insert into works_on('essn','pno','hours')" +
                "values('XXXXXXXXXXXXXXXXXX','p1',100)");

        mDb.execSQL("insert into works_on('essn','pno','hours')" +
                "values('XXXXXXXXXXXXXXXXXX','p2',100)");

        mDb.execSQL("insert into works_on('essn','pno','hours')" +
                "values('XXXXXXXXXXXXXXXXXX','p3',100)");

        mDb.execSQL("insert into works_on('essn','pno','hours')" +
                "values('XXXXXXXXXXXXXXXXXX','p4',100)");

        mDb.execSQL("insert into works_on('essn','pno','hours')" +
                "values('XXXXXXXXXXXXXXXXXX','p5',100)");

        mDb.execSQL("insert into works_on('essn','pno','hours')" +
                "values('XXXXXXXXXXXXXXXXXX','p6',100)");

        mDb.execSQL("insert into works_on('essn','pno','hours')" +
                "values('XXXXXXXXXXXXXXXXXX','p7',100)");
        /*
        * dependent
        * */
        mDb.execSQL("insert into dependent('essn','dependent_name','sex','bdate','relationship')" +
                "values('230101198009081234','张三妻','女','1983-09-02','配偶')");

        mDb.execSQL("insert into dependent('essn','dependent_name','sex','bdate','relationship')" +
                "values('230101198009081234','张三儿','男','2005-01-01','父子')");

        mDb.execSQL("insert into dependent('essn','dependent_name','sex','bdate','relationship')" +
                "values('23010119950101XXXX','小灰灰','男','2009-01-01','父子')");

        mDb.execSQL("insert into dependent('essn','dependent_name','sex','bdate','relationship')" +
                "values('23010119960101XXXX','小灰灰','男','2009-01-01','母子')");
        /*
        * alter table dependent
        * */
        mDb.execSQL("create table dependent_temp as select essn,dependent_name,sex,bdate,relationship from dependent");
        mDb.execSQL("drop table if exists dependent");
        mDb.execSQL("alter table dependent_temp rename to dependent");
        mDb.execSQL("alter table dependent add column company char(20)");
        mDb.execSQL("alter table dependent add column profession char(20)");
        mDb.execSQL("update dependent set company='公司名' where essn='230101198009081234'");
        mDb.execSQL("update dependent set profession='职业名' where essn='230101198009081234'");
    }
}
