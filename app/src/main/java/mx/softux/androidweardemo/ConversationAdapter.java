package mx.softux.androidweardemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by juan on 7/7/15.
 */
public class ConversationAdapter extends BaseAdapter {
    private List<ConversationMessage> messages;
    private Context context;

    public ConversationAdapter(Context context) {
        this.context = context;
        messages = new LinkedList<ConversationMessage>();
    }

    @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public Object getItem(int position) {
        return messages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return messages.get(position).id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder viewHolder;

        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.adapter_conversation_message_layout, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.messageText.setText(messages.get(position).text);

        return view;
    }

    public void addMessages(List<ConversationMessage> messages) {
        messages.addAll(messages);
        notifyDataSetChanged();
    }

    public void removeMessages() {
        messages = new LinkedList<ConversationMessage>();
        notifyDataSetChanged();
    }

    private class ViewHolder {
        public TextView messageText;

        public ViewHolder(View view) {
            messageText = (TextView) view.findViewById(R.id.message_text);
        }
    }
}
