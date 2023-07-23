package com.devdroiddev.chatapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.devdroiddev.chatapp.ChatActivity;
import com.devdroiddev.chatapp.R;
import com.devdroiddev.chatapp.models.UserModel;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter  extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {

    private Context context;
    private List<UserModel> userModelList;


    public UserAdapter(Context context) {
        this.context = context;

        // TODO: Initialize UserModelList Object Here
        userModelList = new ArrayList<>();
    }

    // TODO: Method to Add the Data in userModelList
    public void add(UserModel userModel) {
        if (userModel != null) {
            userModelList.add(userModel);
            notifyDataSetChanged();
        }
    }

    // TODO: Method to Clear the Data From the List
    public void clear() {
        userModelList.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_row, parent, false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        UserModel model = userModelList.get(position);
        if (model != null) {
            if (model.getUserName() != null) {
                holder.name.setText(model.getUserName());
            }
            if (model.getUserEmail() != null) {
                holder.email.setText(model.getUserEmail());
            }
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ChatActivity.class);
                intent.putExtra("id",model.getUserId());
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return userModelList.size();
    }

    // TODO: Make Nested ViewHolder Class
    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView name, email;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.txtuserName);
            email = itemView.findViewById(R.id.txtuserEmail);
        }
    }

}
