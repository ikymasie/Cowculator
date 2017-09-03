package com.iky.cowr;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;


/**
 * Created by obake on 7/26/2017.
 */
public class GlobalApplication extends Application{
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
          CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/RobotoLight.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

    }
}
