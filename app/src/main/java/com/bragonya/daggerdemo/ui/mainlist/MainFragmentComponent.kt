package com.bragonya.daggerdemo.ui.mainlist

import dagger.Subcomponent

@Subcomponent
interface MainFragmentComponent{
    fun inject(mainFragment: MainFragment)
}