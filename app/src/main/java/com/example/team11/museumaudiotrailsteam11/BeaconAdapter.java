package com.example.team11.museumaudiotrailsteam11;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gcell.ibeacon.gcellbeaconscanlibrary.GCelliBeacon;

import java.util.List;


/**
 * Created by c1633899 on 12/03/2017.
 */
public class BeaconAdapter extends BaseAdapter {

    List<GCelliBeacon> dataSource, originalSource;
    LayoutInflater inflater;

    public BeaconAdapter(Context c, List<GCelliBeacon> dataSource) {
        this.dataSource = dataSource;
        inflater = LayoutInflater.from(c);
    }

    @Override
    public int getCount() {
        return dataSource.size();
    }

    @Override
    public Object getItem(int i) {
        return dataSource.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public void remove(int i) {
        this.dataSource.remove(i);
        notifyDataSetChanged();
    }


    private class ViewHolder {
        TextView beaconUUID;
        TextView beaconMajorNo;
        TextView beaconMinorNo;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder vh;

        if(view == null) {
            vh = new ViewHolder();
            view = inflater.inflate(R.layout.custom_adapter_row, null);
            vh.beaconUUID = (TextView) view.findViewById(R.id.beaconRow);
            vh.beaconMajorNo = (TextView) view.findViewById(R.id.beaconMajorNo);
            vh.beaconMinorNo = (TextView) view.findViewById(R.id.beaconMinorNo);
            view.setTag(vh);
        }else{
            vh = (ViewHolder) view.getTag();
        }
        vh.beaconUUID.setText(dataSource.get(position).getProxUuid().getStringFormattedUuid());
        vh.beaconMajorNo.setText("Major No: " + dataSource.get(position).getMajorNo());
        vh.beaconMinorNo.setText("Minor No: " + dataSource.get(position).getMinorNo());
        return view;
    }
}
