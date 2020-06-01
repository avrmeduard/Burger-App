package avrm.foodapp.com.foodlogin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private static final String TAG = "HomeActivity";

    // vars
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();



    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mToolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(mToolbar);


        getImages();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    private void getImages(){
        Log.d(TAG, "initImageBitmaps: preparing bitmaps.");

        mImageUrls.add("https://ak4.picdn.net/shutterstock/videos/24923924/thumb/10.jpg");
        mNames.add("Havasu Falls");

        mImageUrls.add("https://ak4.picdn.net/shutterstock/videos/24923924/thumb/10.jpg");
        mNames.add("Trondheim");

        mImageUrls.add("https://ak4.picdn.net/shutterstock/videos/24923924/thumb/10.jpg");
        mNames.add("Portugal");

        mImageUrls.add("https://ak4.picdn.net/shutterstock/videos/24923924/thumb/10.jpg");
        mNames.add("Rocky Mountain National Park");


        mImageUrls.add("https://ak4.picdn.net/shutterstock/videos/24923924/thumb/10.jpg");
        mNames.add("Mahahual");

        mImageUrls.add("https://ak4.picdn.net/shutterstock/videos/24923924/thumb/10.jpg");
        mNames.add("Frozen Lake");


        mImageUrls.add("https://ak4.picdn.net/shutterstock/videos/24923924/thumb/10.jpg");
        mNames.add("White Sands Desert");

        mImageUrls.add("https://ak4.picdn.net/shutterstock/videos/24923924/thumb/10.jpg");
        mNames.add("Austrailia");

        mImageUrls.add("https://ak4.picdn.net/shutterstock/videos/24923924/thumb/10.jpg");
        mNames.add("Washington");

        initRecycleView();
    }

    private void initRecycleView() {
        Log.d(TAG, "initRecyclerView : init recyclerview");

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mNames, mImageUrls);
        recyclerView.setAdapter(adapter);
    }
}
