package com.iky.cowr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.codemybrainsout.onboarder.AhoyOnboarderActivity;
import com.codemybrainsout.onboarder.AhoyOnboarderCard;

import java.util.ArrayList;
import java.util.List;

public class Welcome extends AhoyOnboarderActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AhoyOnboarderCard ahoyOnboarderCard1 = new AhoyOnboarderCard("COW-CULATOR", "How much you're going to make off of a single cow?", R.drawable.ic_cow_head);
        ahoyOnboarderCard1.setBackgroundColor(R.color.black_transparent);
        ahoyOnboarderCard1.setTitleColor(R.color.white);
        ahoyOnboarderCard1.setDescriptionColor(R.color.grey_200);
        ahoyOnboarderCard1.setTitleTextSize(dpToPixels(14, this));
        ahoyOnboarderCard1.setDescriptionTextSize(dpToPixels(8, this));

        AhoyOnboarderCard ahoyOnboarderCard2 = new AhoyOnboarderCard("KNOW YOUR MEAT", "All major Butcher cuts are defined to industry standards", R.drawable.ic_cleaver);
        ahoyOnboarderCard2.setBackgroundColor(R.color.black_transparent);
        ahoyOnboarderCard2.setTitleColor(R.color.white);
        ahoyOnboarderCard2.setDescriptionColor(R.color.grey_200);
        ahoyOnboarderCard2.setTitleTextSize(dpToPixels(14, this));
        ahoyOnboarderCard2.setDescriptionTextSize(dpToPixels(8, this));

        AhoyOnboarderCard ahoyOnboarderCard3 = new AhoyOnboarderCard("Hang it on a Scale", "Once you know how much your carcus weighs... you're Done", R.drawable.ic_scale);
        ahoyOnboarderCard3.setBackgroundColor(R.color.black_transparent);
        ahoyOnboarderCard3.setTitleColor(R.color.white);
        ahoyOnboarderCard3.setDescriptionColor(R.color.grey_200);
        ahoyOnboarderCard3.setTitleTextSize(dpToPixels(14, this));
        ahoyOnboarderCard3.setDescriptionTextSize(dpToPixels(8, this));

        AhoyOnboarderCard ahoyOnboarderCard4 = new AhoyOnboarderCard("Cow-culations", "Giving as accurate an sales estimate as any out there!", R.drawable.ic_dollar_coins);
        ahoyOnboarderCard4.setBackgroundColor(R.color.black_transparent);
        ahoyOnboarderCard4.setTitleColor(R.color.white);
        ahoyOnboarderCard4.setDescriptionColor(R.color.grey_200);
        ahoyOnboarderCard4.setTitleTextSize(dpToPixels(14, this));
        ahoyOnboarderCard4.setDescriptionTextSize(dpToPixels(8, this));


        List<AhoyOnboarderCard> pages = new ArrayList<>();
        pages.add(ahoyOnboarderCard1);
        pages.add(ahoyOnboarderCard2);
        pages.add(ahoyOnboarderCard3);
        pages.add(ahoyOnboarderCard4);
        setImageBackground(R.drawable.beef_bg);

        setOnboardPages(pages);
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void onFinishButtonPressed() {
        new PreferenceManager(Welcome.this).SetIsOnboard(true);
        this.finish();
    }
}
