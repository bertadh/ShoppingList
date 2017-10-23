package com.example.bertadomingo.shoppinglist;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
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

        adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                ItemList
        );

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

        shopping_list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int pos, long id) {
                maybeRemoveItem(pos);
                return true;
            }
        });
    }

    private void maybeRemoveItem(final int pos) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.confirm);
        builder.setMessage(String.format(
                "Are you sure to remove '%1$s' ?",
                ItemList.get(pos)
        ));

        builder.setPositiveButton(R.string.remove, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ItemList.remove(pos);
                adapter.notifyDataSetChanged();
            }
        });

        builder.setNegativeButton(R.string.cancel, null);

        builder.create().show();
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
