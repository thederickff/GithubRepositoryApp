package com.derickfelix.githubrepository.utils;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtil {

    private static final String GITHUB_BASE_URL = "https://api.github.com/search/repositories";

    private static final String PARAM_QUERY = "q";
    private static final String PARAM_SORT = "s";
    private static final String sortBy = "stars";

    public static URL buildUrl(String query)
    {
        Uri uri = Uri.parse(GITHUB_BASE_URL)
                .buildUpon()
                .appendQueryParameter(PARAM_QUERY, query)
                .appendQueryParameter(PARAM_SORT, sortBy)
                .build();

        try {
            return new URL(uri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException
    {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        try (InputStream in = connection.getInputStream()) {
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            if (scanner.hasNext()) {
                return scanner.next();
            }
        } finally {
            connection.disconnect();
        }

        return null;
    }
}
