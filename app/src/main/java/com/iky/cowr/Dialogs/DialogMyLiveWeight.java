package com.iky.cowr.Dialogs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
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
import com.iky.cowr.ModeSelector;
import com.iky.cowr.Model.CowPortion;
import com.iky.cowr.MyCutsCreator;
import com.iky.cowr.PreferenceManager;
import com.iky.cowr.R;

import java.util.ArrayList;

/**
 * Created by obake on 9/1/2017.
 */

public class DialogMyLiveWeight extends DialogFragment {

    DialogMyLiveWeight instance;


    EditText txtItemPrice;
    Button btnCancel;
    Button btnAddNew;
    PreferenceManager pref;

    ModeSelector parent1;
    MainActivity parent2;
    boolean isFromMenu;
    public DialogMyLiveWeight(){
    instance = this;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        getDialog().getWindow().setSoftInputMode(
        WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        instance=this;
        if(this.getActivity().getClass()== ModeSelector.class){
         parent1= (ModeSelector) this.getActivity();
            isFromMenu=true;
        }
        else  if(this.getActivity().getClass()== MainActivity.class){
            isFromMenu=false;
            parent2=(MainActivity)this.getActivity();

        }

        View view = inflater.inflate(R.layout.dialog_my_live_weight, container);
         txtItemPrice= (EditText) view.findViewById(R.id.txt_live_weight);

        btnCancel= (Button)view.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new btnCancelListner());

        btnAddNew=(Button)view.findViewById(R.id.btnAddNew);
        btnAddNew.setOnClickListener(new btnAddNewListner());

         pref=new PreferenceManager(this.getActivity());

        txtItemPrice.setText(String.valueOf( pref.GetLiveWeightPPKG()));

        return view;

    }


    public class btnAddNewListner implements View.OnClickListener{
        @Override
        public void onClick(View view) {
          if(txtItemPrice.getText()!=null){
              pref.SetLiveWeightPPKG(txtItemPrice.getText().toString());
              if(isFromMenu){
                  //use parent to dismiss
                  pref.SetIsInitialized(true);
                  parent1.CloseDialogFromParent();
              }
              else{
                  instance.dismiss();
              }
          }
          else{
              Toast.makeText(instance.getActivity(),"Enter valid amount",Toast.LENGTH_SHORT).show();
          }
        }
    }
    public class btnCancelListner implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            instance.dismiss();
        }
    }

}
