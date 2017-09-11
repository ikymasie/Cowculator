package com.iky.cowr.Fragments;

import android.content.Context;
import android.net.Uri;
import android.net.wifi.aware.PublishConfig;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.iky.cowr.Adapters.CarcusAdapter;
import com.iky.cowr.GlobalVariables;
import com.iky.cowr.PreferenceManager;
import com.iky.cowr.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LiveWeightResults#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LiveWeightResults extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static  Double TotalWeight = 0.0;
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.lblAmount)
    EditText lblAmount;
    @BindView(R.id.grade_b_label)
    TextView gradeBLabel;
    @BindView(R.id.grade_c_label)
    TextView gradeCLabel;
    @BindView(R.id.grade_d_label)
    TextView gradeDLabel;
    @BindView(R.id.carcusList)
    RecyclerView carcusList;
    Unbinder unbinder;

    // TODO: Rename and change types of parameters
    private String mParam1;

    private OnFragmentInteractionListener mListener;

    public LiveWeightResults() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param totalWeight Parameter 1.
     * @return A new instance of fragment LiveWeightResults.
     */
    // TODO: Rename and change types and number of parameters
    public static LiveWeightResults newInstance(String totalWeight) {
        LiveWeightResults fragment = new LiveWeightResults();
        Bundle args = new Bundle();
        args.putString("weight", totalWeight);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            TotalWeight = Double.parseDouble( getArguments().getString("weight"));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_live_weight_results, container, false);
        unbinder = ButterKnife.bind(this, view);
        Double mTotal=TotalWeight;
        CarcusAdapter adapter = new CarcusAdapter(this.getActivity(),mTotal);
        carcusList.setLayoutManager(new LinearLayoutManager(this.getActivity()));
         carcusList.setAdapter(adapter);
        PreferenceManager pref= new PreferenceManager(this.getActivity());
        Double myPrice = pref.GetLiveWeightPPKG();
        Double BMCOffer = 0.0;
        if(mTotal>=180.0){
            BMCOffer =19.50;
        }
        else{
            BMCOffer =15.50;
        }
        lblAmount.setText(String.format("%.2f",(myPrice* mTotal)));
        gradeBLabel.setText("P"+ String.format("%.2f",BMCOffer* mTotal));
        gradeCLabel.setText("P"+ String.format("%.2f",(myPrice* mTotal)*0.9));
        gradeDLabel.setText("P"+ String.format("%.2f",(myPrice* mTotal)*0.8));


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
