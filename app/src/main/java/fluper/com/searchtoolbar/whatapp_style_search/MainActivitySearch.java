package fluper.com.searchtoolbar.whatapp_style_search;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;

import fluper.com.searchtoolbar.R;


public class MainActivitySearch extends AppCompatActivity {

    MaterialSearchView  searchView;
    Toolbar toolbar;

    RecyclerView listshowrcy;
    List<Item> productlists = new ArrayList<>();
    MyAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_whatapp_style);

        toolbar= findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        productlists.add(new Item("Harley Davidson Street 750 2016 Std", R.mipmap.ic_launcher));
        productlists.add(new Item("Triumph Street Scramble 2017 Std", R.mipmap.ic_launcher));
        productlists.add(new Item("Suzuki GSX R1000 2017 STD", R.mipmap.ic_launcher));


        searchviewcode();

        listshowrcy = (RecyclerView) findViewById(R.id.listshow);
        listshowrcy.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        listshowrcy.setLayoutManager(linearLayoutManager);
        adapter = new MyAdapter(productlists, MainActivitySearch.this);
        listshowrcy.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_whatapp_style, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (searchView.isSearchOpen()) {
            searchView.closeSearch();
        } else {
            super.onBackPressed();
        }
    }

    private void searchviewcode() {
        searchView = findViewById(R.id.search_view);


            searchView.setSuggestions(getResources().getStringArray(R.array.query_suggestions));
            searchView.setEllipsize(true);
            searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    return false;
                }
            });
            searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
                @Override
                public void onSearchViewShown() {

                }

                @Override
                public void onSearchViewClosed() {

                }
            });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == MaterialSearchView.REQUEST_VOICE && resultCode == RESULT_OK) {
            ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            if (matches != null && matches.size() > 0) {
                String searchWrd = matches.get(0);
                if (!TextUtils.isEmpty(searchWrd)) {
                    searchView.setQuery(searchWrd, false);
                }
            }
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}