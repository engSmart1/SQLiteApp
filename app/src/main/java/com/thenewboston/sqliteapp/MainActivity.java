package com.thenewboston.sqliteapp;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editName , editSurname , editMarks, editId;
    Button btnAddData,btnViewAll, btnUpdate, btnDelete;
    DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDB = new DatabaseHelper(this);

        editName = (EditText) findViewById(R.id.editText_name);
        editSurname = (EditText) findViewById(R.id.editText_surname);
        editMarks = (EditText) findViewById(R.id.editText_marks);
        editId =(EditText) findViewById(R.id.editText_id);

        btnAddData = (Button) findViewById(R.id.button_add);
        btnViewAll =(Button) findViewById(R.id.button_view);
        btnUpdate = (Button) findViewById(R.id.button_update);
        btnDelete = (Button) findViewById(R.id.button_delete);

        addData();
        viewAllData();
        upDate();
        deleteData();
    }
    public void deleteData(){
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Integer deleteRaw = myDB.deleteData(editId.getText().toString());
                if (deleteRaw > 0)
                    Toast.makeText(MainActivity.this ,"THE DATA Deleted PERFECTLY !" ,Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this ," Sorry there is no data to delete !" ,Toast.LENGTH_SHORT).show();

            }
        });
    }
    public void upDate(){
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              boolean isUpdate = myDB.updateData(editId.getText().toString(),editName.getText().toString(),editSurname.getText().toString()
                        ,editMarks.getText().toString());
                if (isUpdate == true)
                    Toast.makeText(MainActivity.this ,"THE DATA UPDATED PERFECTLY !" ,Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this ," Sorry there is no data updated !" ,Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void addData(){
        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              boolean isInserted =  myDB.insert(editName.getText().toString(),editSurname.getText().toString(),editMarks.getText().toString());
                if (isInserted == true)
                    Toast.makeText(MainActivity.this ,"THE DATA INSERTED PERFECTLY !" ,Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this ," Sorry there is no data inserted !" ,Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void viewAllData(){
        btnViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res =myDB.getAllData();
                if (res.getCount() == 0){
                    //show message
                    showMessage(" Error " ,"Nothing Found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {

                    buffer.append("id : " + res.getString(0) + "\n");
                    buffer.append(" Name: " + res.getString(1) + "\n");
                    buffer.append("Surname : " + res.getString(2) + "\n");
                    buffer.append("Mark : " + res.getString(3) + "\n\n");

                }
                //show Data
                showMessage("Data" ,buffer.toString());
            }

        });
    }
    public void showMessage(String title , String message ){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
