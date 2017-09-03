package com.iky.cowr.Dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
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
import android.widget.Toast;

import com.iky.cowr.Adapters.SpinnerPortionAdapter;
import com.iky.cowr.Core.PortionHelper;
import com.iky.cowr.MainActivity;
import com.iky.cowr.Model.CowPortion;
import com.iky.cowr.MyCutsCreator;
import com.iky.cowr.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by obake on 9/1/2017.
 */

public class DialogNewPortions extends DialogFragment {

    DialogNewPortions instance;

    Spinner spnPartsLeft;
    Switch switchIsKg;
    TextView lblCurrency;
    TextView lblPriceType;
    EditText txtItemPrice;
    Button btnCancel;
    Button btnAddNew;
    MyCutsCreator parent;
    boolean isPerKg=false;
    ArrayList<CowPortion> myPortions = new ArrayList<CowPortion>();
    SpinnerPortionAdapter adapter;
    public DialogNewPortions(){
    instance = this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        getDialog().getWindow().setSoftInputMode(
        WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        parent =  (MyCutsCreator)this.getActivity();

        View view = inflater.inflate(R.layout.dialog_new_portion, container);
        spnPartsLeft  = (Spinner)view.findViewById(R.id.spn_parts_left);
        lblCurrency = (TextView)view.findViewById(R.id.lbl_currency);
        lblPriceType= (TextView)view.findViewById(R.id.lbl_price_type);
        txtItemPrice= (EditText) view.findViewById(R.id.txt_item_price);

        btnCancel= (Button)view.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new btnCancelListner());

        btnAddNew=(Button)view.findViewById(R.id.btnAddNew);
        btnAddNew.setOnClickListener(new btnAddNewListner());

        switchIsKg = (Switch) view.findViewById(R.id.switch_is_kg);
        switchIsKg.setOnCheckedChangeListener(new kgSwitchListner());

         ArrayList items = PortionHelper.getInstance(this.getActivity()).Data;
        myPortions=items;
         adapter = new SpinnerPortionAdapter(this.getActivity(),R.layout.simple_spinner_item,items);
        spnPartsLeft.setAdapter(adapter);

        return view;

    }


    public class kgSwitchListner implements CompoundButton.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            isPerKg=b;
            if(b){
                lblPriceType.setText("/Kg");
            }
            else{
                lblPriceType.setText("Once-off");

            }
        }
        }
    public class btnAddNewListner implements View.OnClickListener{
        @Override
        public void onClick(View view) {
          CowPortion i=   adapter.getPortion(spnPartsLeft.getSelectedItemPosition());
             if(switchIsKg.isChecked()){
                i.IsPerKg=true;
                i.PPKG = Double.parseDouble(txtItemPrice.getText().toString());
            }
            else{
                i.IsPerKg=false;

            }
            i.Price =  Double.parseDouble(txtItemPrice.getText().toString());
            parent.AddNewPortion(i);
            instance.dismiss();
        }
    }
    public class btnCancelListner implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            instance.dismiss();
        }
    }

}
