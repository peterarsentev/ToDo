package ru.job4j.todo;

import dagger.Component;

@Component(modules = {StoreModule.class})
public interface Context {
   void injects(Object obj);
}
