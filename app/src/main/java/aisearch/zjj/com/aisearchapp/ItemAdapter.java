package aisearch.zjj.com.aisearchapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import aisearch.zjj.com.aisearchapp.pojo.Item;

/**
 * Created by Administrator on 2020/2/6.
 */

public class ItemAdapter extends ArrayAdapter<Item> {
    private int resourceId;

    public ItemAdapter(@NonNull Context context, int resource, @NonNull List<Item> objects) {
        super(context, resource, objects);
        resourceId = resource;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Item item = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        TextView textView = view.findViewById(R.id.textViewId);
        TextView textView1 = view.findViewById(R.id.textViewTitle);
        TextView textView2 = view.findViewById(R.id.textViewContent);
        textView.setText(item.getId());
        textView1.setText(item.getTitle());
        textView2.setText(item.getContent());
        return  view;
    }
}
