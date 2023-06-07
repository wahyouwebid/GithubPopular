package com.ujangwahyu.app.ui.gituser

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.jakewharton.rxbinding3.widget.textChangeEvents
import com.ujangwahyu.app.R
import com.ujangwahyu.app.base.BaseFragment
import com.ujangwahyu.app.databinding.FragmentGitUserBinding
import com.ujangwahyu.app.ui.adapter.GitUserAdapter
import com.ujangwahyu.app.ui.adapter.GitUserFooterAdapter
import com.ujangwahyu.app.utils.Utils
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class GitUserFragment : BaseFragment<FragmentGitUserBinding>(FragmentGitUserBinding::inflate){

    private val viewModel: GitUserViewModel by viewModels()

    private val adapter : GitUserAdapter by lazy {
        GitUserAdapter()
    }

    override fun setupView(savedInstanceState: Bundle?) {
        setupSearch()
        setupAdapter()
    }


    override fun setupViewModel() {
        viewModel.users.observe(viewLifecycleOwner) { result ->
            adapter.submitData(lifecycle, result)
        }
    }

    private fun setupSearch() {
        with(binding) {
            etSearch.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null)
            etSearch.textChangeEvents()
                .debounce(500, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { textView ->
                    val keyword = textView.text.toString()
                    if (keyword.trim { it <= ' ' }.isNotEmpty()) {
                        viewModel.searchUsers(keyword)
                    } else {
                        adapter.submitData(lifecycle, PagingData.empty())
                    }
                }
        }
    }

    private fun setupAdapter() {
        with(binding) {
            rvUsers.adapter = adapter.withLoadStateFooter(GitUserFooterAdapter { adapter.retry() })
            rvUsers.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            rvUsers.setHasFixedSize(true)
            adapter.addLoadStateListener { loadState(it) }
        }
    }

    private fun loadState(loadState: CombinedLoadStates) {
        when(loadState.source.refresh) {
            is LoadState.Loading -> onLoading()
            is LoadState.Error -> onError(loadState)
            else -> onCheckEmpty(loadState)
        }
    }

    private fun onCheckEmpty(loadState: CombinedLoadStates) {
        with(binding) {
            if (loadState.source.refresh is LoadState.NotLoading &&
                loadState.append.endOfPaginationReached &&
                adapter.itemCount < 1
            ) {
                shUsers.isVisible = false
                rvUsers.isVisible = false
                llError.isVisible = false
                llEmpty.isVisible = true
            } else {
                shUsers.isVisible = false
                rvUsers.isVisible = true
                llError.isVisible = false
                llEmpty.isVisible = false
            }
        }
    }

    private fun onLoading() {
        with(binding) {
            shUsers.isVisible = true
            rvUsers.isVisible = false
            llError.isVisible = false
            llEmpty.isVisible = false
        }
    }

    private fun onError(loadState: CombinedLoadStates) {
        with(binding) {
            when(val error = (loadState.source.refresh as LoadState.Error).error) {
                is HttpException -> {
                    val result = Utils.errorMapper(error)
                    tvTitleError.text = getString(R.string.title_error)
                    tvMessageError.text = result?.message ?: getString(R.string.title_error_des)
                }

                is IOException -> {
                    tvTitleError.text = getText(R.string.title_error_no_internet)
                    tvMessageError.text = getText(R.string.title_error_no_internet_des)
                }
            }

            shUsers.isVisible = false
            rvUsers.isVisible = false
            llError.isVisible = true
            llEmpty.isVisible = false

            btnRetry.setOnClickListener {
                adapter.retry()
            }
        }
    }

}