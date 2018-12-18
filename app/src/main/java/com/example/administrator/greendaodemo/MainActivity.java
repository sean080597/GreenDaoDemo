package com.example.administrator.greendaodemo;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.administrator.greendaodemo.database.DaoMaster;
import com.example.administrator.greendaodemo.database.DaoSession;
import com.example.administrator.greendaodemo.database.Student;
import com.example.administrator.greendaodemo.database.StudentDao;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    //DAO --> Data Access Object
    private StudentDao studentDao;//sql access object
    private Student studentObj;

    private EditText edtId, edtName, edtFaName, edtMoName, edtPhone;
    private String name, faName, moName, phoneNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mapping id
        edtId = findViewById(R.id.edt_id);
        edtName = findViewById(R.id.edt_name);
        edtFaName = findViewById(R.id.edt_father_name);
        edtMoName = findViewById(R.id.edt_mother_name);
        edtPhone = findViewById(R.id.edt_phone);

        Button btnSave = findViewById(R.id.btn_save);
        Button btnShow = findViewById(R.id.btn_show);

        //Initialise DAO
        studentDao = initStudentDb();

        // reset errors
        edtName.setError(null);
        edtFaName.setError(null);
        edtMoName.setError(null);
        edtPhone.setError(null);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //get text
                name = edtName.getText().toString();
                faName = edtFaName.getText().toString();
                moName = edtMoName.getText().toString();
                phoneNum = edtPhone.getText().toString();

                //set errors & set focus view
                setErrors();

                boolean cancel = false;
                View focusView = null;

                if(TextUtils.isEmpty(name)){
                    focusView = edtName;
                    cancel = true;
                } else if(TextUtils.isEmpty(faName)){
                    focusView = edtFaName;
                    cancel = true;
                } else if(TextUtils.isEmpty(moName)){
                    focusView = edtMoName;
                    cancel = true;
                } else if(TextUtils.isEmpty(phoneNum)){
                    focusView = edtPhone;
                    cancel = true;
                }

                if(cancel){
                    focusView.requestFocus();
                }else{
                    studentObj = new Student(null, name, faName, moName, phoneNum);
                    studentDao.insert(studentObj);
                    resetEdt();
                }
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtId.setText(getFromSQL(0,0));
                edtName.setText(getFromSQL(0, 1));
                edtFaName.setText(getFromSQL(0, 2));
                edtMoName.setText(getFromSQL(0, 3));
                edtPhone.setText(getFromSQL(0, 4));
            }
        });
    }

    private StudentDao initStudentDb() {
        //create db file if not exist
        String DB_NAME = "student_db";
        DaoMaster.DevOpenHelper masterHelper = new DaoMaster.DevOpenHelper(this, DB_NAME, null);
        //get the created db file
        SQLiteDatabase db = masterHelper.getWritableDatabase();
        DaoMaster master = new DaoMaster(db);//create masterDao
        DaoSession masterSession = master.newSession();//create session
        return masterSession.getStudentDao();
    }

    private String getFromSQL(int position, int index) {
        //get list of student in descending order
        List<Student> stdList = studentDao.queryBuilder().orderDesc(StudentDao.Properties.Id).build().list();
        String result = null;

        if(stdList.size() > 0){
            switch (index){
                case 0:
                    result = stdList.get(position).getId().toString();
                    break;
                case 1:
                    result = stdList.get(position).getName();
                    break;
                case 2:
                    result = stdList.get(position).getFatherName();
                    break;
                case 3:
                    result = stdList.get(position).getMotherName();
                    break;
                case 4:
                    result = stdList.get(position).getContactNumber();
                    break;
            }
            return result;
        }

        return "No data";
    }

    private void setErrors(){
        if(TextUtils.isEmpty(name)){
            edtName.setError(getString(R.string.invalid_input));
        }
        if(TextUtils.isEmpty(faName)){
            edtFaName.setError(getString(R.string.invalid_input));
        }
        if(TextUtils.isEmpty(moName)){
            edtMoName.setError(getString(R.string.invalid_input));
        }
        if(TextUtils.isEmpty(phoneNum)){
            edtPhone.setError(getString(R.string.invalid_input));
        }
    }

    private void resetEdt(){
        edtId.setText(null);
        edtName.setText(null);
        edtFaName.setText(null);
        edtMoName.setText(null);
        edtPhone.setText(null);
    }
}
