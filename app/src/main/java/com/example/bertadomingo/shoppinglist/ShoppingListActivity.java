package com.example.bertadomingo.shoppinglist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class ShoppingListActivity extends AppCompatActivity {


    private ListView shopping_list;
    private Button btn_add;
    private EditText edit_item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        shopping_list = (ListView) findViewById(R.id.shopping_list);
        btn_add = (Button) findViewById(R.id.btn_add);
        edit_item = (EditText) findViewById(R.id.edit_item);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);
    }

}
