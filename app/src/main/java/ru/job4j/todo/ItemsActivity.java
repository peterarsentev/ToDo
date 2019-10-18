package ru.job4j.todo;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

public class ItemsActivity extends BaseActivity implements ItemsFrg.Callbacks {
    @Override
    public Fragment loadFrg() {
        return new ItemsFrg();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_master;
    }

    @Override
    public void onItemSelected(int index) {
        if (findViewById(R.id.detail) == null) {
            Intent intent = new Intent(this, ViewItemActivity.class);
            intent.putExtra("index", index);
            startActivity(intent);
        } else {
            FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction()
                    .replace(R.id.detail, ViewItemFrg.instOf(index))
                    .commit();
        }
    }
}
