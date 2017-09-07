package com.iky.cowr.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.iky.cowr.Adapters.Cowadapter;
import com.iky.cowr.Core.Cowrculator;
import com.iky.cowr.GlobalVariables;
import com.iky.cowr.R;
import com.iky.cowr.Results;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ButcherResultsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ButcherResultsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String CARCUS_WEIGHT = "0.00";
    @BindView(R.id.lblAmount)
    EditText lblAmount;
    @BindView(R.id.lblDefaultUnit)
    TextView lblDefaultUnit;
    @BindView(R.id.errorMargin)
    TextView errorMargin;
    @BindView(R.id.grade_b_label)
    TextView gradeBLabel;
    @BindView(R.id.grade_c_label)
    TextView gradeCLabel;
    @BindView(R.id.grade_d_label)
    TextView gradeDLabel;
    @BindView(R.id.list)
    RecyclerView list;
    Unbinder unbinder;
    Double TotalWieght=0.0;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String getCarcusWeight;

    private OnFragmentInteractionListener mListener;

    public ButcherResultsFragment() {
        // Required empty public constructor
    }

    /**
     * Us  TotalWieght = Double.parseDouble(CARCUS_WEIGHT) * GlobalVariables.GRADE_A_SALABLE;
     e this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ButcherResultsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ButcherResultsFragment newInstance(String param1, String param2) {
        ButcherResultsFragment fragment = new ButcherResultsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(CARCUS_WEIGHT, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            getCarcusWeight = getArguments().getString(CARCUS_WEIGHT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_butcher_results, container, false);
        unbinder = ButterKnife.bind(this, view);


         Cowadapter adapter = new Cowadapter(this.getActivity(),Double.parseDouble(getCarcusWeight));
        list.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        list.setAdapter(adapter);
        Double amt = Cowrculator.getInstance(this.getActivity()).CalculateTotal(Double.parseDouble(getCarcusWeight));
        lblAmount.setText(String.format("%.2f",amt*GlobalVariables.GRADE_A_SALABLE));
        gradeBLabel.setText("P"+String.format("%.2f",amt*GlobalVariables.GRADE_B_SALABLE));
        gradeCLabel.setText("P"+String.format("%.2f",amt*GlobalVariables.GRADE_C_SALABLE));
        gradeDLabel.setText("P"+String.format("%.2f",amt*GlobalVariables.GRADE_D_SALABLE));

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
