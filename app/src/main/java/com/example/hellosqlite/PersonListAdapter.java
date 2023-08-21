package com.example.hellosqlite;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PersonListAdapter extends RecyclerView.Adapter<PersonListAdapter.PersonViewHolder> {

    private PersonDataHolder dataHolder;

    private OnClickListener onClickListener;

    public PersonListAdapter(PersonDataHolder dataHolder, OnClickListener onClickListener) {
        this.dataHolder = dataHolder;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.person_item, parent, false);
        return new PersonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder holder, int position) {
        holder.bindPerson(dataHolder.getPerson(position));
    }

    @Override
    public int getItemCount() {
        return dataHolder.size();
    }

    public class PersonViewHolder extends RecyclerView.ViewHolder {

        private TextView tvName;
        private TextView tvFamily;
        private TextView tvAge;

        public PersonViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tv_person_item_name);
            tvFamily = itemView.findViewById(R.id.tv_person_item_family);
            tvAge = itemView.findViewById(R.id.tv_person_item_age);

            itemView.setOnLongClickListener(v -> {
                int idx = getAbsoluteAdapterPosition();
                onClickListener.onRemove(idx);
                notifyItemRemoved(idx);

                String toastText = String.format("%s is removed", tvName.getText());
                Toast.makeText(itemView.getContext(), toastText, Toast.LENGTH_SHORT).show();
                return false;
            });
        }

        public void bindPerson(Person p) {
            tvName.setText(p.getName());
            tvFamily.setText(p.getFamily());
            tvAge.setText(String.valueOf(p.getAge()));
        }
    }

    public interface OnClickListener {
        public void onRemove(int idx);
    }

}
