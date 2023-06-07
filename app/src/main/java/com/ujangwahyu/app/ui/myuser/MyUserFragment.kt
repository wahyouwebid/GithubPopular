package com.ujangwahyu.app.ui.myuser

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.ujangwahyu.app.R
import com.ujangwahyu.app.base.BaseFragment
import com.ujangwahyu.app.data.entity.UserEntity
import com.ujangwahyu.app.databinding.FragmentMyUserBinding
import com.ujangwahyu.app.ui.adapter.MyUserAdapter
import com.ujangwahyu.app.utils.PopupDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyUserFragment : BaseFragment<FragmentMyUserBinding>(FragmentMyUserBinding::inflate) {

    private val viewModel: MyUserViewModel by viewModels()

    private val navigation: NavController? by lazy {
        activity?.findNavController(R.id.nav_host_main)
    }

    override fun setupView(savedInstanceState: Bundle?) {
        with(binding) {
            fabAdd.setOnClickListener {
                goToDetailWithoutData()
            }
        }
    }

    override fun setupViewModel() {
        with(binding) {
            viewModel.getUser()
            viewModel.users.observe(viewLifecycleOwner) { result ->
                val adapter = MyUserAdapter(
                    showDetail = { item ->
                        goToDetail(item)
                    },
                    showMore = { item ->
                        showMore(item)
                    }
                )
                rvUsers.adapter = adapter
                rvUsers.layoutManager = LinearLayoutManager(
                    context, LinearLayoutManager.VERTICAL, false
                )
                adapter.submitData(lifecycle, result)
                loadState(adapter)
            }
        }
    }

    private fun showMore(item: UserEntity?) {
        PopupDialog(this@MyUserFragment).showPopupConfirmation(
            getString(R.string.title_confirmation),
            getString(R.string.title_confirmation_please_select),
            getString(R.string.title_edit),
            getString(R.string.title_delete),
            actionBtnNegative = {
                goToDetail(item)
            }
        ) {
            viewModel.deleteUser(item!!.id)
        }
    }

    private fun loadState(adapter: MyUserAdapter) {
        with(binding) {
            adapter.addLoadStateListener { loadState ->
                if (loadState.source.refresh is LoadState.NotLoading &&
                    loadState.append.endOfPaginationReached &&
                    adapter.itemCount < 1
                ) {
                    rvUsers.isVisible = false
                    llEmpty.isVisible = true
                } else {
                    rvUsers.isVisible = true
                    llEmpty.isVisible = false
                }
            }
        }
    }

    private fun goToDetail(item: UserEntity?) {
        navigation?.navigate(
            R.id.action_navigation_my_user_to_manageUserFragment,
            bundleOf("data" to item)
        )
    }

    private fun goToDetailWithoutData() {
        navigation?.navigate(
            R.id.action_navigation_my_user_to_manageUserFragment,
        )
    }

}