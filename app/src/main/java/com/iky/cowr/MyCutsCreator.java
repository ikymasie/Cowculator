package com.iky.cowr;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.iky.cowr.Adapters.PortionAdapter;
import com.iky.cowr.Core.PortionHelper;
import com.iky.cowr.Dialogs.DialogNewPortions;
import com.iky.cowr.Model.CowPortion;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyCutsCreator extends AppCompatActivity {

    @BindView(R.id.toolbar2)
    Toolbar toolbar2;
    @BindView(R.id.txtBeefPPKG)
    EditText txtBeefPPKG;
    @BindView(R.id.btnAddNewPortion)
    Button btnAddNewPortion;
    @BindView(R.id.list)
    RecyclerView list;

    PortionAdapter adapter;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cuts_creator);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        list.setLayoutManager(new GridLayoutManager(this,3));
        adapter =new PortionAdapter(this,PortionHelper.getInstance(MyCutsCreator.this).Data);
        list.setAdapter(adapter);
        String price = String.valueOf( new PreferenceManager(this).GetCarcusPKG());
        txtBeefPPKG.setText(price);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.save_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                this.finish();
                break;
            case R.id.action_save:
                if(txtBeefPPKG.getText().equals(null) || txtBeefPPKG.getText().equals("")){
                    Toast.makeText(MyCutsCreator.this,"Please enter an amount for Beef",Toast.LENGTH_SHORT).show();

                    return false;
                }
                else{
                    new PreferenceManager(MyCutsCreator.this).SetCarcusKG(txtBeefPPKG.getText().toString());
                }

                Double c = new PreferenceManager(MyCutsCreator.this).GetCarcusPKG();
                this.setResult(RESULT_OK);
                this.finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    public void AddNewPortion(CowPortion p){
        PortionHelper.getInstance(MyCutsCreator.this).SaveNewPortion(p);
        adapter.addNewItem(p);
        //// TODO: 9/1/2017 Add to list and Update adapter 
    }
    
    @OnClick(R.id.btnAddNewPortion)
    public void onViewClicked() {
        DialogNewPortions dialog =new DialogNewPortions();
        dialog.show(getSupportFragmentManager(),"newItemDialog");
    }
}
