package com.example.user.hrtc;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AutoCompleteAdapter extends ArrayAdapter<String> implements Filterable{

    private static final String TAG = "AutoCompleteAdapter";
    private ArrayList<String> data, info;
    private Context context;

    // default constructor
    public AutoCompleteAdapter(Activity context, String nameFilter)
    {
        super(context, android.R.layout.simple_list_item_1);
        data = new ArrayList<String>();

        setNotifyOnChange(true);
    }
    @Override
    public int getCount()
    {
        return data.size();
    }
    @Override
    public String getItem(int position)
    {
        Log.d("AutoCompleteAdapter", data.get(position));
        return data.get(position);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {

        return super.getView(position, view, parent);
    }

    // To prevent repetition, we have this private method
    private TextView createTextViewAsItem(int position){
        TextView label = new TextView(context);
        String name = "";
        if(data != null && data.size() > 0 && position >= 0 && position < data.size() - 1)
            name = data.get(position);
        label.setText(name);

        return label;
    }


    @Override
    public Filter getFilter()
    {
        Filter myfilter= new Filter()

        {
            @Override
            protected FilterResults performFiltering (CharSequence constraint)
            {
                FilterResults filterResults = new FilterResults();
                json jp=new json();

                if (constraint != null) {
                    if (constraint == null) {
                        filterResults.values = data;
                        filterResults.count = data.size();
                    } else {

                        ArrayList<SuggestGetSet> sugestion=new ArrayList<>();
                        List<SuggestGetSet> new_data = jp.getParseJsonWCF(constraint.toString());
                        data.clear();
                        for (int i = 0; i < new_data.size(); i++) {
                            data.add(new_data.get(i).getName());
                            //  data.toString();
                        }
                        filterResults.values =data;
                        filterResults.count = data.size();
                    }
                }

                return  filterResults;
            }
            @Override
            protected void publishResults (CharSequence constraint, FilterResults results) {
                // clear();
                // addAll((List)filterResults.values);
                // notifyDataSetChanged();

                if (results != null && results.count > 0) {
                    notifyDataSetChanged();
                } else {
                    notifyDataSetInvalidated();
                }
            }
        };


        return myfilter;
    }
}

