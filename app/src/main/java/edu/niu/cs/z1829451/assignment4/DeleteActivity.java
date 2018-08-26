/*Group Members: SIVALINGAM SUNDARARAJ SHANTHI z1829451
                 YOKESH SRIHARI z1809328*/

///////////////////////////////////////////////////////////
//This activity is used to delete items from the database//
///////////////////////////////////////////////////////////

package edu.niu.cs.z1829451.assignment4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.ArrayList;

public class DeleteActivity extends AppCompatActivity {

    private DataBaseManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        manager = new DataBaseManager(this);

        updateView();
    }

    ////////////////////////////////////////////////////////////////////////////////
    //This method is used to display the items in the database using a radiobutton//
    ////////////////////////////////////////////////////////////////////////////////
    public void updateView(){
        ArrayList<Check> checkList = manager.selectAll();

        RelativeLayout layout = new RelativeLayout(this);
        ScrollView scrollView = new ScrollView(this);


        RadioGroup radioGroup = new RadioGroup(this);

        for (Check check : checkList){
            RadioButton radioButton = new RadioButton(this);

            radioButton.setId(check.getId());
            radioButton.setText(check.getItem());

            radioGroup.addView(radioButton);
        }

        RadioButtonHandler handler = new RadioButtonHandler();

        radioGroup.setOnCheckedChangeListener(handler);

        Button backBtn = new Button(this);
        backBtn.setText("BACK");

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //finish();

                Intent intent = new Intent(DeleteActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        scrollView.addView(radioGroup);

        layout.addView(scrollView);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);

        params.setMargins(0,0,0,50);

        layout.addView(backBtn,params);

        setContentView(layout);
    }

    private class RadioButtonHandler implements RadioGroup.OnCheckedChangeListener{

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            manager.deleteById(checkedId);
            Toast.makeText(DeleteActivity.this,"Item Deleted", Toast.LENGTH_SHORT).show();

            updateView();
        }
    }

    //@Override
    //public void onBackPressed() {
      //  finish();
        //super.onBackPressed();
    //}
}
