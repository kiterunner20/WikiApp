package com.task.di;

import com.task.App;
import com.task.ui.searchlist.WikiSearchResultFragment;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    void inject(App app);

    void inject(WikiSearchResultFragment wikiSearchResultFragment);


    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(App application);

        AppComponent build();
    }
}
