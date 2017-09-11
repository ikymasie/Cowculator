package com.iky.cowr.Dialogs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.iky.cowr.Adapters.MissingWeightsAdapter;
import com.iky.cowr.Adapters.OnEditTextChanged;
import com.iky.cowr.Adapters.SpinnerPortionAdapter;
import com.iky.cowr.Core.PortionHelper;
import com.iky.cowr.Model.CowPortion;
import com.iky.cowr.MyCutsCreator;
import com.iky.cowr.R;
import com.iky.cowr.Results;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by obake on 9/1/2017.
 */

public class DialogMissingWeights extends DialogFragment implements OnEditTextChanged {

    DialogMissingWeights instance;


    Button btnCancel;
    Button btnAddNew;
    Results parent;
    boolean isPerKg=false;
    ArrayList<CowPortion> myPortions = new ArrayList<CowPortion>();
    MissingWeightsAdapter adapter;
    RecyclerView list;
    public DialogMissingWeights(){
    instance = this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        getDialog().getWindow().setSoftInputMode(
        WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        parent =  (Results)this.getActivity();

        View view = inflater.inflate(R.layout.dialog_ppkg_items, container);
        myPortions = PortionHelper.getInstance(this.getActivity()).getItemsRequiringPPKG();
        list = (RecyclerView)view.findViewById(R.id.new_weight_list);
        list.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        adapter = new MissingWeightsAdapter(this.getActivity(),this,myPortions);
        list.setAdapter(adapter);
        btnCancel= (Button)view.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new btnCancelListner());

        btnAddNew=(Button)view.findViewById(R.id.btnAddNew);
        btnAddNew.setOnClickListener(new btnAddNewListner());


        return view;

    }

    @Override
    public void onTextChanged(int position, String charSeq) {
        try{
        CowPortion currentEdit = myPortions.get(position);
        currentEdit.Weight = Double.parseDouble(charSeq);
        Log.d("EDIT", currentEdit.Name  + " now wieghs " + charSeq);
        }
        catch (Exception e){
            Log.d("Error",e.getMessage());
        }

    }


    public class btnAddNewListner implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            HashMap<String,Double> points = new HashMap<String,Double>();
            for(int i=0;i<myPortions.size();i++){
                CowPortion item =myPortions.get(i);
                points.put(item.Name,item.Weight);
            }
            parent.DisplayCustomResult(points);
            instance.dismiss();
        }
    }
    public class btnCancelListner implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            parent.CancelDialogWithActivity();
        }
    }

}
