package com.example.santhosh.earthquake.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.santhosh.earthquake.R;
import com.example.santhosh.earthquake.network.model.EarthquakeModel;

import java.util.List;

public class EarthquakeAdapter extends RecyclerView.Adapter<EarthquakeAdapter.EarthViewHolder> {

    private Context mContext;
    private List<EarthquakeModel> mEarthquakeList;
    private LayoutInflater mLayoutInflater;

    public EarthquakeAdapter(Context context, List<EarthquakeModel> quakeList) {
        this.mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        this.mEarthquakeList = quakeList;
    }

    @Override
    public EarthViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.earthquake_list_item, parent, false);
        return new EarthViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EarthViewHolder holder, int position) {

        final EarthquakeModel earthquakeModel = mEarthquakeList.get(position);
        if (earthquakeModel.getMagnitude() >= 8) {
            holder.itemView.setBackgroundColor(Color.RED);
        } else {
            holder.itemView.setBackgroundColor(Color.YELLOW);
        }
        holder.date_time.setText(earthquakeModel.getDatetime());
        holder.depth.setText(String.valueOf(earthquakeModel.getDepth()));
        String magnitudeValue = Double.toString(earthquakeModel.getMagnitude());
        holder.magnitude.setText(magnitudeValue);
    }

    @Override
    public int getItemCount() {
        return mEarthquakeList.size();
    }

    class EarthViewHolder extends RecyclerView.ViewHolder {

        TextView date_time;
        TextView depth;
        TextView magnitude;

        public EarthViewHolder(final View itemView) {
            super(itemView);
            date_time = (TextView) itemView.findViewById(R.id.date_time);
            depth = (TextView) itemView.findViewById(R.id.depth);
            magnitude = (TextView) itemView.findViewById(R.id.magnitude);
        }
    }
}
