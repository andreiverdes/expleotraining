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
import com.andreiverdes.training.expleo.stackoverflow.model.QuestionItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.ViewHolder> {

    private List<QuestionItem> itemList = new ArrayList<>();

    @BindingAdapter("items")
    public static void setItemList(RecyclerView recyclerView,
                                   List<QuestionItem> questionItems) {
        if (questionItems != null) {
            ((QuestionsAdapter) recyclerView.getAdapter()).setItems(questionItems);
        }
    }

    @BindingAdapter("userUri")
    public static void setUserUri(ImageView imageView,
                                  Uri userImageUri) {
        if (userImageUri != null) {
            Picasso.get()
                    .load(userImageUri)
                    .placeholder(R.drawable.ic_ac_unit_black_24dp)
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
        QuestionItem questionItem = itemList.get(position);
        holder.binding.setCeVreau(questionItem);
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void setItems(List<QuestionItem> items) {
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

}
