package com.iky.cowr.Adapters;

import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.iky.cowr.Core.PortionHelper;
import com.iky.cowr.Model.CowPortion;
import com.iky.cowr.R;

import java.util.ArrayList;

/**
 * Created by obake on 7/21/2017.
 */

public class MissingWeightsAdapter extends RecyclerView.Adapter<MissingWeightsAdapter.mViewHolder>   {
        private Context mContext;
        private ArrayList<CowPortion> mItems;
        private int lastPosition = -1;
       OnEditTextChanged onEditTextChanged;
        public MissingWeightsAdapter(Context c, OnEditTextChanged onEditTextChanged1, ArrayList data){
            super();
            this.onEditTextChanged=onEditTextChanged1;
            mItems = data;
          //  mItems =portions;
            mContext=c;
        }


        public class  mViewHolder extends RecyclerView.ViewHolder   {
             CowPortion item;
            // Your holder should contain a member variable
            // for any view that will be set as you render a row
            public EditText nameTextView;
            public TextInputLayout inputLayout;

            public ImageView iconView;

            // We also create a constructor that accepts the entire item row
            // and does the view lookups to find each subview
            public mViewHolder(View itemView) {
                // Stores the itemView in a public final member variable that can be used
                // to access the context from any ViewHolder instance.
                super(itemView);
                 nameTextView = (EditText) itemView.findViewById(R.id.txt_input);
                iconView= (ImageView) itemView.findViewById(R.id.icon);
                inputLayout=(TextInputLayout) itemView.findViewById(R.id.txt_input_layout);

              }



         }

        @Override
        public mViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            final Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);

            // Inflate the custom layout
            View contactView = inflater.inflate(R.layout.custom_cut_result_listitem, parent, false);
              // Return a new holder instance
            mViewHolder viewHolder = new mViewHolder(contactView);

            return viewHolder;
        }


        @Override
        public void onBindViewHolder(mViewHolder holder, final int position) {
                CowPortion item = mItems.get(position);
                Double d=0.0;
                //SET DATA TO VIEWHOLDER
                holder.inputLayout.setHint(item.Name.toUpperCase()+" wieght (Kg)" );

                holder.nameTextView.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        onEditTextChanged.onTextChanged(position, charSequence.toString());

                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });
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
