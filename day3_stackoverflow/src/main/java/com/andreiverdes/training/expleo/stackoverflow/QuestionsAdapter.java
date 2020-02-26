package com.andreiverdes.training.expleo.stackoverflow;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.andreiverdes.training.expleo.arch.R;
import com.andreiverdes.training.expleo.arch.databinding.ItemQuestionBinding;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.ViewHolder> {

    private List<Item> itemList = new ArrayList<>();

    @BindingAdapter("items")
    public static void setItemList(RecyclerView recyclerView,
                                   List<Item> items) {
        if (items != null) {
            ((QuestionsAdapter) recyclerView.getAdapter()).setItems(items);
        }
    }

    @BindingAdapter("userUri")
    public static void setUserUri(ImageView imageView,
                                  Uri userImageUri) {
        if (userImageUri != null) {
            Glide.with(imageView)
                    .load(userImageUri)
                    .into(imageView);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemQuestionBinding binding = DataBindingUtil.inflate(
                layoutInflater, R.layout.item_question, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item item = itemList.get(position);
        holder.binding.setItem(item);
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void setItems(List<Item> items) {
        this.itemList.clear();
        this.itemList.addAll(items);
        this.notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ItemQuestionBinding binding;

        public ViewHolder(ItemQuestionBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public static class Item {
        public Uri photoUri;
        public String questionTitle;
        public String questionDate;
    }
}
