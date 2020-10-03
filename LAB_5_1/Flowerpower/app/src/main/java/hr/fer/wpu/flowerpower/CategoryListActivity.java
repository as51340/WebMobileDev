package hr.fer.wpu.flowerpower;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

public class CategoryListActivity extends AppCompatActivity {

    private Map<Category, List<Product>> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);
        ScrollView scroller = new ScrollView(this);
        LinearLayout lin = new LinearLayout(this);
        lin.setOrientation(LinearLayout.VERTICAL);
        data = Utils.loadData();
        int index = 0;
        int[] imagesLoadingArr = new int[] {R.drawable.img1_flowers, R.drawable.img2_tools, R.drawable.img3_pots,
        R.drawable.img4_seeds, R.drawable.img5_soil, R.drawable.img6_fertilizers, R.drawable.img7_feeders, R.drawable.img8_gnomes};
        int[] buttonsLocalized = new int[] {R.string.flowers, R.string.tools, R.string.pots, R.string.seeds, R.string.soil, R.string.fertilizers, R.string.feeders,
        R.string.gnomes};
        for(Category cat: data.keySet()) {
            ImageView img = new ImageView(this);
            img.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 550));
            String imgName = cat.imgName;
            img.setImageResource(imagesLoadingArr[index]);
            lin.addView(img);
            Button opisSlike = new Button(this);
            opisSlike.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            //opisSlike.setText(cat.getName());
            opisSlike.setText(buttonsLocalized[index++]);
            opisSlike.setOnClickListener(click -> {
                Intent intent = new Intent(CategoryListActivity.this, ProductListActivity.class);
                intent.putExtra("kategorija", cat);
                startActivity(intent);
            });
            lin.addView(opisSlike);
        }
        scroller.addView(lin);
        setContentView(scroller);
    }
}