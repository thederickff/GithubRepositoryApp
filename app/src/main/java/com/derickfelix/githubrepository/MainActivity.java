package com.derickfelix.githubrepository;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.derickfelix.githubrepository.utils.NetworkUtil;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private EditText mSearchEditText;
    private TextView mQueryResultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSearchEditText = findViewById(R.id.et_search);
        mQueryResultTextView = findViewById(R.id.tv_query_result);
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

            mQueryResultTextView.setText(s);
            Toast.makeText(MainActivity.this, "Response fetched successfully!", Toast.LENGTH_SHORT).show();
        }

    }
}
