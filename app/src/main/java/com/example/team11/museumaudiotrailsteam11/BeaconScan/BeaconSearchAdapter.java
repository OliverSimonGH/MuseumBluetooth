package com.example.team11.museumaudiotrailsteam11.BeaconScan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.team11.museumaudiotrailsteam11.BeaconHistory.BluetoothBeacon;
import com.example.team11.museumaudiotrailsteam11.R;
import com.gcell.ibeacon.gcellbeaconscanlibrary.GCelliBeacon;

import org.w3c.dom.Text;

import java.util.List;


/**
 * Created by c1633899 on 12/03/2017.
 */
public class BeaconSearchAdapter extends BaseAdapter {

    List<BluetoothBeacon> dataSource;
    LayoutInflater inflater;

    public BeaconSearchAdapter(Context c, List<BluetoothBeacon> dataSource) {
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
        TextView beaconName;
        TextView beaconURL;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder vh;

        if(view == null) {
            vh = new ViewHolder();
            view = inflater.inflate(R.layout.beacon_search_adapter_row, null);
//            vh.beaconUUID = (TextView) view.findViewById(R.id.beaconUUID);
            vh.beaconMajorNo = (TextView) view.findViewById(R.id.beaconMajorNo);
            vh.beaconMinorNo = (TextView) view.findViewById(R.id.beaconMinorNo);
            vh.beaconName = (TextView) view.findViewById(R.id.beaconName);
            vh.beaconURL = (TextView) view.findViewById(R.id.beaconURL);
//            vh.beaconProximity = (TextView) view.findViewById(R.id.beaconProximity);
            view.setTag(vh);
        }else{
            vh = (ViewHolder) view.getTag();
        }
//        vh.beaconUUID.setText(dataSource.get(position).getBeaconUUID());
        vh.beaconMajorNo.setText("Major: " + dataSource.get(position).getMajorNo());
        vh.beaconMinorNo.setText("Minor: " + dataSource.get(position).getMinorNo());
        vh.beaconName.setText("Exhibit: " + dataSource.get(position).getName());
        vh.beaconURL.setText("URL: " + dataSource.get(position).getUrl());

        return view;
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

    public String getBeaconName(int i) {
        return dataSource.get(i).getName();
    }

    public String getBeaconURL(int i) {
        return dataSource.get(i).getUrl();
    }




}
