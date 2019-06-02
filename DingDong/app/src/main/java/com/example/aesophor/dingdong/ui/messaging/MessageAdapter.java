package com.example.aesophor.dingdong.ui.messaging;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aesophor.dingdong.R;
import com.example.aesophor.dingdong.message.Message;
import com.example.aesophor.dingdong.user.User;
import com.example.aesophor.dingdong.util.ImageUtils;

import java.util.ArrayList;
import java.util.List;

public class MessageAdapter extends BaseAdapter {

    List<Message> messages = new ArrayList<>();
    Context context;

    public MessageAdapter(Context context) {
        this.context = context;
    }

    public void add(Message message) {
        messages.add(message);
        notifyDataSetChanged();
    }

    public void clear() {
        messages.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public Object getItem(int i) {
        return messages.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    // This is the backbone of the class, it handles the creation of single ListView row (chat bubble)
    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        MessageViewHolder holder = new MessageViewHolder();
        LayoutInflater messageInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        Message message = messages.get(i);

        if (message.isBelongsToCurrentUser()) { // this message was sent by us so let's create a basic chat bubble on the right
            convertView = messageInflater.inflate(R.layout.msg_bubble_self, null);
            holder.messageBody = convertView.findViewById(R.id.message_body);
            convertView.setTag(holder);
            holder.messageBody.setText(message.getContent());
        } else { // this message was sent by someone else so let's create an advanced chat bubble on the left
            convertView = messageInflater.inflate(R.layout.msg_bubble_others, null);
            holder.avatar = convertView.findViewById(R.id.avatar);
            holder.name = convertView.findViewById(R.id.name);
            holder.messageBody = convertView.findViewById(R.id.message_body);
            convertView.setTag(holder);

            holder.name.setText(message.getSender().getUsername());
            holder.messageBody.setText(message.getContent());

            User sender = message.getSender();
            if (!sender.getB64Avatar().isEmpty()) {
                ImageUtils.b64LoadImage(holder.avatar, sender.getB64Avatar());
            } else {
                GradientDrawable drawable = (GradientDrawable) holder.avatar.getBackground();
                drawable.setColor(Color.parseColor("#a2c9b4"));
            }
        }

        return convertView;
    }

    private class MessageViewHolder {
        public ImageView avatar;
        public TextView name;
        public TextView messageBody;
    }

}