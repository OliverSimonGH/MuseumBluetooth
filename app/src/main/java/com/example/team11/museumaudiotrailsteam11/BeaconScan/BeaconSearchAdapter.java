package com.example.team11.museumaudiotrailsteam11.BeaconScan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.team11.museumaudiotrailsteam11.R;
import com.gcell.ibeacon.gcellbeaconscanlibrary.GCelliBeacon;

import java.util.List;


/**
 * Created by c1633899 on 12/03/2017.
 */
public class BeaconSearchAdapter extends BaseAdapter {

    List<GCelliBeacon> dataSource;
    LayoutInflater inflater;

    public BeaconSearchAdapter(Context c, List<GCelliBeacon> dataSource) {
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
        TextView beaconProximity;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder vh;

        if(view == null) {
            vh = new ViewHolder();
            view = inflater.inflate(R.layout.beacon_search_adapter_row, null);
            vh.beaconUUID = (TextView) view.findViewById(R.id.beaconUUID);
            vh.beaconMajorNo = (TextView) view.findViewById(R.id.beaconMajorNo);
            vh.beaconMinorNo = (TextView) view.findViewById(R.id.beaconMinorNo);
//            vh.beaconProximity = (TextView) view.findViewById(R.id.beaconProximity);
            view.setTag(vh);
        }else{
            vh = (ViewHolder) view.getTag();
        }
        vh.beaconUUID.setText(dataSource.get(position).getProxUuid().getStringFormattedUuid());
        vh.beaconMajorNo.setText("Major: " + dataSource.get(position).getMajorNo());
        vh.beaconMinorNo.setText("Minor: " + dataSource.get(position).getMinorNo());
//        vh.beaconProximity.setText("Proximity: " + dataSource.get(position).getProximity().toString());
        return view;
    }

    public String getBeaconUUID(int i) {
        return dataSource.get(i).getProxUuid().getStringFormattedUuid();
    }

    public String getBeaconMajorNo(int i) {
        return dataSource.get(i).getMajorNo();
    }

    public String getBeaconMinorNo(int i) {
        return dataSource.get(i).getMinorNo();
    }


}
