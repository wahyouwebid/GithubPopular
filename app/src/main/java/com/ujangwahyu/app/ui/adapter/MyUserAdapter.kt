package com.ujangwahyu.app.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ujangwahyu.app.data.entity.UserEntity
import com.ujangwahyu.app.databinding.ItemMyUserBinding
import com.ujangwahyu.app.utils.splitString

class MyUserAdapter(
    val showDetail: (UserEntity?) -> Unit,
    val showMore: (UserEntity?) -> Unit
) :
    PagingDataAdapter<UserEntity, MyUserAdapter.ViewHolder>(DiffCallback) {

    class ViewHolder(val binding: ItemMyUserBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.binding) {
            tvName.text = getItem(position)?.name
            tvRole.text = getItem(position)?.role
            tvGithub.text = getItem(position)?.github
            tvThumbnail.text = getItem(position)?.name?.splitString()

            root.setOnClickListener {
                showDetail(getItem(position))
            }

            ivMore.setOnClickListener {
                showMore(getItem(position))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMyUserBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    object DiffCallback : DiffUtil.ItemCallback<UserEntity>() {
        override fun areItemsTheSame(oldItem: UserEntity, newItem: UserEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UserEntity, newItem: UserEntity): Boolean {
            return oldItem == newItem
        }
    }
}