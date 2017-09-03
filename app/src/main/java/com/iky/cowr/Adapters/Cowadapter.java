package com.iky.cowr.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.iky.cowr.Core.Cowrculator;
import com.iky.cowr.Model.CowPortion;

import com.iky.cowr.Model.DatasetHelper;
import com  .iky.cowr.R;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by obake on 7/21/2017.
 */

public class Cowadapter extends RecyclerView.Adapter<Cowadapter.mViewHolder>   {
    private Context mContext;
    private ArrayList<CowPortion> mItems;
    private int lastPosition = -1;
   OnItemClick mListner;
    public Cowadapter(Context c, Double carcusWeight){
        super();
        mItems =new DatasetHelper().GetResultBreakdown(c,carcusWeight);
        mContext=c;
    }


    public class  mViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
         CowPortion item;
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView nameTextView;
         public TextView subtitleTextView;

         public TextView amountView;
        public TextView ppkgView;
        public ImageView iconView;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public mViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);
             nameTextView = (TextView) itemView.findViewById(R.id.title);
            subtitleTextView= (TextView) itemView.findViewById(R.id.subtitle);
            amountView= (TextView) itemView.findViewById(R.id.amount);
             ppkgView= (TextView) itemView.findViewById(R.id.selling_price);
           iconView= (ImageView) itemView.findViewById(R.id.icon);

            itemView.setOnClickListener(this);
         }


         @Override
         public void onClick(View v) {
             if (mListner != null) mListner.onClick(v, getAdapterPosition());
         }
     }



    @Override
    public mViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.list_item, parent, false);
          // Return a new holder instance
        mViewHolder viewHolder = new mViewHolder(contactView);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(mViewHolder holder, final int position) {
        CowPortion item = mItems.get(position);
        Double d=0.0;
        //SET DATA TO VIEWHOLDER
        holder.nameTextView.setText(item.Name.toUpperCase());
        holder.amountView.setText(String.format("%.2f",item.Price));
        holder.ppkgView.setText(" Selling at P"+String.format("%.2f",item.PPKG) + "/kg");
       holder.subtitleTextView.setText("You get approx. " + String.format("%.1f",item.Weight) + "kg" );
       holder.iconView.setImageResource(item.Icon);
        setAnimation(holder.itemView,position);
    }
    private void setAnimation(View viewToAnimate, int position)
    {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition)
        {

            Animation animation = AnimationUtils.loadAnimation(mContext,R.anim.pull_in_right);
            //  Animation fadeOut = new AlphaAnimation(0, 1);
            //   fadeOut.Interpolator = new AccelerateInterpolator();
            // fadeOut.Duration = 1000;

            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }
    public int getItemCount() {
        return mItems.size();
    }
}
