package com.derickfelix.githubrepository;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.derickfelix.githubrepository.factories.JsonFactory;
import com.derickfelix.githubrepository.models.GithubRepository;
import com.derickfelix.githubrepository.utils.NetworkUtil;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText mSearchEditText;
    private RecyclerView mQueryResultRecyclerView;

    private List<GithubRepository> mGithubRepositories;
    private RepositoryAdapter mRepositoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGithubRepositories = new ArrayList<>();
        mRepositoryAdapter = new RepositoryAdapter(mGithubRepositories);

        mSearchEditText = findViewById(R.id.et_search);
        mQueryResultRecyclerView = findViewById(R.id.rv_query_result);

        mQueryResultRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mQueryResultRecyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));
        mQueryResultRecyclerView.setHasFixedSize(true);
        mQueryResultRecyclerView.setAdapter(mRepositoryAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId()) {
            case R.id.mi_search:
                makeQuery(mSearchEditText.getText().toString());
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void makeQuery(String query)
    {
        URL url = NetworkUtil.buildUrl(query);

        new GithubQueryTask().execute(url);
    }


    private class GithubQueryTask extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... urls)
        {
            if (urls == null) {
                return null;
            }

            URL url = urls[0];

            try {
                return NetworkUtil.getResponseFromHttpUrl(url);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s)
        {
            super.onPostExecute(s);

            mGithubRepositories = JsonFactory.githubRepositories(s);
            mRepositoryAdapter.update(mGithubRepositories);

            Toast.makeText(MainActivity.this, "Response fetched successfully!", Toast.LENGTH_SHORT).show();
        }

    }

}
