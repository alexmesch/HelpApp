package com.msch.helpapp.presenters

import android.annotation.SuppressLint
import com.msch.domain.interactor.GetCategoryItemsUseCase
import com.msch.domain.model.CategoryItems
import com.msch.helpapp.views.HelpView
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import moxy.InjectViewState
import moxy.MvpPresenter
import javax.inject.Inject

@InjectViewState
class HelpPresenter @Inject constructor(private val useCase: GetCategoryItemsUseCase) :
    MvpPresenter<HelpView>() {
    private var disposables = CompositeDisposable()

    @SuppressLint("CheckResult")
    fun showCategories() {
        getCategoriesSingle().subscribe({
            displayCategories(it)
        }, {
            it.stackTrace
        })
            .let { disposables.add(it) }
    }

    private fun getCategoriesSingle(): Single<List<CategoryItems>> {
        return useCase.execute()
    }

    private fun displayCategories(list: List<CategoryItems>) {
        viewState.displayCategories(list)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }
}