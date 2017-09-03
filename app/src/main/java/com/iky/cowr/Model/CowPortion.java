package com.iky.cowr.Model;

import com.noodle.Id;

import java.nio.DoubleBuffer;

/**
 * Created by obake on 8/24/2017.
 * The live weight method is when they take the live cow and put it on a scale and buy it as per the weight.
 * Say your cow weighs 250kg and theier buying price is P19.50 per kg then they will buy it at 250*19.50.
 * This is what most local butcheries do.
 * But in some instances you get other buyers who will kill the cow and weigh as per carcass item or part
 * and pay you the preciding total weight.
 * This is ideal for big retailers as they know they can rip you off cause if they buy it live thay means blood,
 * water and moswang are included yet they are going to throw away.
 * Now BMC buys prime 0 tooth until Grade D at a rate of 19.50 for all cows weighing above 180kg and above.
 * Any cow that weighs less than 180kg they buy at P15.50 going down to P10.00 per kg as you go down the grades.
 * But in other cases if it is a prime 0 tooth it will go to the feedlots but still the rates are low for the farmer
 */

public class CowPortion {

    @Id
    public long id;

    public String Name ;
    public Double PPKG ;
    public Double Percent_of_carcus ;
    public Double Weight ;
    public Double Price ;
    public Integer Icon;
    public boolean IsPerKg;

    public CowPortion(){

    }
    public CowPortion(String name, boolean isPerKg, Double price, Integer icon){

        this.Name = name;
        this.IsPerKg = isPerKg;
        this.Price = price;
        this.Icon=icon;

    }
    public CowPortion(String name, boolean isPerKg, Double price, Integer icon, Double weight, Double PPKG, Double percent_of_carcus){

        this.Name = name;
        this.IsPerKg = isPerKg;
        this.Price = price;
        this.Icon=icon;
        this.Weight = weight;
        this.PPKG = PPKG;
        this.Percent_of_carcus=percent_of_carcus;
    }

}
