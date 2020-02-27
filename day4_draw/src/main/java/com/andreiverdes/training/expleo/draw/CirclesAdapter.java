package com.andreiverdes.training.expleo.draw;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.andreiverdes.training.expleo.draw.view.DefaultCenterView;
import com.andreiverdes.training.expleo.draw.view.LowerLeftCornerView;
import com.andreiverdes.training.expleo.draw.view.LowerRightCornerView;
import com.andreiverdes.training.expleo.draw.view.UpperLeftCornerView;
import com.andreiverdes.training.expleo.draw.view.UpperRightCornerView;

public class CirclesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private final int rows;
    private final int columns;

    public CirclesAdapter(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case R.layout.item_upper_left:
                UpperLeftCornerView view = ((UpperLeftCornerView) layoutInflater.inflate(R.layout.item_upper_left, parent, false));
                return new UpperLeftViewHolder(view);
            case R.layout.item_upper_right:
                UpperRightCornerView view1 = ((UpperRightCornerView) layoutInflater.inflate(R.layout.item_upper_right, parent, false));
                return new UpperRightViewHolder(view1);
            case R.layout.item_lower_left:
                LowerLeftCornerView view2 = ((LowerLeftCornerView) layoutInflater.inflate(R.layout.item_lower_left, parent, false));
                return new LowerLeftCornerViewHolder(view2);
            case R.layout.item_lower_right:
                LowerRightCornerView view3 = ((LowerRightCornerView) layoutInflater.inflate(R.layout.item_lower_right, parent, false));
                return new LowerRightViewHolder(view3);
        }
        DefaultCenterView view = ((DefaultCenterView) layoutInflater.inflate(R.layout.item_center, parent, false));
        return new CenterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {

        }
    }

    @Override
    public int getItemCount() {
        return rows * columns;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return R.layout.item_upper_left;
        }
        if (position == columns - 1) {
            return R.layout.item_upper_right;
        }
        if (position == (rows -1) * columns) {
            return R.layout.item_lower_left;
        }
        if (position == rows * columns - 1) {
            return R.layout.item_lower_right;
        }
        return R.layout.item_center;
    }

    public static class UpperLeftViewHolder extends RecyclerView.ViewHolder {
        public UpperLeftViewHolder(@NonNull UpperLeftCornerView itemView) {
            super(itemView);
        }
    }

    public static class UpperRightViewHolder extends RecyclerView.ViewHolder {
        public UpperRightViewHolder(@NonNull UpperRightCornerView itemView) {
            super(itemView);
        }
    }

    public static class LowerRightViewHolder extends RecyclerView.ViewHolder {
        public LowerRightViewHolder(@NonNull LowerRightCornerView itemView) {
            super(itemView);
        }
    }

    public static class LowerLeftCornerViewHolder extends RecyclerView.ViewHolder {
        public LowerLeftCornerViewHolder(@NonNull LowerLeftCornerView itemView) {
            super(itemView);
        }
    }

    public static class CenterViewHolder extends RecyclerView.ViewHolder {
        public CenterViewHolder(@NonNull DefaultCenterView itemView) {
            super(itemView);
        }
    }
}
