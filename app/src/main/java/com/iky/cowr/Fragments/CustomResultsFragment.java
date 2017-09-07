package com.iky.cowr.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iky.cowr.GlobalVariables;
import com.iky.cowr.Model.ArcGraphModelCreator;
import com.iky.cowr.PreferenceManager;
import com.iky.cowr.R;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import devlight.io.library.ArcProgressStackView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link custom_cuts_results.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link custom_cuts_results#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CustomResultsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static  HashMap<String,Double> mGraphItems = new HashMap<>();
    private static  Double mTotalAmount = 0.00;
    private static  Double mCarcusAmount = 0.00;
    private static  Double mFixedAmount = 0.00;
    private static  Double mItemsAmount = 0.00;


    @BindView(R.id.lbl_Total_made)
    TextView lblTotal;
    @BindView(R.id.lbl_beef_amount)
    TextView lblBeefAmount;
    @BindView(R.id.lbl_fixed_items_amount)
    TextView lblFixedItemsAmount;
    @BindView(R.id.lbl_wieghed_items_amount)
    TextView lblWieghedItemsAmount;
    @BindView(R.id.arcProgressStackView)
    ArcProgressStackView arcProgressStackView;
    @BindView(R.id.lblExtraInfo)
    TextView lblExtraInfo;

    @BindView(R.id.lblAmountOther)
    TextView lbl_other_amount;

    Unbinder unbinder;


    // TODO: Rename and change types of parameters
    private HashMap<String,Double> graphItems;
    private String totalWeight;

    private OnFragmentInteractionListener mListener;

    public CustomResultsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment custom_cuts_results.
     */
    // TODO: Rename and change types and number of parameters
    public static CustomResultsFragment newInstance(HashMap<String,Double> param1, Double carcusWeight, Double fixedItemTotal,Double weightItemTotal ) {
        CustomResultsFragment fragment = new CustomResultsFragment();
        Bundle args = new Bundle();
       args.putSerializable("param1",param1);
        args.putDouble("param2", fixedItemTotal);
        args.putDouble("param3", weightItemTotal);
        args.putDouble("param0",carcusWeight);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mGraphItems =(HashMap<String, Double>) getArguments().getSerializable("param1");
            mFixedAmount =   getArguments().getDouble("param2");
            mItemsAmount = getArguments().getDouble("param3");
            Double weight = getArguments().getDouble("param0");
            Double ppkg = new PreferenceManager(this.getActivity()).GetCarcusPKG();
            mCarcusAmount  = weight*ppkg;
            mTotalAmount = mFixedAmount+mCarcusAmount+mItemsAmount;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_custom_cuts_results, container, false);
        unbinder = ButterKnife.bind(this, view);
         lblTotal.setText(String.format("%.2f",mTotalAmount));
        lblBeefAmount.setText("P"+String.format("%.2f",mCarcusAmount));
        lblFixedItemsAmount.setText("P"+String.format("%.2f",mFixedAmount));
        lblWieghedItemsAmount.setText("P"+String.format("%.2f",mItemsAmount));
        lbl_other_amount.setText("P"+String.format("%.2f",mItemsAmount + mFixedAmount));
        arcProgressStackView.setModels(ArcGraphModelCreator.getInstance(CustomResultsFragment.this.getActivity()).GetGraph(mFixedAmount+mItemsAmount,mGraphItems));
        arcProgressStackView.animateProgress();
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
