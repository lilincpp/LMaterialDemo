package lilincpp.lmaterialdemo.recycleviewHead;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import lilincpp.lmaterialdemo.R;

/**
 * Created by Colin on 2017/3/23.
 */

public class MyAdaper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    MyRecyclerview myRecyclerview;
    List<Item> data;


    public MyAdaper(List<Item> data) {
        this.data = data;
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        myRecyclerview = (MyRecyclerview) recyclerView;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;

        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_content, parent, false);
        return new ItemViewHolder(view);

    }

    private static final String TAG = "MyAdaper";

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        itemViewHolder.tv.setText(data.get(position).content);
        itemViewHolder.tv.setBackgroundColor(Color.parseColor(data.get(position).color));
    }


    @Override
    public int getItemCount() {
        return data.size();
    }


    static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView tv;

        public ItemViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv);
        }
    }

}
