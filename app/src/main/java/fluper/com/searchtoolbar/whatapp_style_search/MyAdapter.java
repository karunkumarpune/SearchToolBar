package fluper.com.searchtoolbar.whatapp_style_search;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import fluper.com.searchtoolbar.R;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.Holderview>{

    private List<Item> productlist;
    private Context context;

    public MyAdapter(List<Item> productlist, Context context) {
        this.productlist = productlist;
        this.context = context;
    }

    @Override
    public Holderview onCreateViewHolder(ViewGroup parent, int viewType) {
       View layout= LayoutInflater.from(parent.getContext()).
         inflate(R.layout.customitem,parent,false);

        return new Holderview(layout);
    }

    @Override
    public void onBindViewHolder(Holderview holder, final int position) {

        holder.v_name.setText(productlist.get(position).getName());
        holder.v_image.setImageResource(productlist.get(position).getPhoto());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override            public void onClick(View view) {
                Toast.makeText(context, "click on " + productlist.get(position).getName(),
        Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override

    public int getItemCount() {
        return productlist.size();
    }

    public void setfilter(List<Item> listitem)
    {
        productlist=new ArrayList<>();
        productlist.addAll(listitem);
        notifyDataSetChanged();
    }
    class Holderview extends RecyclerView.ViewHolder
    {
        ImageView v_image;
        TextView v_name;

        Holderview(View itemview)
        {
            super(itemview);
            v_image=(ImageView) itemview.findViewById(R.id.product_image);
            v_name = (TextView) itemView.findViewById(R.id.product_title);

        }
    }
}