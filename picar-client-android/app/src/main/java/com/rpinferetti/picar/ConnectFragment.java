package com.rpinferetti.picar;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ConnectFragment.OnConnectListener} interface
 * to handle interaction events.
 * Use the {@link ConnectFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConnectFragment extends Fragment {

    private static final String TAG = "ConnectFragment";

    private static final String SERVER_IP = "192.168.4.1";
    private static final String SERVER_MACADDR = "b8:27:eb:33:1d:2b";
    private static final int SERVER_PORT = 12345;

    private OnConnectListener mListener;

    public ConnectFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ConnectFragment.
     */
    static ConnectFragment newInstance() {
        return new ConnectFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_connect, container, false);

        // IP ADDRESS and PORT inputs
        final TextInputEditText addressInput = view.findViewById(R.id.ipaddress_input);
        final TextInputEditText portInput = view.findViewById(R.id.port_input);
        addressInput.setText(SERVER_IP);
        portInput.setText(String.valueOf(SERVER_PORT));

        // Connect button
        Button connectButton = view.findViewById(R.id.connect_button);
        connectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String addr = addressInput.getText().toString();
                String port = portInput.getText().toString();
                mListener.onConnect(addr, Integer.parseInt(port));
            }
        });

        // Force portrait orientation
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        return view;
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnConnectListener) {
            mListener = (OnConnectListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnConnectListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnConnectListener {
        void onConnect(String address, int port);
    }

}
