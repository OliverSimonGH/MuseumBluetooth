package com.example.team11.museumaudiotrailsteam11;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by c1630186 on 20/03/2017.
 */
public class exhibitAdapter extends BaseAdapter implements Filterable {

    List<String> dataSource, originalDataSource;
    LayoutInflater myInflater;
    DBConnector exhibitConnector;

    public exhibitAdapter(Context context){ // Constructor for the exhibitAdapter
        exhibitConnector = new DBConnector(context);   // Declaring our DBConstructor and setting it in the context of the exhibit table
        Map<Long, String > exhibits = exhibitConnector.getExhibits(null);
        this.dataSource = new ArrayList<>();
        this.dataSource.addAll(exhibits.values());
        myInflater = LayoutInflater.from(context);
    }

    public void addExhibit(String exhibit){
        Long i = this.exhibitConnector.addExhibit(exhibit);
        this.dataSource.add(exhibit);
        notifyDataSetChanged();
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    public void remove(int i){
        exhibitConnector.removeExhibit(String.valueOf(i));
        this.dataSource.remove(i);
        notifyDataSetChanged();
    }


    @Override
    public Filter getFilter() {
        Filter exhibitFilter = new Filter(){
            @Override
            protected FilterResults performFiltering(CharSequence filterTerm){
                FilterResults filterExhibits = new FilterResults();
                List<String> filteredExhibits = new ArrayList<>();

                if(originalDataSource == null) {
                    originalDataSource = new ArrayList<>(dataSource);
                }

                if(filterTerm == null || filterTerm.length() == 0) {
                    filterExhibits.count = originalDataSource.size();
                    filterExhibits.values = originalDataSource;
                }else{

                }
                return filterExhibits;

            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                dataSource = (List<String>) filterResults.values;
                notifyDataSetChanged();
            }


        };
        return exhibitFilter;

    }
}
