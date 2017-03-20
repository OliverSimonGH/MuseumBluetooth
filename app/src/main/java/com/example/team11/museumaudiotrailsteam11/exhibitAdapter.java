package com.example.team11.museumaudiotrailsteam11;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.List;
import java.util.Map;

/**
 * Created by c1630186 on 20/03/2017.
 */
public class exhibitAdapter extends BaseAdapter implements Filterable {

    List<String> dataSource, originalDataSource;
    LayoutInflater myInflater;
    DBConnector exhibitConnector;

    public exhibitAdapter(Context context){
        exhibitConnector = new DBConnector(context);
        Map<Long, String > exhibits = exhibitConnector.getExhibits(null);

    }


    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public Filter getFilter() {
        return null;
    }
}
