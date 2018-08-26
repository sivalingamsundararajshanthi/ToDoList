/*Group Members: SIVALINGAM SUNDARARAJ SHANTHI z1829451
                 YOKESH SRIHARI z1809328*/

/////////////////////////////////////////////////////////////
//This function is used to update the items in the database//
////////////////////////////////////////////////////////////

package edu.niu.cs.z1829451.assignment4;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class UpdateActivity extends AppCompatActivity {

    DataBaseManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        manager = new DataBaseManager(this);

        updateView();
    }

    /////////////////////////////////////////////////////////////////////////////
    //This function is used to display the items in the database after updating//
    /////////////////////////////////////////////////////////////////////////////
    public void updateView(){
        ArrayList<Check> checkList = manager.selectAll();

        if(checkList.size() > 0){

            ScrollView scrollView = new ScrollView(this);

            GridLayout gridLayout = new GridLayout(this);

            gridLayout.setRowCount(checkList.size());
            gridLayout.setColumnCount(3);

            TextView [] ids = new TextView[checkList.size()];

            EditText [][] items = new EditText[checkList.size()][1];

            Button [] buttons = new Button[checkList.size()];

            ButtonHandler handler = new ButtonHandler();

            Point size = new Point();
            getWindowManager().getDefaultDisplay().getSize(size);

            int width = size.x;

            int sub = 0;

            for(Check check : checkList){
                ids[sub] = new TextView(this);
                ids[sub].setGravity(Gravity.CENTER);
                ids[sub].setText("" + check.getId());

                items[sub][0] = new EditText(this);

                items[sub][0].setText(check.getItem());

                items[sub][0].setId(10 * check.getId());
                //items[sub][1].setId(10 * check.getId() + 1);

                buttons[sub] = new Button(this);
                buttons[sub].setText("UPDATE");
                buttons[sub].setId(check.getId());

                buttons[sub].setOnClickListener(handler);

                gridLayout.addView(ids[sub],(int)(width * 0.1), ViewGroup.LayoutParams.WRAP_CONTENT);
                gridLayout.addView(items[sub][0], (int)(width * 0.4), ViewGroup.LayoutParams.WRAP_CONTENT);
                gridLayout.addView(buttons[sub], (int)(width * 0.35), ViewGroup.LayoutParams.WRAP_CONTENT);

                sub++;
            }
            scrollView.addView(gridLayout);

            setContentView(scrollView);
        }
    }

    private class ButtonHandler implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            int itemId = view.getId();

            EditText itemEt = (EditText)findViewById(10 * itemId);

            String item = itemEt.getText().toString();

            if(itemEt.getText().toString().equals("")){
                Toast.makeText(UpdateActivity.this, "Please enter something", Toast.LENGTH_SHORT).show();
            }else{
                try{
                    manager.updateById(itemId, item);
                    Toast.makeText(UpdateActivity.this, "updated: " + item, Toast.LENGTH_SHORT).show();
                    updateView();
                } catch (NumberFormatException nfe){
                    Toast.makeText(UpdateActivity.this, "item error", Toast.LENGTH_SHORT).show();
                }
            }

            /*try{
                manager.updateById(itemId, item);
                Toast.makeText(UpdateActivity.this, "updated: " + item, Toast.LENGTH_SHORT).show();
                updateView();
            } catch (NumberFormatException nfe){
                Toast.makeText(UpdateActivity.this, "item error", Toast.LENGTH_SHORT).show();
            }*/
        }
    }
}
