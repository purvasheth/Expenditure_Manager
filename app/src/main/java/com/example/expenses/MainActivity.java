package com.example.expenses;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    //public final static String EXTRA_MESSAGE ="com.example.Expenditure.MESSAGE";
    public final static String EXTRA_COST ="com.example.Expenditure.COST";
    Calendar calender;
    SimpleDateFormat sdf;
    String Date;
    EditText cost;
    EditText details;
    RadioButton other;
    RadioButton travel;
    RadioButton food;
    MyDBHandler dbHandler;
    int currentCost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        details=(EditText)findViewById(R.id.details);
        cost=(EditText)findViewById(R.id.cost);
        food=(RadioButton)findViewById(R.id.food);
        travel=(RadioButton)findViewById(R.id.travel);
        other=(RadioButton)findViewById(R.id.other);

        dbHandler=new MyDBHandler(this,null,null,1);
        calender=Calendar.getInstance();
        sdf =new SimpleDateFormat("dd-MM-yyy");
        Date=sdf.format(calender.getTime());
        currentCost=0;
        callDisplay();
    }
    public void sendData(View view){
        Items item=new Items();
        currentCost=Integer.parseInt(cost.getText().toString());
        String currentDetails=details.getText().toString();
        String currentCause= "";
        item.setCost(currentCost);
        item.setDate(Date);
        item.setDetail(currentDetails);
        if(food.isChecked())
            currentCause="food";
        else if(travel.isChecked())
            currentCause="travel";
        else
            currentCause="other";
        item.setCause(currentCause);
        dbHandler.addItem(item);
        callDisplay();



    }
    void callDisplay(){
        Intent intent = new Intent(this, DisplayActivity.class);
        //intent.putExtra(EXTRA_MESSAGE,dbString);//used for key value pairs
        intent.putExtra(EXTRA_COST,currentCost);//used for key value pairs
        startActivity(intent);
        details.setText("");
        cost.setText("");
        other.setChecked(false);
        travel.setChecked(false);
        food.setChecked(false);

    }

    public void clear(View view) {
        dbHandler.clearTable();
        MyDBCostHandler myDBCostHandler=new MyDBCostHandler(this,null,null,2);
        myDBCostHandler.costUpdateTotal(0);
        myDBCostHandler.costChangeBalance(0);
        currentCost=0;
        callDisplay();
    }
}
