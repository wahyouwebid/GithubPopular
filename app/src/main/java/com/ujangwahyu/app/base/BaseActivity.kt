package com.ujangwahyu.app.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<T : ViewBinding>(private val bindingInflater: (layoutInflater: LayoutInflater) -> T) : AppCompatActivity() {

    private lateinit var _binding: T

    protected val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = bindingInflater.invoke(layoutInflater)
        setContentView(binding.root)
        setupData()
        setupNavigation(savedInstanceState)
        setupListener()
    }

    abstract fun setupNavigation(savedInstanceState: Bundle?)

    open fun setupData() {}

    open fun setupListener() {}
}