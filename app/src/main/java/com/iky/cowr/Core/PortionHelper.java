package com.iky.cowr.Core;

import android.content.Context;

import com.iky.cowr.Model.CowPortion;
import com.noodle.Noodle;
import com.noodle.collection.Collection;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by iky on 9/1/2017.
 */

public class PortionHelper {

    private static PortionHelper ourInstance;
    private static Context context;
    private static Noodle db;
    private static List<CowPortion> tbl_portions;
    public static ArrayList<CowPortion> Data;
    private static Collection dbContext;

    public  static PortionHelper getInstance(Context c) {
        ourInstance =new PortionHelper(c);
        return ourInstance;
    }
    private PortionHelper(Context ctx) {
        context = ctx;
        db= Noodle.with(context).addType(CowPortion.class).build();
        dbContext =db.collectionOf(CowPortion.class);
        tbl_portions =(List<CowPortion>) dbContext.all().now();
        Data = new ArrayList<CowPortion>();
        for(int i =0;i<tbl_portions.size();i++){
            Data.add(tbl_portions.get(i));
        }
    }
    public   void SaveNewPortion(CowPortion c){
        dbContext.put(c).now();
    }
    public   void DeletePortion(long c){
        dbContext.delete(c).now();
    }
    public  void GetPortion(long c){
        dbContext.get(c).now();
    }

}
