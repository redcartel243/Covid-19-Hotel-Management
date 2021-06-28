package com.example.hotelmanagement;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


class CustomImageList extends ArrayAdapter {
    private String[] Objects;
    private String[] state;
    private Integer[] imageid;
    private Activity context;

    public CustomImageList(Activity context, String[] RoomName,String []State, Integer[] imageid) {
        super(context, R.layout.row_item, RoomName);
        this.context = context;
        this.Objects = RoomName;
        this.state=State;
        this.imageid = imageid;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row=convertView;
        LayoutInflater inflater = context.getLayoutInflater();
        if(convertView==null)
            row = inflater.inflate(R.layout.row_item, null, true);
        TextView textViewRoomName = (TextView) row.findViewById(R.id.Roomname);
        TextView textViewDescription = (TextView) row.findViewById(R.id.textViewDescription);
        TextView textViewState=(TextView)row.findViewById(R.id.textViewstate) ;
        ImageView imageFlag = (ImageView) row.findViewById(R.id.imageViewroom);

        textViewRoomName.setText(Objects[position]);
        textViewState.setText(state[position]);
        imageFlag.setImageResource(imageid[position]);
        return  row;
    }
}