package com.iky.cowr;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.iky.cowr.Dialogs.DialogMyLiveWeight;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ModeSelector extends AppCompatActivity {
    private static final int INTENT_CUTS = 729;
    private static final int INTENT_LIVE = 915;
    private static final int INTENT_CUSTOM = 77;



    @BindView(R.id.btnButcherMode)
    RelativeLayout btnButcherMode;
    @BindView(R.id.btnLiveMode)
    RelativeLayout btnLiveMode;
    @BindView(R.id.btnCustomMode)
    RelativeLayout btnCustomMode;
    DialogMyLiveWeight mDialog;
    PreferenceManager pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode_selector);
        ButterKnife.bind(this);
        pref = new PreferenceManager(this);
    }

public  void CloseDialogFromParent(){
    if(mDialog==null){

    }
    else{
        pref.SetMode("live");
        mDialog.dismiss();
    }
    this.setResult(RESULT_OK);
    this.finish();
}

    @OnClick({R.id.btnButcherMode, R.id.btnLiveMode, R.id.btnCustomMode})
    public void onViewClicked(View view) {
        Intent dash;
        switch (view.getId()) {
            case R.id.btnButcherMode:
                pref.SetMode("cuts");
                dash = new Intent(ModeSelector.this,Settings.class);
                startActivityForResult(dash,INTENT_CUTS);
                break;
            case R.id.btnLiveMode:
              mDialog   = new DialogMyLiveWeight();
                mDialog.show(this.getSupportFragmentManager(),"parentMode");
             /*
                dialog.onDismiss(new DialogInterface() {
                    @Override
                    public void cancel() {
                        Toast.makeText(ModeSelector.this,"Cancelled",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void dismiss() {
                        Toast.makeText(ModeSelector.this,"Weight Saved!",Toast.LENGTH_SHORT).show();
                        pref.SetMode("live");
                        ModeSelector.this.setResult(RESULT_OK);
                        ModeSelector.this.finish();
                    }
                });
            */
                break;
            case R.id.btnCustomMode:
                pref.SetMode("custom");
                dash = new Intent(ModeSelector.this,MyCutsCreator.class);
                startActivityForResult(dash,INTENT_CUSTOM);

                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode== RESULT_CANCELED){
            return;
        }
        else{
            pref.SetIsInitialized(true);
            this.finish();
        }


        super.onActivityResult(requestCode, resultCode, data);
    }


}
