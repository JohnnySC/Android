package github.johnnysc.githubusers.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import github.johnnysc.githubusers.Model.GithubUser;
import github.johnnysc.githubusers.R;

/**
 * Created by Hovhannes Asatryan on 22.07.16.
 */
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.Holder> {

    private final UserClickListener userClickListener;
    private List<GithubUser> users;

    public UserAdapter(UserClickListener userClickListener) {
        this.userClickListener = userClickListener;
    }

    public void initialize(){
        this.users = new ArrayList<>();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent,false);
        return new Holder(row);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        GithubUser currentUser = users.get(position);
        holder.userLogin.setText(currentUser.getLogin());
        Picasso.with(holder.itemView.getContext()).load(currentUser.getAvatar_url()).into(holder.avatarImageView);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public void addUser(GithubUser user){
        users.add(user);
        notifyDataSetChanged();
    }

    public GithubUser getSelectedUser(int position) {
        return users.get(position);
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.avatarImageView) ImageView avatarImageView;
        @BindView(R.id.userNameTxtView) TextView userLogin;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            userClickListener.onClick(getLayoutPosition());
        }
    }

    public interface UserClickListener{
        void onClick(int position);
    }
}
