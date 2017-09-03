package com.iky.cowr.Adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.iky.cowr.Model.CowPortion;
import com.iky.cowr.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by obake on 9/2/2017.
 */

public class SpinnerPortionAdapter extends  ArrayAdapter<CowPortion> {

    private ArrayList<CowPortion> mItems;
    public static ArrayList<String> portionsLeft;
    private Context mContext;
    private  LayoutInflater mInflator;

    public SpinnerPortionAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<CowPortion> objects) {
        super(context, resource, objects);
        mContext =context;
        mInflator =LayoutInflater.from(context);
        mItems= new ArrayList<CowPortion>();
        SetBasePortions(objects);
    }

    void SetBasePortions(ArrayList<CowPortion> cowPortions){
        portionsLeft = new ArrayList<>();
        portionsLeft.add("Ditlhako");
        portionsLeft.add("Lebala la Mpa");
        portionsLeft.add("Lebete");
        portionsLeft.add("Letlalo");
        portionsLeft.add("Makgwafo");
        portionsLeft.add("Mala le Mogodu");
        portionsLeft.add("Marapo");
        portionsLeft.add("Sebete");
        portionsLeft.add("Tlhogo");
        portionsLeft.add("Tlhogo (le Ditlhako)");
        for(int i=0;i<cowPortions.size();i++){
            CowPortion s = cowPortions.get(i);
            for(int x=0;x<portionsLeft.size();x++){
                String nm = portionsLeft.get(x);
                if(nm.equals(s.Name)){
                    //remove from available portions
                    portionsLeft.remove(x);
                    break;
                }
            }
        }
        for(int i =0;i<portionsLeft.size();i++){
            String nm =portionsLeft.get(i);
            CowPortion p = new CowPortion();
            p.Name = nm;
            switch (nm){
                case "Ditlhako":
                    p.Icon = R.drawable.ic_hoofs_64;
                    break;
                case "Lebete":
                    p.Icon = R.drawable.ic_spleen_64;
                    break;
                case "Sebete":
                    p.Icon = R.drawable.ic_liver_64;
                    break;
                case "Mala le Mogodu":
                    p.Icon = R.drawable.ic_stomach_64;
                    break;
                case "Lebala la Mpa":
                    p.Icon = R.drawable.ic_stomach_64;
                    break;
                case "Letlalo":
                    p.Icon = R.drawable.ic_leather_64;
                    break;
                case "Marapo":
                    p.Icon = R.drawable.ic_bone_64;
                    break;
                case "Makgwafo":
                    p.Icon = R.drawable.ic_lungs_64;
                    break;
                case "Tlhogo":
                    p.Icon = R.drawable.ic_cowhead_64;
                    break;

                case "Tlhogo (le Ditlhako)":
                    p.Icon = R.drawable.ic_cowhead_64;
                    break;
            }
            if(mItems.contains(p)){

            }
            else {
                mItems.add(p);
            }
        }
    }



    @Override
    public int getCount() {
        return mItems.size();
    }

    public CowPortion getPortion(int position){
        return  mItems.get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

     @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            View row = mInflator.inflate(R.layout.simple_spinner_item, parent, false);
            CowPortion item = mItems.get(position);
            ImageView icon = (ImageView) row.findViewById(R.id.icon);
            TextView text = (TextView) row.findViewById(R.id.text1);
            text.setText(item.Name);
            icon.setImageResource(item.Icon);

            return row;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

            View row = mInflator.inflate(R.layout.simple_spinner_item, viewGroup, false);
            CowPortion item = mItems.get(position);
            ImageView icon = (ImageView) row.findViewById(R.id.icon);
            TextView text = (TextView) row.findViewById(R.id.text1);
            text.setText(item.Name);
            icon.setImageResource(item.Icon);

            return row;

    }
}
