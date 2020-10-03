package hr.fer.wpu.flowerpower;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScrollView scroller = new ScrollView(this);
        LinearLayout lin = new LinearLayout(this);
        lin.setOrientation(LinearLayout.VERTICAL);
        //logo
        ImageView logo = new ImageView(this);
        logo.setImageResource(R.drawable.logo);
        LinearLayout.LayoutParams imgParams  = new LinearLayout.LayoutParams(1000, 600, Gravity.CENTER_VERTICAL);
        logo.setLayoutParams(imgParams);
        logo.setForegroundGravity(Gravity.CENTER);
        lin.addView(logo);
        //naslov
        TextView imeApp = new TextView(this);
        imeApp.setGravity(Gravity.CENTER);
        imeApp.setText(R.string.app_name);
        imeApp.setTextSize(30);
        imeApp.setTypeface(null, Typeface.BOLD);
        imeApp.setPadding(0, 30, 0,10);
        lin.addView(imeApp);
        //ulica
        TextView ulica = new TextView(this);
        ulica.setGravity(Gravity.CENTER);
        ulica.setText("Kunska 3, Zagreb");
        ulica.setTextSize(26);
        ulica.setTypeface(null, Typeface.BOLD);
        ulica.setPadding(0, 30, 0,10);
        lin.addView(ulica);
        //telefon
        TextView mob = new TextView(this);
        mob.setGravity(Gravity.CENTER);
        mob.setText("+385 090 541 8337");
        mob.setTextSize(20);
        mob.setTypeface(null, Typeface.ITALIC);
        mob.setPadding(0, 30, 0,10);
        lin.addView(mob);
        //mail
        LinearLayout mailLay = new LinearLayout(this);
        TextView mail = new TextView(this);
        mail.setGravity(Gravity.CENTER);
        mail.setText("email: info@ciklama.fer.hr");
        mail.setTextSize(20);
        mail.setTypeface(null, Typeface.ITALIC);
        mail.setPadding(0, 30, 0,10);
        mailLay.addView(mail);
        mail.setOnClickListener(click -> {
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:"));
            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"info@ciklama.fer.hr"});
            intent.putExtra(Intent.EXTRA_SUBJECT,  "Query from Flower power mobile app");
            startActivity(intent);
        });
        mailLay.setGravity(Gravity.CENTER);
        lin.addView(mailLay);
        //web page
        TextView page = new TextView(this);
        page.setGravity(Gravity.CENTER);
        page.setText("http://www.infoFlowers.com");
        page.setTextSize(20);
        page.setTypeface(null, Typeface.ITALIC);
        page.setPadding(0, 30, 0,10);
        lin.addView(page);
        //app by
        TextView app = new TextView(this);
        app.setGravity(Gravity.CENTER);
        app.setText("App by: Andi Škrgat");
        app.setTextSize(20);
        app.setTypeface(null, Typeface.ITALIC);
        app.setPadding(0, 30, 0,10);
        lin.addView(app);
        scroller.addView(lin);
        setContentView(scroller);
    }
}