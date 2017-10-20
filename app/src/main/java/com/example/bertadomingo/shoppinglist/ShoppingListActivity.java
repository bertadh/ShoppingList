package com.example.bertadomingo.shoppinglist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class ShoppingListActivity extends AppCompatActivity {


    private ListView shopping_list;
    private Button btn_add;
    private EditText edit_item;
    private ArrayList<String> ItemList;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);

        shopping_list = (ListView) findViewById(R.id.shopping_list);
        btn_add = (Button) findViewById(R.id.btn_add);
        edit_item = (EditText) findViewById(R.id.edit_item);

        ItemList = new ArrayList<String>();
        ItemList.add("Ous");
        ItemList.add("Patates");

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ItemList);

        shopping_list.setAdapter(adapter);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItem();
            }
        });

        edit_item.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                addItem();
                return true;
            }
        });


    }

    private void addItem() {
        String txt = edit_item.getText().toString();
        if(!txt.isEmpty()){
            ItemList.add(txt);
            adapter.notifyDataSetChanged();
            edit_item.setText("");
        }
    }

}
