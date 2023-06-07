package com.ujangwahyu.app.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ujangwahyu.app.R
import com.ujangwahyu.app.databinding.ItemUserFooterBinding
import com.ujangwahyu.app.utils.Utils
import retrofit2.HttpException
import java.io.IOException

class GitUserFooterAdapter(
    private val retry: () -> Unit
) :
    LoadStateAdapter<GitUserFooterAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemUserFooterBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: ViewHolder, loadState: LoadState) {
        with(holder.binding) {
            btnRetry.setOnClickListener {
                retry.invoke()
            }

            if (loadState is LoadState.Error) {
                when(val error = loadState.error) {
                    is HttpException -> {
                        val result = Utils.errorMapper(error)
                        tvMessageError.text = result?.message ?: root.context.getString(R.string.title_error_des)
                    }

                    is IOException -> {
                        tvTitleError.text = root.context.getText(R.string.title_error_no_internet)
                        tvMessageError.text = root.context.getText(R.string.title_error_no_internet_des)
                    }
                }
            }

            progressBar.isVisible = loadState is LoadState.Loading
            btnRetry.isVisible = loadState !is LoadState.Loading
            tvMessageError.isVisible = loadState !is LoadState.Loading
            tvTitleError.isVisible = loadState !is LoadState.Loading
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ViewHolder {
        val binding = ItemUserFooterBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }
}