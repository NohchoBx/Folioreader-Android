package com.folioreader.ui.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.folioreader.R;
import com.folioreader.model.Dosh;
import com.folioreader.model.dictionary.DictionaryResults;

import java.util.List;

public class DoshamAdapter extends RecyclerView.Adapter<DoshamAdapter.DoshamVH> {

    private static final String TAG = "MovieAdapter";
    List<Dosh> doshList;
    private Context context;


    public DoshamAdapter(Context context, List<Dosh> movieList) {
        this.doshList = movieList;
        this.context = context;

    }

    @NonNull
    @Override
    public DoshamVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dosh_list_item, parent, false);
        return new DoshamVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoshamVH holder, int position) {

        Dosh dosh = doshList.get(position);
        holder.titleTextView.setText(dosh.getWord1());
        holder.translateTextView.setText(Html.fromHtml(dosh.getTranslate1()));

        boolean isExpanded = doshList.get(position).isExpanded();
        holder.expandableLayout.setVisibility(isExpanded ? View.VISIBLE : View.GONE);

    }

    public void setResults(List<Dosh> resultsList) {
        if (resultsList != null && !resultsList.isEmpty()) {
            doshList.addAll(resultsList);
            notifyDataSetChanged();
        }
    }

    public void clear() {
        doshList.clear();
        notifyItemRangeRemoved(0, doshList.size());
    }


    @Override
    public int getItemCount() {
        return doshList.size();
    }

    class DoshamVH extends RecyclerView.ViewHolder {

        private static final String TAG = "MovieVH";

        ConstraintLayout expandableLayout;
        TextView titleTextView, translateTextView;

        public DoshamVH(@NonNull final View itemView) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.titleTextView);
            translateTextView = itemView.findViewById(R.id.translateTextView);
            expandableLayout = itemView.findViewById(R.id.expandableLayout);


            titleTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Dosh movie = doshList.get(getAdapterPosition());
                    movie.setExpanded(!movie.isExpanded());
                    notifyItemChanged(getAdapterPosition());

                }
            });
        }
    }
}
