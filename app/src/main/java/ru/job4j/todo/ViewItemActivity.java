package ru.job4j.todo;

import android.support.v4.app.Fragment;

public class ViewItemActivity extends BaseActivity {
    @Override
    public Fragment loadFrg() {
        return new ViewItemFrg();
    }
}
