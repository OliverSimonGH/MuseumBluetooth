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
public class CustomAdapter extends BaseAdapter {

    List<GCelliBeacon> dataSource, originalSource;
    LayoutInflater inflater;

    public CustomAdapter(Context c, List<GCelliBeacon> dataSource) {
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

    public void notInRange(List<GCelliBeacon> beacons) {
        for(GCelliBeacon b: this.dataSource) {
            if(beacons.contains(b)) {
                this.dataSource.remove(b);
            }
        }
        this.notifyDataSetChanged();
    }

    private class ViewHolder {
        TextView textView;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder vh;

        if(view == null) {
            vh = new ViewHolder();
            view = inflater.inflate(R.layout.custom_adapter_row, null);
            vh.textView = (TextView) view.findViewById(R.id.beaconRow);
            view.setTag(vh);
        }else{
            vh = (ViewHolder) view.getTag();
        }
        vh.textView.setText(dataSource.get(position).getProxUuid().getStringFormattedUuid());
        return view;
    }

    public boolean checkBeaconUUIDExists(GCelliBeacon beacon){
        for (int i = 0; i < dataSource.size(); i++) {
            GCelliBeacon b = dataSource.get(i);
            if ((b.getMajorNo() == beacon.getMajorNo()) && (b.getMinorNo() == beacon.getMinorNo())){
                return true;
            }
        }
        return false;
    }

}
