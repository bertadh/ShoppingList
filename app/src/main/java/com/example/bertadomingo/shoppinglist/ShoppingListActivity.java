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
    private ArrayList<ShoppingItem> ItemList;
    private ShoppingListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);

        shopping_list = (ListView) findViewById(R.id.shopping_list);
        btn_add = (Button) findViewById(R.id.btn_add);
        edit_item = (EditText) findViewById(R.id.edit_item);

        ItemList = new ArrayList<>();
        ItemList.add(new ShoppingItem("Ous", true));
        ItemList.add(new ShoppingItem("Patates"));

        adapter = new ShoppingListAdapter(
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
        shopping_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                /*ShoppingItem item = ItemList.get(position);
                boolean checked = item.isChecked();
                ItemList.get(position).setChecked();*/
                ItemList.get(position).toggleChecked();
                adapter.notifyDataSetChanged();
            }
        });

    }

    private void maybeRemoveItem(final int pos) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.confirm);
        builder.setMessage(String.format(
                "Are you sure to remove '%1$s' ?",
                ItemList.get(pos).getText()
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
            ItemList.add(new ShoppingItem(txt));
            adapter.notifyDataSetChanged();
            edit_item.setText("");
        }
        shopping_list.smoothScrollToPosition(ItemList.size()-1);
    }

}
