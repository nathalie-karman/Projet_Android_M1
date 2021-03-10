package com.example.to_doapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipDescription;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class PostIt extends AppCompatActivity {
    private View myDrag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_it);
        myDrag = findViewById(R.id.dragView);

        // myDrag.setOnLongClickListener();//{
           // val clipText = "c est la valeur cliptext";
            // val item = ClipData.Item(clipText);
                // val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
                //          val data = ClipData(clipText, mimeTypes, item);
                //   val dragShadowBuilder = View.DragShadowBuilder(it);
                // })
        //  }
}
}