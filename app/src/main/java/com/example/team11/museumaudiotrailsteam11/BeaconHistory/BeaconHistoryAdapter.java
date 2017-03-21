package com.example.team11.museumaudiotrailsteam11.BeaconHistory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.team11.museumaudiotrailsteam11.R;

import java.util.List;

/**
 * Created by c1633899 on 19/03/2017.
 */
public class BeaconHistoryAdapter extends BaseAdapter {
    List<BluetoothBeacon> dataSource;
    LayoutInflater inflater;

    public BeaconHistoryAdapter(Context c, List<BluetoothBeacon> dataSource) {
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

    public void remove(String beaconUUID, String beaconMajorNo, String beaconMinorNo) {
        for (int i = 0; i < dataSource.size(); i++) {
            BluetoothBeacon b = dataSource.get(i);
            if ((b.getBeaconUUID() == beaconUUID) && (b.getMajorNo() == beaconMajorNo) && (b.getMinorNo() == beaconMinorNo)){
                this.dataSource.remove(b);
            }
        }
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
            view = inflater.inflate(R.layout.beacon_history_row, null);
            vh.beaconUUID = (TextView) view.findViewById(R.id.beaconRow);
            vh.beaconMajorNo = (TextView) view.findViewById(R.id.beaconMajorNo);
            vh.beaconMinorNo = (TextView) view.findViewById(R.id.beaconMinorNo);
            view.setTag(vh);
        }else{
            vh = (ViewHolder) view.getTag();
        }
        vh.beaconUUID.setText(dataSource.get(position).getBeaconUUID());
        vh.beaconMajorNo.setText("Major No: " + dataSource.get(position).getMajorNo());
        vh.beaconMinorNo.setText("Minor No: " + dataSource.get(position).getMinorNo());
        return view;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BeaconHistoryAdapter that = (BeaconHistoryAdapter) o;

        return dataSource.equals(that.dataSource);
    }

    public String getBeaconUUID(int i) {
        return dataSource.get(i).getBeaconUUID();
    }

    public String getBeaconMajorNo(int i) {
        return dataSource.get(i).getMajorNo();
    }

    public String getBeaconMinorNo(int i) {
        return dataSource.get(i).getMinorNo();
    }
}

