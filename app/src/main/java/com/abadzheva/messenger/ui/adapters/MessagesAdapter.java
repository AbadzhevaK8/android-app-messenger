package com.abadzheva.messenger.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.abadzheva.messenger.R;
import com.abadzheva.messenger.data.Message;

import java.util.ArrayList;
import java.util.List;

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.MessageViewHolder> {
    public static final int VIEW_TYPE_MESSAGE_SENT = 1;

    public static final int VIEW_TYPE_MESSAGE_RECEIVED = 2;

    private List<Message> messages = new ArrayList<>();

    private final String currentUserId;

    public MessagesAdapter(String currentUserId) {
        this.currentUserId = currentUserId;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
        notifyDataSetChanged();
    }

    public static class MessageViewHolder extends RecyclerView.ViewHolder {

        private final TextView textViewMessage;

        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewMessage = itemView.findViewById(R.id.textViewMessage);
        }
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layoutResId;
        if (viewType == VIEW_TYPE_MESSAGE_SENT) layoutResId = R.layout.out_message_item;
        else layoutResId = R.layout.in_message_item;
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutResId, parent, false);
        return new MessageViewHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
        Message message = messages.get(position);
        if (message.getSenderId().equals(currentUserId)) {
            return VIEW_TYPE_MESSAGE_SENT;
        } else {
            return VIEW_TYPE_MESSAGE_RECEIVED;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        Message message = messages.get(position);
        holder.textViewMessage.setText(message.getText());
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }
}
