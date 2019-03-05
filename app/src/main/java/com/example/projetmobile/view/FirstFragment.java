package com.example.projetmobile.view;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.projetmobile.R;

import org.jetbrains.annotations.Nullable;

public class FirstFragment extends Fragment {
    String typestr = null;
    String naturestr;
    String envstr;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle = this.getArguments();
        String[] infos = bundle.getStringArray("infos");
        typestr = infos[0];
        return inflater.inflate(R.layout.first_fragment, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);
        TextView type = view.findViewById(R.id.type_ikeamon);
        TextView nature = view.findViewById(R.id.nature_ikeamon);
        TextView env = view.findViewById(R.id.env_ikeamon);
        type.setText(typestr);
        nature.setText("nature test");
        env.setText("env test");
    }
}
