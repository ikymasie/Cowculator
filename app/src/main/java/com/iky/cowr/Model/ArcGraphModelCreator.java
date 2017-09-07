package com.iky.cowr.Model;

import android.content.Context;
import android.graphics.Color;

import com.iky.cowr.R;

import java.util.ArrayList;
import java.util.HashMap;

import devlight.io.library.ArcProgressStackView;

/**
 * Created by obake on 9/6/2017.
 */

public class ArcGraphModelCreator {
    private static  ArcGraphModelCreator ourInstance;
    private static Context context;
    private static String[] endColors,bgColors,startColors;
    private static int[] mEndColors,mStartColor,mBgColors;
   /* final String[] startColors = getResources().getStringArray(R.array.devlight);
    final String[] endColors = getResources().getStringArray(R.array.default_preview);
    final String[] bgColors = getResources().getStringArray(R.array.medical_express);
*/
    public  static ArcGraphModelCreator getInstance(Context ctx) {
        return ourInstance = new ArcGraphModelCreator(ctx);
    }

    private ArcGraphModelCreator(Context ctx) {
        context=ctx;
        startColors = context. getResources().getStringArray(R.array.devlight);
        endColors = context. getResources().getStringArray(R.array.bg);
        bgColors = context. getResources().getStringArray(R.array.bg);
    }

    public  ArrayList<ArcProgressStackView.Model> GetGraph(Double totalAmount, HashMap<String,Double> items){
        ArrayList<ArcProgressStackView.Model> points =new ArrayList<>();
        for(int i=0;i< items.size();i++){
            String nm =(String)items.keySet().toArray()[i];
            Double amt = items.get(nm);
            Double perc = ((amt/totalAmount)*100);
            points.add(new ArcProgressStackView.Model(nm, perc.intValue(), Color.parseColor(bgColors[i]), Color.parseColor(startColors[i])));
        }

        return points;
    }
    public  ArrayList<ArcProgressStackView.Model> GetDefaultGraph(Double totalAmount, HashMap<String,Double> items){
        ArrayList<ArcProgressStackView.Model> points =new ArrayList<>();
        for(int i=0;i< items.size();i++){
            String nm =(String)items.keySet().toArray()[i];
            Double amt = items.get(nm);
            Double perc = ((amt/totalAmount)*100);
            points.add(new ArcProgressStackView.Model(nm,0, Color.parseColor(bgColors[i]), Color.parseColor(startColors[i])));
        }

        return points;
    }
}
