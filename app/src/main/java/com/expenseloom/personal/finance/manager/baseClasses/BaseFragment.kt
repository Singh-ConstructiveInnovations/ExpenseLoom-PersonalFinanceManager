package com.example.social_life.baseClasses

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<T : ViewBinding>(
    private val bindingFactory: (LayoutInflater, ViewGroup?, Boolean) -> T
) : Fragment() {

    private var _binding: T? = null
    protected val binding get() = _binding!!

    protected lateinit var mActivity: Activity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding =
            bindingFactory(inflater, container, false)  // Inflate the binding using the lambda

        mActivity = requireActivity()
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initViewListener()
    }


    abstract fun initView()
    open fun initViewListener() {}


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null  // Avoid memory leaks by setting binding to null
        onDestroy()
    }

    open fun clearMemoryOnDestroy() {}

}
