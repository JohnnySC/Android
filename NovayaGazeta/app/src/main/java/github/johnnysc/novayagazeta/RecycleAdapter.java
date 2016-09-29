package github.johnnysc.novayagazeta;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Hovhannes Asatryan on 27.09.16.
 */
public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.Holder>{
    private List<Article> articlesList;
    private final ArticleClickListener articleClickListener;

    public RecycleAdapter(List<Article> articlesList, ArticleClickListener articleClickListener){
        this.articlesList = articlesList;
        this.articleClickListener = articleClickListener;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.article_item,parent,false);
        return new Holder(row);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Article current = articlesList.get(position);
        Picasso.with(holder.itemView.getContext())
                .load(current.getImgLink())
                .error(R.drawable.ic_error)
                .placeholder(R.drawable.progress_animation)
                .into(holder.image);
        Log.d("myLog","Picasso is working now!!!!");
        holder.heading.setText(current.getHeading());
        holder.paragraph.setText(current.getParagraph());
        holder.author.setText(current.getAuthor());
        holder.viewsCount.setText(current.getViews());
    }

    @Override
    public int getItemCount() {
        return articlesList.size();
    }

    public Article getSelectedArticle(int position){
        return articlesList.get(position);
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.imageView) ImageView image;
        @BindView(R.id.headingTextView)TextView heading;
        @BindView(R.id.paragraphTextView)TextView paragraph;
        @BindView(R.id.authorTextView)TextView author;
        @BindView(R.id.viewsCountTextView)TextView viewsCount;

        public Holder(View item) {
            super(item);
            ButterKnife.bind(this,item);
            item.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            articleClickListener.onClick(getLayoutPosition());
        }
    }

    public interface ArticleClickListener{
        void onClick(int position);
    }
}
