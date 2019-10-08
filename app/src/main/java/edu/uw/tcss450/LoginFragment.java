package edu.uw.tcss450;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import edu.uw.tcss450.model.Credentials;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LoginFragment.OnLoginFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class LoginFragment extends Fragment implements View.OnClickListener {

    private OnLoginFragmentInteractionListener mListener;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        Button b = (Button) v.findViewById(R.id.registerActions);
        b.setOnClickListener(this); //add this Fragment Object as the OnClickListener
        Button login = (Button) v.findViewById(R.id.sign_in_action);
        login.setOnClickListener(this);
        //Use a Lamda expression to add the OnClickListener
//        b.setOnClickListener(view -> mListener.onLoginSuccess(c, ""));

        return v;
    }

    public void onClick(View view) {
        if (mListener != null) {
            switch (view.getId()) {
                case R.id.registerActions:
                    mListener.onRegisterClicked();
                    break;
                case R.id.sign_in_action:

                    EditText editText = (EditText) getActivity().findViewById(R.id.email);;
                    String email = editText.getText().toString();
                    String password = ((EditText) getActivity().findViewById(R.id.password)).getText().toString();
                    Credentials.Builder cr = new Credentials.Builder(email,password);
                    Credentials c = cr.build();
                    Log.println(Log.WARN,"Login", c.asJSONObject().toString());
                    mListener.onLoginSuccess(c, "");
                    break;
                default:
                    Log.wtf("", "Didn't expect to see me...");
            }
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnLoginFragmentInteractionListener) {
            mListener = (OnLoginFragmentInteractionListener) context;
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
    public interface OnLoginFragmentInteractionListener {
        // TODO: Update argument type and name
        void onLoginSuccess(Credentials cr, String st);
        void onRegisterClicked();
    }
}
