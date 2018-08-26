/*Group Members: SIVALINGAM SUNDARARAJ SHANTHI z1829451
                 YOKESH SRIHARI z1809328*/

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//This activity is used to display the items in the database using checkboxes. This activity also has menu which allows the//
//user to navigate to insert, delete, update and clear the database items                                                  //
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

package edu.niu.cs.z1829451.assignment4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private DataBaseManager manager;
    private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        manager = new DataBaseManager(this);

        scrollView = (ScrollView)findViewById(R.id.contentScrollView);
        /*TextView textView = new TextView(this);
        textView.setText("Your List: ");
        textView.setHeight(20);
        setContentView(textView);*/
        updateView();  //call to the updateView function
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateView();
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////
    //This function actually takes the items from the database and displays the items using a check box//
    /////////////////////////////////////////////////////////////////////////////////////////////////////
    public void updateView(){


        ArrayList<Check> checkList = manager.selectAll();

        if(checkList.size()>0){
            scrollView.removeAllViewsInLayout();

            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setOrientation(LinearLayout.VERTICAL);

            //TextView textView = new TextView(this);
            //textView.setText("Your List: ");
            //textView.setHeight(50);

            //ListView listView = new ListView(this);

            //linearLayout.addView(textView);
            for(Check check : checkList){
                CheckBox cb = new CheckBox(this);
                cb.setText(check.getItem());
                linearLayout.addView(cb, LinearLayout.LayoutParams.WRAP_CONTENT);
            }


            scrollView.addView(linearLayout);
            //scrollView.addView(textView);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //////////////////////////////////////////////////
    //This is used to navigate to various activities//
    //////////////////////////////////////////////////
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add) {
            Intent addIntent = new Intent(MainActivity.this,InsertActivity.class);
            startActivity(addIntent);
            return true;
        } else if(id == R.id.action_delete){
            Intent deleteIntent = new Intent(MainActivity.this, DeleteActivity.class);
            startActivity(deleteIntent);
            return true;
        } else if(id == R.id.action_clear){
            scrollView.removeAllViewsInLayout();
            manager.clear();
        } else if(id == R.id.action_update){
            Intent updateIntent = new Intent(MainActivity.this, UpdateActivity.class);
            startActivity(updateIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
