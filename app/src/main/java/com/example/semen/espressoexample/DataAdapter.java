package com.example.semen.espressoexample;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private ItemClickListener itemClickListener;
    private LayoutInflater inflater;
    private List<Phone> phones;

    DataAdapter(Context context, List<Phone> phones) {
        this.phones = phones;
        this.inflater = LayoutInflater.from(context);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder holder, int position) {
        holder.bind(phones.get(position));
    }

    @Override
    public int getItemCount() {
        return phones.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final TextView nameView, companyView;
        Phone phone;

        ViewHolder(View view) {
            super(view);
            nameView = view.findViewById(R.id.name);
            companyView = view.findViewById(R.id.company);
            itemView.setTag(itemView);
            itemView.setOnClickListener(this);
        }

        public void bind(Phone phone) {
            nameView.setText(phone.getName());
            companyView.setText(phone.getCompany());
            this.phone = phone;
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                itemClickListener.onClick(phones.get(position).getName());
            }
        }

        public Phone getPhone(){
            return phone;
        }
    }

    public interface ItemClickListener {
        void onClick(String name);
    }
}