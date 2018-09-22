package com.derickfelix.githubrepository;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.derickfelix.githubrepository.models.GithubRepository;

import java.util.List;

public class RepositoryAdapter extends RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder> {

    private List<GithubRepository> githubRepositories;

    public RepositoryAdapter(List<GithubRepository> githubRepositories)
    {
        this.githubRepositories = githubRepositories;
    }

    @NonNull
    @Override
    public RepositoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recyclerview_item, parent, false);

        return new RepositoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RepositoryViewHolder holder, int position)
    {
        GithubRepository githubRepository = githubRepositories.get(position);

        holder.mTitleTextView.setText(githubRepository.getTitle());

        if (githubRepository.getDescription().equals("null")) {
            holder.mDescriptionTextView.setText("");
        } else {
            holder.mDescriptionTextView.setText(githubRepository.getDescription());
        }

        if (githubRepository.getLanguage().equals("null")) {
            holder.mLanguageTextView.setText("-");
        } else {
            holder.mLanguageTextView.setText(githubRepository.getLanguage());
        }

        holder.mStargazersCountTextView.setText(String.valueOf(githubRepository.getStargazers()));
    }

    @Override
    public int getItemCount()
    {
        return githubRepositories.size();
    }

    public void update(List<GithubRepository> githubRepositories)
    {
        this.githubRepositories = githubRepositories;
        notifyDataSetChanged();
    }

    public class RepositoryViewHolder extends RecyclerView.ViewHolder {

        private TextView mTitleTextView;
        private TextView mDescriptionTextView;
        private TextView mLanguageTextView;
        private TextView mStargazersCountTextView;

        public RepositoryViewHolder(@NonNull View itemView)
        {
            super(itemView);

            mTitleTextView = itemView.findViewById(R.id.tv_repository_title);
            mDescriptionTextView = itemView.findViewById(R.id.tv_repository_description);
            mLanguageTextView = itemView.findViewById(R.id.tv_repository_language);
            mStargazersCountTextView = itemView.findViewById(R.id.tv_repository_stargazers_count);
        }

    }
}
