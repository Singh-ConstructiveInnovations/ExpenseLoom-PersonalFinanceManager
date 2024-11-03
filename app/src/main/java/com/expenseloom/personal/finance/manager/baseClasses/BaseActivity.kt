package com.example.social_life.baseClasses

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<T : ViewBinding>(
    private val bindingFactory: (layoutInflater: android.view.LayoutInflater) -> T
) : AppCompatActivity() {

    protected lateinit var binding: T
    protected val mActivity = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = bindingFactory(layoutInflater)  // Use the lambda to create the binding
        enableEdgeToEdge()
        setContentView(binding.root)

        initView()
        initViewListener()

    }

    abstract fun initView()
    open fun initViewListener() {}

}
