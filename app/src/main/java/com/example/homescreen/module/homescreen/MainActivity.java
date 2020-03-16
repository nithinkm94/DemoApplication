package com.example.homescreen.module.homescreen;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.homescreen.R;
import com.example.homescreen.base.BaseActivity;
import com.example.homescreen.databinding.ActivityMainBinding;
import com.example.homescreen.model.Items;

import java.util.List;

public class MainActivity extends BaseActivity {

    MainActivityViewModel viewModel;
    ActivityMainBinding binding;
    private long mLastClickTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = ViewModelProviders.of(MainActivity.this).get(MainActivityViewModel.class);
        binding.setViewModel(viewModel);
        setSupportActionBar(binding.myToolbar);

        viewModel.getItemList().observe(this, new Observer<List<Items>>() {
            @Override
            public void onChanged(List<Items> items) {
                viewModel.updateAdapter(items);
            }
        });


        viewModel.showLoadingDialog.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean)
                    showLoadingDialog(MainActivity.this);
                else
                    hideaLoadingDialog();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_refresh) {
            viewModel.onSyncBtnClicked();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


}
