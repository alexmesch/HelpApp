package com.msch.helpapp.presenters

import android.util.Log
import androidx.fragment.app.Fragment
import com.msch.domain.interactor.GetCategoryItemsUseCase
import com.msch.domain.model.CategoryItems
import com.msch.helpapp.fragments.Router
import com.msch.helpapp.views.HelpView
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class HelpPresenter @Inject constructor(
    private val categoryUseCase: GetCategoryItemsUseCase,
    private val router: Router
) : MvpPresenter<HelpView>() {
    private var disposables = CompositeDisposable()

    fun showCategories() {
        getCategoriesSingle().subscribe({
            displayCategories(it)
        }, {
            Log.e("hpObserver", "subscription fail!")
            it.printStackTrace()
        })
            .let { disposables.add(it) }
    }

    fun showFragment(fragment: Fragment) {
        router.openFragment(fragment)
    }

    private fun getCategoriesSingle(): Single<List<CategoryItems>> {
        return categoryUseCase.execute()
    }

    private fun displayCategories(list: List<CategoryItems>) {
        viewState.displayCategories(list)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }
}