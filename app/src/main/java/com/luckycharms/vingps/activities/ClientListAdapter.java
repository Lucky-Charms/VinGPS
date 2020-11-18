package com.luckycharms.vingps.activities;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.datastore.generated.model.Client;
import com.luckycharms.vingps.R;

import java.util.ArrayList;

public class ClientListAdapter extends RecyclerView.Adapter<ClientListAdapter.ClientViewHolder> {
    ArrayList<Client> clients;

    public ClientListAdapter(ArrayList<Client> clients) {
        this.clients = clients;
    }

    @NonNull
    @Override // gets called when fragment is first made

    public ClientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_client_view, parent, false);

        ClientViewHolder clientViewHolder = new ClientViewHolder(view);
        return clientViewHolder;
    }

    @Override

    public void onBindViewHolder(@NonNull ClientViewHolder holder, int position) {

        holder.client = clients.get(position);

        ((TextView) holder.fragment.findViewById(R.id.fname_text)).setText(holder.client.getFirstName());
        ((TextView) holder.fragment.findViewById(R.id.lname_text)).setText(holder.client.getLastName());

    }

    @Override

    public int getItemCount() {
        return clients.size();
    }

    public static class ClientViewHolder extends RecyclerView.ViewHolder {

        Client client;
        View fragment;

        public ClientViewHolder(@NonNull View itemView) {
            super(itemView);
            this.fragment = itemView;

        }
    }
}

