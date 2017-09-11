package com.iky.cowr.Core;

import android.content.Context;
import android.util.Log;

import com.iky.cowr.Model.CowPortion;
import com.noodle.Noodle;
import com.noodle.collection.Collection;
import com.noodle.description.Description;

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
        db= Noodle.with(context).addType(Description.of(CowPortion.class).withIdField("id").build()).build();

        dbContext =db.collectionOf(CowPortion.class);
        tbl_portions =(List<CowPortion>) dbContext.all().now();
        Data = new ArrayList<CowPortion>();
        for(int i =0;i<tbl_portions.size();i++){
            Data.add(tbl_portions.get(i));
        }
    }
    public ArrayList<CowPortion> getItemsRequiringPPKG(){
        ArrayList<CowPortion> list = new ArrayList<>();
        for(int i =0;i<Data.size();i++){
         CowPortion x= Data.get(i);
            if(x.IsPerKg){
                list.add(x);
            }
        }
        return  list;
    }
    public ArrayList<CowPortion> getFixedPriceItems(){
        ArrayList<CowPortion> list = new ArrayList<>();
        for(int i =0;i<Data.size();i++){
            CowPortion x= Data.get(i);
            if(!x.IsPerKg){
                list.add(x);
            }
        }
        return  list;
    }
    public   void SaveNewPortion(CowPortion c){
        dbContext.put(c).now();
    }
    public   boolean DeletePortion(long c){
        if(dbContext!=null) {
           CowPortion d= Data.get(0);
            try {

              dbContext.delete(c).now();
            return true;
            }
            catch (NullPointerException e){
            return false;
            }
            catch (RuntimeException e){
                 Log.d("Error",e.getMessage());
                return  false;
            }
        }else{
            return false;
        }
    }
    public  void GetPortion(long c){
        dbContext.get(c).now();
    }

}
