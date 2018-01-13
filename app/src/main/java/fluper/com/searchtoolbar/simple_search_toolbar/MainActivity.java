package fluper.com.searchtoolbar.simple_search_toolbar;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fluper.com.searchtoolbar.R;
import fluper.com.searchtoolbar.simple_search_toolbar.adapter.MainActivityAdapter;
import fluper.com.searchtoolbar.whatapp_style_search.Item;

public class MainActivity extends AppCompatActivity {
    SearchView searchView;
    RecyclerView listshowrcy;
    List<Item> productlists = new ArrayList<>();

    MainActivityAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //adding product name and image in array list of Item type
        productlists.add(new Item("Harley Davidson Street 750 2016 Std", R.mipmap.ic_launcher));
        productlists.add(new Item("Triumph Street Scramble 2017 Std", R.mipmap.ic_launcher));
        productlists.add(new Item("Suzuki GSX R1000 2017 STD", R.mipmap.ic_launcher));


        listshowrcy = (RecyclerView) findViewById(R.id.listshow);
        listshowrcy.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        listshowrcy.setLayoutManager(linearLayoutManager);
        adapter = new MainActivityAdapter(productlists, MainActivity.this);
        listshowrcy.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.searchfile, menu);

        final MenuItem myActionMenuItem = menu.findItem(R.id.search);
        searchView = (SearchView) myActionMenuItem.getActionView();
        changeSearchViewTextColor(searchView);
        ((EditText) searchView.findViewById(
                android.support.v7.appcompat.R.id.search_src_text)).
                setHintTextColor(getResources().getColor(R.color.white));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (!searchView.isIconified()) {
                    searchView.setIconified(true);
                }
                myActionMenuItem.collapseActionView();

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                final List<Item> filtermodelist = filter(productlists, newText);
                adapter.setfilter(filtermodelist);
                return true;
            }
        });

        return true;
    }

    private List<Item> filter(List<Item> pl, String query) {
        query = query.toLowerCase();
        final List<Item> filteredModeList = new ArrayList<>();
        for (Item model : pl) {
            final String text = model.getName().toLowerCase();
            if (text.startsWith(query)) {
                filteredModeList.add(model);
            }
        }
        return filteredModeList;
    }

    //for changing the text color of searchview
    private void changeSearchViewTextColor(View view) {
        if (view != null) {
            if (view instanceof TextView) {
                ((TextView) view).setTextColor(Color.WHITE);
                return;
            } else if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    changeSearchViewTextColor(viewGroup.getChildAt(i));
                }
            }
        }
    }


}