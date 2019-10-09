package ru.job4j.todo;

import dagger.Module;
import dagger.Provides;

@Module
public class StoreModule {

    @Provides
    public Store provideStore() {
        return MemStore.getStore();
    }
}
