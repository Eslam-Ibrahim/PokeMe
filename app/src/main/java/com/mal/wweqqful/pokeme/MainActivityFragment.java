package com.mal.wweqqful.pokeme;

import android.content.Context;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    // Attributes
    private Post currentPost;
    private FetchPost postFetcher;
    private TextView userName;
    private TextView postTime;
    private TextView postMainTxt;
    private TextView postLikesTxt;
    private TextView postCmtTxt;
    private TextView postShareTxt;
    private ImageView userImg;
    private ImageView postImg;
    private Context rootViewContxt;

    public MainActivityFragment() {
        postFetcher = new FetchPost();
        currentPost = new Post();
    }
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Add this line in order for this fragment to handle menu events.
        setHasOptionsMenu(true);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_fragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        if (item.getItemId() == R.id.action_refresh) {
            postFetcher = new FetchPost();
            postFetcher.execute();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        rootViewContxt = rootView.getContext();
        // Construct post objects
        userName = (TextView) rootView.findViewById(R.id.textViewUserName);
        userImg = (ImageView) rootView.findViewById(R.id.imageViewUser);
        postTime = (TextView) rootView.findViewById(R.id.textViewPostTime);
        postMainTxt = (TextView) rootView.findViewById(R.id.textViewMainPostTxt);
        postLikesTxt = (TextView) rootView.findViewById(R.id.likesTxt);
        postCmtTxt = (TextView) rootView.findViewById(R.id.commentsTxt);
        postShareTxt = (TextView) rootView.findViewById(R.id.shareTxt);
        postImg = (ImageView) rootView.findViewById(R.id.imageViewPostImg);
        // Call Post Async Task to fetch post
        postFetcher.execute();
        return rootView;

    }


    // Post Retrieval AsyncTask Inner Class
    public class FetchPost extends AsyncTask<Void, Void, String> {

        // Constants
        private final String LOG_TAG = FetchPost.class.getSimpleName();
        private final String BASE_URL = "https://dl.dropboxusercontent.com/s/7rvknz9e6tfprun/facebookFeed.json";

        @Override
        protected String doInBackground(Void... voids) {
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            String postJsonString = null;

            try {
                URL url = new URL(BASE_URL);
                Log.v(LOG_TAG, "Built URI " + url.toString());
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                    Toast.makeText(getActivity(), "Connection Error!",
                            Toast.LENGTH_LONG).show();
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }
                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    return null;
                }
                postJsonString = buffer.toString();

                Log.v(LOG_TAG, "post string: " + postJsonString);
            } catch (IOException e) {
                Log.e(LOG_TAG, "Error ", e);
                Toast.makeText(getActivity(), "An Error Has occurred!",
                        Toast.LENGTH_LONG).show();
                return null;
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e(LOG_TAG, "Error closing stream", e);
                        Toast.makeText(getActivity(), "Error in streams!",
                                Toast.LENGTH_LONG).show();
                    }
                }
            }
            return postJsonString;
        }


        @Override
        protected void onPostExecute(String result) {
            if (result != null) {
                // New data is back from the server.  Hooray!
                try{
                    currentPost = new Post(result);
                    updatePostView();
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "Error in parsing json!",
                            Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    private void updatePostView(){
        userName.setText(currentPost.getUserPost().getUserName());
        Picasso.with(rootViewContxt)
                .load(currentPost.getUserPost().getImgUrl())
                .resize(50, 50)
                .into(userImg);
        postTime.setText(currentPost.getPostTimeTxt());
        postMainTxt.setText(currentPost.getPostTxt());
        Picasso.with(rootViewContxt)
                .load(currentPost.getPostImgUrl())
                .resize(1000, 1000)
                .into(postImg);
        postLikesTxt.setText(currentPost.getPostLikesTxt());
        postCmtTxt.setText(currentPost.getPostCommentsTxt());
        postShareTxt.setText(currentPost.getPostShareTxt());
    }
}


