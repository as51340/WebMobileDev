package hr.fer.wpu.flowerpower;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.icu.text.IDNA;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;
import java.util.Map;

public class ProductListActivity extends Activity {

    private Map<Category, List<Product>> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        data = Utils.loadData();
        ScrollView scroller = new ScrollView(this);
        LinearLayout lin = new LinearLayout(this);
        lin.setOrientation(LinearLayout.VERTICAL);
        int[] flowersLocal = new int[] {R.string.tulip, R.string.lavender, R.string.fucsia, R.string.daisy};
        Bundle extras = getIntent().getExtras();
        Category cat  = null;
        if(extras != null) {
            cat = (Category) extras.getSerializable("kategorija");
        }
        if(cat == null) {
            throw new RuntimeException("Dohvaćanje kategorije nije uspjelo");
        }
        TextView naslov = new TextView(this);
        naslov.setText(cat.getName());
        lin.addView(naslov);
        naslov.setTextSize(26);
        List<Product> products = data.get(cat);
        int i = 0;
        for(Product p: products) {
            LinearLayout temp = new LinearLayout(this);
            temp.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            TextView imeCv = new TextView(this);
            imeCv.setTextSize(20);
            imeCv.setText(p.name);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 2);
            LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
            imeCv.setLayoutParams(params);
            temp.addView(imeCv);
            Button addButton = new Button(this);
            addButton.setGravity(Gravity.CENTER);
            addButton.setText("+");
            addButton.setLayoutParams(params2);
            addButton.setOnClickListener(click -> {
                Toast.makeText(ProductListActivity.this, R.string.toastAddItem, Toast.LENGTH_SHORT).show();
            });
            temp.addView(addButton);
            Button infoButton = new Button(this);
            infoButton.setText("INFO");infoButton.setGravity(Gravity.CENTER);
            infoButton.setLayoutParams(params2);
            infoButton.setOnClickListener(click -> {
                Intent intent = new Intent(ProductListActivity.this, InfoActivity.class);
                startActivity(intent);
            });
            temp.addView(infoButton);
            lin.addView(temp);
        }
        scroller.addView(lin);
        setContentView(scroller);
    }
}