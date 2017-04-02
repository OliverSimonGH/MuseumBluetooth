package com.example.team11.museumaudiotrailsteam11;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by c1630186 on 29/03/2017.
 */
public class MapFragment extends Fragment{

    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;


    public static MapFragment newInstance(){
            MapFragment fragment = new MapFragment();
            return fragment;
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.map_fragment_layout, container, false);

            btn1 = (Button)view.findViewById(R.id.button);
            btn2 = (Button)view.findViewById(R.id.button2);
            btn3 = (Button)view.findViewById(R.id.button3);
            btn4 = (Button)view.findViewById(R.id.button4);

            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity().getApplicationContext(), R.string.ExhibitA, Toast.LENGTH_SHORT).show();
                }

            });

            btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity().getApplicationContext(), R.string.ExhibitB, Toast.LENGTH_SHORT).show();
                }

            });

            btn3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity().getApplicationContext(), R.string.ExhibitC, Toast.LENGTH_SHORT).show();
                }

            });

            btn4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity().getApplicationContext(), R.string.ExhibitD, Toast.LENGTH_SHORT).show();
                }

            });

            return view;
        }

}
