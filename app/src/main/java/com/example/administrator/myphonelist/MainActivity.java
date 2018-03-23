package com.example.administrator.myphonelist;

import android.Manifest;
import android.app.Application;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

import kr.co.namee.permissiongen.PermissionGen;

public class MainActivity extends AppCompatActivity {

    private ListView my_address_book_listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        my_address_book_listview = findViewById(R.id.my_address_book_listview);
        //添加的权限
        PermissionGen.needPermission(this, 100,
                new String[]{
                        Manifest.permission.GET_ACCOUNTS, Manifest.permission.READ_CONTACTS, Manifest.permission.GET_ACCOUNTS,
                        Manifest.permission.WRITE_CONTACTS, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA
                }
        );
        //数据源
        ArrayList<String> readContact = getReadContact();
        //适配器
        my_address_book_listview.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, readContact));
    }

    //路径
    Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

    private ArrayList<String> getReadContact() {
        ContentResolver resolver = getContentResolver();
        Cursor query = resolver.query(uri, null, null, null, null);
        ArrayList<String> arr = new ArrayList<String>();
        while (query.moveToNext()) {
            String name = query.getString(query.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String num = query.getString(query.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            arr.add(name + "," + num);
        }
        return arr;
    }


    //没用到
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        PermissionGen.onRequestPermissionsResult(this, requestCode, permissions, grantResults);

    }
}

