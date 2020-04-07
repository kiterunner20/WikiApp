package com.task.di;

import androidx.lifecycle.ViewModel;


import com.task.ui.searchlist.WikiSearchResultViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module public abstract class ViewModelModule {

  @Binds @IntoMap @ViewModelKey(WikiSearchResultViewModel.class)
  abstract ViewModel bindSearchResultViewModel(WikiSearchResultViewModel wikiSearchResultViewModel);
}
