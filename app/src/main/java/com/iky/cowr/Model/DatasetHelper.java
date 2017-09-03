package com.iky.cowr.Model;


import android.content.Context;

import com.iky.cowr.GlobalVariables;
import com.iky.cowr.PreferenceManager;
import com.iky.cowr.R;

import java.util.ArrayList;

/**
 * Created by obake on 8/24/2017.
 */

public class DatasetHelper {
    public ArrayList<CowPortion> GetResultBreakdown(Context c, Double carcus_weight){
        ArrayList<CowPortion>list = new ArrayList<CowPortion>();
        CowPortion portion =new CowPortion();
        PreferenceManager pref = new PreferenceManager(c);
        //Chuck
        portion.Name="CHUCK";
        portion.Icon = R.drawable.chuck;
        portion.PPKG  =pref.GetChuckPKG();
        portion.Percent_of_carcus =  GlobalVariables.PORTION_CHUCK;
        portion.Weight = carcus_weight* (GlobalVariables.PORTION_CHUCK);
        portion.Price = portion.Weight*portion.PPKG;
        list.add(portion);
        portion= new CowPortion();

        portion.Name="BRISKET";
        portion.Icon = R.drawable.brisket;
        portion.PPKG  =pref.GetBrisketPKG();
        portion.Percent_of_carcus =  GlobalVariables.PORTION_BRISKET;
        portion.Weight = carcus_weight* (GlobalVariables.PORTION_BRISKET);
        portion.Price = portion.Weight*portion.PPKG;
        list.add(portion);
        portion= new CowPortion();

        portion.Name="RIB";
        portion.Icon = R.drawable.rib;
        portion.PPKG  =pref.GetRibPKG();
        portion.Percent_of_carcus =  GlobalVariables.PORTION_RIB;
        portion.Weight = carcus_weight* (GlobalVariables.PORTION_RIB);
        portion.Price = portion.Weight*portion.PPKG;
        list.add(portion);
        portion= new CowPortion();

        portion.Name="ROUND";
        portion.Icon = R.drawable.round;
        portion.PPKG  =pref.GetRoundPKG();
        portion.Percent_of_carcus =  GlobalVariables.PORTION_ROUND;
        portion.Weight = carcus_weight* (GlobalVariables.PORTION_ROUND);
        portion.Price = portion.Weight*portion.PPKG;
        list.add(portion);
        portion= new CowPortion();



        portion.Name="LOIN";
        portion.Icon = R.drawable.loin;
        portion.PPKG  =pref.GetLoinPKG();
        portion.Percent_of_carcus =  GlobalVariables.PORTION_LOIN;
        portion.Weight = carcus_weight* (GlobalVariables.PORTION_LOIN);
        portion.Price = portion.Weight*portion.PPKG;
        list.add(portion);
        portion= new CowPortion();

        portion.Name="MALA le MOGODU";
        portion.Icon = R.drawable.mala;
        portion.PPKG  =pref.GetMalaPKG();
        portion.Percent_of_carcus =  GlobalVariables.PORTION_MALA;
        portion.Weight = carcus_weight* (GlobalVariables.PORTION_MALA);
        portion.Price = portion.Weight*portion.PPKG;
        list.add(portion);
        portion= new CowPortion();

        portion.Name="SHANK";
        portion.Icon = R.drawable.shank;
        portion.PPKG  =pref.GetShankPKG();
        portion.Percent_of_carcus =  GlobalVariables.PORTION_SHANK;
        portion.Weight = carcus_weight* (GlobalVariables.PORTION_SHANK);
        portion.Price = portion.Weight*portion.PPKG;
        list.add(portion);
        portion= new CowPortion();

        portion.Name="FLANK";
        portion.Icon = R.drawable.flank;
        portion.PPKG  =pref.GetFlankPKG();
        portion.Percent_of_carcus =  GlobalVariables.PORTION_FLANK;
        portion.Weight = carcus_weight* (GlobalVariables.PORTION_FLANK);
        portion.Price = portion.Weight*portion.PPKG;
        list.add(portion);
        portion= new CowPortion();



        portion.Name="SHORT PLATE";
        portion.Icon = R.drawable.shortplate;
        portion.PPKG  =pref.GetShortPlatePKG();
        portion.Percent_of_carcus =  GlobalVariables.PORTION_SHORTPLATE;
        portion.Weight = carcus_weight* (GlobalVariables.PORTION_SHORTPLATE);
        portion.Price = portion.Weight*portion.PPKG;
        list.add(portion);



        return  list;
    }
}
