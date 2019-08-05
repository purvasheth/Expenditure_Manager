package com.example.expenses;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;



public class DisplayActivity extends AppCompatActivity {
    EditText total;
    EditText balance;
    EditText newAmt;
    //EditText displayScreen;
    TextView cost;
    TextView cause;
    TextView detail;
    TextView date;
    TableLayout table;
    MyDBCostHandler dbCostHandler;
    MyDBHandler dbHandler;

    int currentCost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        dbCostHandler=new MyDBCostHandler(this,null,null,1);
        dbHandler = new MyDBHandler(this,null,null,1);

        Intent intent=getIntent();
        //String message=intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        currentCost = intent.getIntExtra(MainActivity.EXTRA_COST,0);
        //displayScreen=(EditText)findViewById(R.id.display);
        balance=findViewById(R.id.balance);
        total=findViewById(R.id.total);
        table =  findViewById(R.id.display);
        newAmt=findViewById(R.id.editText3);

        if(dbCostHandler.printBalance()!="0")
            dbCostHandler.costUpdateBalance(currentCost);

        total.setText(dbCostHandler.printTotal());
        balance.setText(dbCostHandler.printBalance());
        init();
    }
    public void updateValues(View view){


        int acost =Integer.parseInt(total.getText().toString());
        //int bcost =Integer.parseInt(balance.getText().toString());
        dbCostHandler.costUpdateTotal(acost);
        dbCostHandler.costChangeBalance(acost);
        total.setText(dbCostHandler.printTotal());
        balance.setText(dbCostHandler.printBalance());
    }
    public void printDB() {
        ArrayList<Items> dbList = new ArrayList<>();
        dbList = dbHandler.print();
        /*for(Items i:dbList){
            displayScreen.append(i.toString());
        }*/





        /*for (Items i : dbList) {
            String row = i.toString();
            TableRow tableRow = new TableRow(getApplicationContext());
            final String[] cols = row.split(" ");


            for (int j = 0; j < cols.length; j++) {

                final String col = cols[j];
                final TextView columsView = new TextView(getApplicationContext());
                columsView.setText(String.format("%7s", col));
                tableRow.addView(columsView);

            }
        }
*/    }

    public void init(){


        table.removeAllViews();
        table.setStretchAllColumns(true);
        table.bringToFront();
        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);


        TableRow header= new TableRow(this);
        header.setLayoutParams(lp);
        date = new TextView(this);
        date.setText("Date");
        date.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        float currentSize = date.getTextSize(); // default size
        float newSize = currentSize * 0.4F; // new size
        date.setTextSize(newSize);
        date.setTextColor(Color.parseColor("#000000"));


        cause = new TextView(this);
        cause.setText("Reason");
        cause.setTextSize(newSize);
        cause.setTextColor(Color.parseColor("#000000"));
        cause.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        cost= new TextView(this);
        cost.setText("Cost");
        cost.setTextSize(newSize);
        cost.setTextColor(Color.parseColor("#000000"));
        cost.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);



        detail = new TextView(this);
        detail.setText("Description");
        detail.setTextSize(newSize);
        detail.setTextColor(Color.parseColor("#000000"));
        detail.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        header.addView(date);
        header.addView(cause);
        header.addView(cost);
        header.addView(detail);

        table.addView(header);


        newSize = currentSize*0.37F;





        ArrayList<Items> dbList = new ArrayList<>();
        dbList = dbHandler.print();
        for(Items i:dbList){
            TableRow row= new TableRow(this);
            row.setLayoutParams(lp);
            date = new TextView(this);
            date.setText(i.getDate());
            date.setTextSize(newSize);
            date.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            cause = new TextView(this);
            cause.setText(i.getCause());
            cause.setTextSize(newSize);
            cause.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            cost=new TextView(this);
            cost.setText(i.getCost()+"");
            cost.setTextSize(newSize);
            cost.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            detail = new TextView(this);
            detail.setText((i.getDetail()));
            detail.setTextSize(newSize);
            detail.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);


            row.addView(date);
            row.addView(cause);
            row.addView(cost);
            row.addView(detail);

            table.addView(row);

        }

        int totalCost = dbHandler.total();
        TableRow footer = new TableRow(this);
        footer.setLayoutParams(lp);
        TextView key1 = new TextView(this);
        key1.setTextColor(Color.parseColor("#000000"));
        key1.setTextSize(currentSize*0.4f);
        key1.setText("Total");
        key1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        TextView key2 = new TextView(this);
        key2.setTextColor(Color.parseColor("#000000"));
        key2.setTextSize(currentSize*0.4f);
        key2.setText("Amount");
        key2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        TextView value = new TextView(this);
        value.setTextColor(Color.parseColor("#000000"));
        value.setTextSize(currentSize*0.4f);
        value.setText(totalCost+"");
        value.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        footer.addView(key1);
        footer.addView(key2);
        footer.addView(value);
        table.addView(footer);

    }


    public void updateTotal(View view) {
        int oldTotal = Integer.parseInt(total.getText().toString());
        int newTotal = Integer.parseInt(newAmt.getText().toString());
        newTotal = oldTotal + newTotal;
        total.setText(newTotal+"");
        newAmt.setText("");
        dbCostHandler.costUpdateTotal(newTotal);
    }
}

