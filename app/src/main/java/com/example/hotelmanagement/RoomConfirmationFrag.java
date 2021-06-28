package com.example.hotelmanagement;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class RoomConfirmationFrag extends Fragment {


    String appuser;
    String rn;
    String country;
    private String state[] = {};
    DatabaseHandlerOnline mconnectMySql2 = new DatabaseHandlerOnline("state");
    DatabaseHandlerOnline mCanBook=new DatabaseHandlerOnline("rooms");
    Button Confirmation,Cancel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.roomconfirmationlayout, container, false);
       Confirmation =(Button)v.findViewById(R.id.confirmbook);
        Cancel=(Button)v.findViewById(R.id.cancelbook);
        ImageView imageView=(ImageView)v.findViewById(R.id.imageView3);
        ImageView imageView1=(ImageView)v.findViewById(R.id.description);
        country=getActivity().getIntent().getExtras().getString("location");
        TextView textView1=(TextView)v.findViewById(R.id.textView3);
        imageView.setImageResource(getArguments().getInt("picture"));
        imageView1.setImageResource(getArguments().getInt("description"));
        textView1.setText(getArguments().getString("RoomNumber"));
        rn=getArguments().getString("RoomNumber");
        appuser=getArguments().getString("user");
        Confirmation.setOnClickListener(onclicked);
        Cancel.setOnClickListener(onclicked);
        return v;
    }


    View.OnClickListener onclicked=new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()){
                case R.id.confirmbook:
                    Confirmation.setEnabled(false);
                    mCanBook.setRoomNumber(rn);
                    mCanBook.setUsername(appuser);
                    mCanBook.execute("");
                    mconnectMySql2.execute("");
                    Toast.makeText(getContext(),"Booking...",Toast.LENGTH_SHORT).show();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent=new Intent(getActivity(),rooms.class);
                            intent.putExtra("st",mconnectMySql2.state);
                            System.out.println(mconnectMySql2.state);
                            intent.putExtra("activity","from book cancel");
                            intent.putExtra("location",country);
                            Toast.makeText(getContext(),"The room as been successfully booked,You will be redirected to the room page",Toast.LENGTH_SHORT);
                            startActivityForResult(intent,45);

                        }
                        }, 4000);
                    break;
                case R.id.cancelbook:
                    Cancel.setEnabled(false);
                    Handler handler2 = new Handler();
                    handler2.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                    state=mconnectMySql2.state;
                    Intent intent1=new Intent(getActivity(),rooms.class);
                    intent1.putExtra("st",state);
                    intent1.putExtra("activity","from book cancel");
                    intent1.putExtra("location",country);
                    startActivityForResult(intent1,45);
                        }
                    }, 2000);
                    break;


            }

        }
    };
    public static RoomConfirmationFrag newInstance(int pic,int des,int RoomNumber,String user){
        RoomConfirmationFrag f=new RoomConfirmationFrag();
        Bundle b=new Bundle();
        b.putInt("description",des);
        b.putInt("picture",pic);
        b.putInt("RoomNumber",RoomNumber);
        b.putString("user",user);
        f.setArguments(b);
        return f;
    }


}
