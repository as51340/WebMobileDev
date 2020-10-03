package hr.fer.wpu.flowerpower;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout lin = new LinearLayout(this);
        lin.setOrientation(LinearLayout.VERTICAL);
        Button kategorije = new Button(this);
        kategorije.setText("Kategorije proizvoda");
        kategorije.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        Button info = new Button(this);
        info.setText("Info");
        info.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        kategorije.setOnClickListener(click -> {
            Intent intent = new Intent(MainActivity.this, CategoryListActivity.class);
            startActivity(intent);
        });
        info.setOnClickListener(click -> {
            Intent intent = new Intent(MainActivity.this, InfoActivity.class);
            startActivity(intent);
        });
        lin.addView(kategorije);
        lin.addView(info);
        setContentView(lin);
    }
}