

package com.practice.sweekritee.practise;

        import android.app.LauncherActivity;
        import android.content.Context;
        import android.support.v7.widget.ContentFrameLayout;
        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.TextView;

        import com.squareup.picasso.Picasso;

        import java.util.List;

        import static com.practice.sweekritee.practise.R.id.image;
        import static com.practice.sweekritee.practise.R.id.rout_image;

/**
 * Created by yamuna on 8/8/2018.
 */

public class Adapter_routine extends RecyclerView.Adapter <Adapter_routine.ViewHolder> {
    List<RoutineItem> routineItems;
    private Context context;

    public Adapter_routine(List<RoutineItem> routineItems, Context context) {
        this.routineItems = routineItems;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.routine_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RoutineItem routineItem= routineItems.get(position);
        holder.name.setText(routineItem.getName());
        //ImageView imgage_path=(ImageView) itemView.findViewById(R.id.rout_image);
        Picasso.with(context).load(routineItem.getImage()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return routineItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView name;
        public ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            name=(TextView) itemView.findViewById(R.id.rout_heading);
            imageView=(ImageView) itemView.findViewById(R.id.rout_image);
        }
    }
}

