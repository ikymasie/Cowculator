package com.iky.cowr.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.iky.cowr.Core.PortionHelper;
import com.iky.cowr.Model.CowPortion;
import com.iky.cowr.Model.DatasetHelper;
import com.iky.cowr.R;

import java.util.ArrayList;

/**
 * Created by obake on 7/21/2017.
 */

public class CarcusAdapter extends RecyclerView.Adapter<CarcusAdapter.mViewHolder>   {
        private Context mContext;
        private ArrayList<CowPortion> mItems;
        private int lastPosition = -1;
        private Double mTotalWeight=0.0;
       OnItemClick mListner;
        public CarcusAdapter(Context c,  Double totalWeight){
            super();
            mItems   =new DatasetHelper().GetResultBreakdown(c,totalWeight);
            mContext=c;
            mTotalWeight = totalWeight*0.62;
        }


        public class  mViewHolder extends RecyclerView.ViewHolder  {
             CowPortion item;
            // Your holder should contain a member variable
            // for any view that will be set as you render a row
            public TextView nameTextView;
             public TextView subtitleTextView;

             public TextView amountView;
            public ProgressBar progressBar;


            // We also create a constructor that accepts the entire item row
            // and does the view lookups to find each subview
            public mViewHolder(View itemView) {
                // Stores the itemView in a public final member variable that can be used
                // to access the context from any ViewHolder instance.
                super(itemView);
                 nameTextView = (TextView) itemView.findViewById(R.id.lblTitle);
                subtitleTextView= (TextView) itemView.findViewById(R.id.lblPerc);
                amountView= (TextView) itemView.findViewById(R.id.lblWeight);
                progressBar=(ProgressBar)itemView.findViewById(R.id.progressBar);
              }



         }

        @Override
        public mViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            final Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);

            // Inflate the custom layout
            View contactView = inflater.inflate(R.layout.carcus_breakdown_listitem, parent, false);
              // Return a new holder instance
            mViewHolder viewHolder = new mViewHolder(contactView);

            return viewHolder;
        }


        @Override
        public void onBindViewHolder(mViewHolder holder, final int position) {
             CowPortion item = mItems.get(position);
            Double d=0.0;
            //SET DATA TO VIEWHOLDER

              Double i=   item.Percent_of_carcus;
                holder.subtitleTextView.setText("Makes up "+String.format("%.2f",(i.doubleValue()*100)) + "% of the Total Carcus Weight");
             holder.nameTextView.setText(item.Name);
            holder.amountView.setText(String.format("%.2f",(i.doubleValue()*mTotalWeight)) +"kg");
            Double p=(i.doubleValue()*100);
            holder.progressBar.setProgress(p.intValue());
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
