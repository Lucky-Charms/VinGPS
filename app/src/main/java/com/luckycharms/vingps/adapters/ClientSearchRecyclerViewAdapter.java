package com.luckycharms.vingps.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.datastore.generated.model.Car;
import com.amplifyframework.datastore.generated.model.Client;
import com.luckycharms.vingps.R;

import java.util.ArrayList;

public class ClientSearchRecyclerViewAdapter extends RecyclerView.Adapter<ClientSearchRecyclerViewAdapter.ClientViewHolder> {

    public ArrayList<Client> clients;
    public ClientFragmentOnClickListener listener;

    public ClientSearchRecyclerViewAdapter(ArrayList<Client> clients, ClientFragmentOnClickListener listener) {
        this.clients = clients;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ClientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_client_search, parent, false);

        ClientViewHolder clientViewHolder = new ClientViewHolder(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.ClientFragmentListener(clientViewHolder.client);
            }
        });

        return clientViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ClientViewHolder holder, int position) {
        holder.client = clients.get(position);

        ((TextView) holder.fragment.findViewById(R.id.clientFragmentFirstName)).setText(holder.client.getFirstName());
        ((TextView) holder.fragment.findViewById(R.id.clientFragmentLastName)).setText(holder.client.getLastName());
    }

    @Override
    public int getItemCount() {
        return clients.size();
    }

    public static class ClientViewHolder extends RecyclerView.ViewHolder {
        public Client client;
        public View fragment;

        public ClientViewHolder(@NonNull View itemView) {
            super(itemView);
            this.fragment = itemView;
        }
    }

    public static interface ClientFragmentOnClickListener {
        public void ClientFragmentListener(Client client);
    }
}
