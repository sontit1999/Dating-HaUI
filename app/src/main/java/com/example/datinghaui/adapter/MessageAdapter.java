package com.example.datinghaui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.datinghaui.R;
import com.example.datinghaui.model.Message;
import com.example.datinghaui.model.User;
import com.example.datinghaui.utils.Constant;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<Message> list = new ArrayList<>();
    private User userTalk = null;
    public static final int TypeSent = 888;
    public static final int TypeRecieve = 999;
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if(viewType == TypeSent){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mess_send,parent,false);
            return new Sendhoder(view);
        }else{
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mess_recieve,parent,false);
            return new Recievehoder(view);
        }
    }

    public void setUserTalk(User userTalk) {
        this.userTalk = userTalk;
    }

    public List<Message> getList() {
        return list;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
         Message message = list.get(position);
         if(message.getSender().equals(Constant.userCurent.getPhoneNumber())){
              Sendhoder sendhoder = (Sendhoder) holder;
              sendhoder.binData(message);
         }else{
             Recievehoder recievehoder = (Recievehoder) holder;
             recievehoder.binData(message);
         }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(list.get(position).getSender().equals(Constant.userCurent.getPhoneNumber())){
            return TypeSent;
        }else{
            return TypeRecieve;
        }
    }

    public void setList(List<Message> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public class Sendhoder extends RecyclerView.ViewHolder{
        TextView txtContent;
        public Sendhoder(@NonNull View itemView) {
            super(itemView);
            txtContent = (TextView) itemView.findViewById(R.id.tv_message_send);
        }
        public void binData(Message messages){
            txtContent.setText(messages.getContent());
        }
    }
    public class Recievehoder extends RecyclerView.ViewHolder{
        TextView txtContent;
        public Recievehoder(@NonNull View itemView) {
            super(itemView);
            txtContent = (TextView) itemView.findViewById(R.id.tv_message_recieve);
        }
        public void binData(Message message){
            txtContent.setText(message.getContent());
        }
    }
}
