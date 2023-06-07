package com.ujangwahyu.app.ui.myuser.detail

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.ujangwahyu.app.R
import com.ujangwahyu.app.base.BaseFragment
import com.ujangwahyu.app.data.entity.UserEntity
import com.ujangwahyu.app.databinding.FragmentManageUserBinding
import com.ujangwahyu.app.ui.myuser.MyUserViewModel
import com.ujangwahyu.app.utils.PopupDialog
import com.ujangwahyu.app.utils.hide
import com.ujangwahyu.app.utils.parcelable
import com.ujangwahyu.app.utils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ManageUserFragment : BaseFragment<FragmentManageUserBinding>(FragmentManageUserBinding::inflate) {

    private val viewModel: MyUserViewModel by viewModels()

    private val navigation: NavController? by lazy {
        activity?.findNavController(R.id.nav_host_main)
    }

    private val dataItem: UserEntity? by lazy {
        arguments?.parcelable("data")
    }

    override fun setupView(savedInstanceState: Bundle?) {
        with(binding) {
            if (dataItem != null) {
                etName.setText(dataItem?.name)
                etRole.setText(dataItem?.role)
                etGithub.setText(dataItem?.github)
                btnDelete.show()
            } else {
                btnDelete.hide()
            }
        }
        setupListener()
    }

    private fun setupListener() {
        with(binding) {
            ivBack.setOnClickListener {
                navigation?.navigateUp()
            }
            btnSave.setOnClickListener {
                if (!etName.text.isNullOrEmpty()
                    && !etRole.text.isNullOrEmpty()
                    && !etGithub.text.isNullOrEmpty()
                ) {
                    if (dataItem != null) {
                        updateData()
                    } else {
                        saveData()
                    }
                } else {
                    PopupDialog(this@ManageUserFragment).showPopupMandatory()
                }
            }

            btnDelete.setOnClickListener {
                PopupDialog(this@ManageUserFragment).showPopupConfirmation(
                    getString(R.string.title_confirmation_delete),
                    String.format(
                        getString(R.string.title_confirmation_delete_des),
                        dataItem?.name
                    ),
                    getString(R.string.title_cancel),
                    getString(R.string.title_yes)
                ) {
                    viewModel.deleteUser(dataItem!!.id)
                }
            }
        }
    }

    override fun setupViewModel() {
        viewModel.insert.observe(viewLifecycleOwner) {
            navigation?.navigateUp()
        }

        viewModel.update.observe(viewLifecycleOwner) {
            navigation?.navigateUp()
        }

        viewModel.delete.observe(viewLifecycleOwner) {
            navigation?.navigateUp()
        }
    }

    private fun saveData() {
        with(binding) {
            viewModel.insertUser(
                UserEntity(
                    name = etName.text.toString(),
                    role = etRole.text.toString(),
                    github = etGithub.text.toString(),
                ),
            )
        }
    }

    private fun updateData() {
        with(binding) {
            viewModel.updateUser(
                UserEntity(
                    id = dataItem?.id!!,
                    name = etName.text.toString(),
                    role = etRole.text.toString(),
                    github = etGithub.text.toString(),
                ),
            )
        }
    }

}