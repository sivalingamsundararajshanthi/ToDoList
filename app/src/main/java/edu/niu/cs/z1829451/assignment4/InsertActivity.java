/*Group Members: SIVALINGAM SUNDARARAJ SHANTHI z1829451
                 YOKESH SRIHARI z1809328*/

////////////////////////////////////////////////////////////////
//This activity is used to insert new items into the database.//
////////////////////////////////////////////////////////////////

package edu.niu.cs.z1829451.assignment4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class InsertActivity extends AppCompatActivity {

    private DataBaseManager manager;
    TextView tv;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        manager = new DataBaseManager(this);
        setContentView(R.layout.activity_insert);

        tv = (TextView)findViewById(R.id.insertTvId);

        //ArrayList<Check> checkItem = manager.selectAll();

        //for(Check check : checkItem){
          //  tv.setText("" + check.getItem());
        //}

        showList();  //Function call to the showList function
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //This function is used to insert the user entered item into the database. If the user enters nothing and tries to hit submit//
    //a toast message will be displayed which will tell the user to enter something in the textfield                             //
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void insertItem(View view){

        //ArrayList<Check> checkList = manager.selectAll();
        EditText itemEt = (EditText)findViewById(R.id.ItemEtId);

        String item = itemEt.getText().toString();


        /*try{
            Check check = new Check(0,item);
            manager.insert(check);
        } catch(NumberFormatException nfe){
            Toast.makeText(InsertActivity.this,"Error",Toast.LENGTH_SHORT).show();
        }*/

        //ArrayList<Check> checkList = manager.selectAll();
        //tv.setText("");
        //for(Check check: checkList){
            //tv.setText("");
            /*Toast.makeText(InsertActivity.this,"Item: " + check.getItem(), Toast.LENGTH_SHORT).show();
            //tv.setText("" + check.getItem());
            Log.d("here", "" + check.getItem());
            //tv.setText("" + check.getItem());
            //String txt = check.getItem();
            //tv.setText("");
            /*if(tv.getText().toString().matches("")){
                tv.append("");
            } else {
                //tv.setText("\n" + txt);
                tv.append("\n" + check.getItem());
            }*/

            //if(!tv.getText().toString().matches("")){
              //  tv.append("\n" + check.getItem());
            //}

            //tv.append("\n" + check.getItem());

            /*if(count<checkList.size()){
                Check check1 = checkList.get(count);

                //tv.setText("");
                tv.append("\n");
                tv.append("" + check1.getItem());

                count++;
            }else{
                count = 0;
            }*/

            //tv.append("\n" + check.getItem());


            /*if(!tv.getText().toString().matches("")){
                tv.append("");
                Log.d("here","" + check.getItem());
                tv.append(check.getItem());
            }*/


        //}

        //checkList.clear();



        /*for(int i = 0;i<checkList.size();i++){
            tv.setText("" + checkList.get(i));
        }*/

        //showList();
        //itemEt.setText("");



        /*int count = 0;
        if(count<checkList.size()){
            Check check = checkList.get(count);

            tv.setText("" + check.getItem());

            count++;
        } else {
            count = 0;
        }*/

        if(itemEt.getText().toString().equals("")){
            Toast.makeText(InsertActivity.this,"Please enter something", Toast.LENGTH_SHORT).show();
        }else{
            //String item = itemEt.getText().toString();
            try{
                Check check = new Check(0,item);
                manager.insert(check);
            } catch(NumberFormatException nfe){
                Toast.makeText(InsertActivity.this,"Error",Toast.LENGTH_SHORT).show();
            }
            showList();
            itemEt.setText("");
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    //This function is used to display whatever is in the database to the user//
    ////////////////////////////////////////////////////////////////////////////
    public void showList(){
        ArrayList<Check> checkList = manager.selectAll();
        tv.setText("The List contains:");

        for(Check check: checkList){
            tv.append("\n" + check.getItem());
        }

        checkList.clear();
    }

    public void goBack(View view){
        finish();
    }
}
