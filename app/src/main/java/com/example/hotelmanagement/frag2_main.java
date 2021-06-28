package com.example.hotelmanagement;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class frag2_main extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag2_main, container, false);
        TextView textView=(TextView)v.findViewById(R.id.textViewfrag2);
        ImageView imageView=(ImageView)v.findViewById(R.id.imageView2);
        imageView.setImageResource(getArguments().getInt("picture"));
        textView.setText(getArguments().getString("country"));
        return v;
    }

    public static frag2_main newInstance(int pic,String txt){
        frag2_main f=new frag2_main();
        Bundle b=new Bundle();
        b.putInt("picture",pic);
        //b.putString("country",txt);
        f.setArguments(b);
        return f;
    }
}
