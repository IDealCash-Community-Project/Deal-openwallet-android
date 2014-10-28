package com.coinomi.wallet.ui;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.coinomi.wallet.Constants;
import com.coinomi.wallet.R;
import com.coinomi.wallet.util.Keyboard;

import javax.annotation.Nullable;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link com.coinomi.wallet.ui.PasswordConfirmationFragment.Listener} interface
 * to handle interaction events.
 * Use the {@link PasswordConfirmationFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class PasswordConfirmationFragment extends Fragment {
    @Nullable private String message;
    private Listener mListener;

    static PasswordConfirmationFragment newInstance(String message, @Nullable Bundle args) {
        PasswordConfirmationFragment fragment = new PasswordConfirmationFragment();
        fragment.setArguments(args != null ? args : new Bundle());
        fragment.getArguments().putString(Constants.ARG_MESSAGE, message);
        return fragment;
    }

    public PasswordConfirmationFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            message = getArguments().getString(Constants.ARG_MESSAGE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_password_confirmation, container, false);

        TextView messageView = (TextView) view.findViewById(R.id.message);
        if (message != null) {
            messageView.setText(message);
        } else {
            messageView.setVisibility(View.GONE);
        }

        final EditText password = (EditText) view.findViewById(R.id.password);
        // FIXME causes problems in older Androids
//        Keyboard.focusAndShowKeyboard(password, getActivity());

        view.findViewById(R.id.button_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Keyboard.hideKeyboard(getActivity());
                if (mListener != null) {
                    getArguments().remove(Constants.ARG_MESSAGE);
                    getArguments().putString(Constants.ARG_PASSWORD, password.getText().toString());
                    mListener.onPasswordConfirmed(getArguments());
                }
            }
        });

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (Listener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface Listener {
        public void onPasswordConfirmed(Bundle args);
    }
}