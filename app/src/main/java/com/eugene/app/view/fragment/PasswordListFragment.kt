package com.eugene.app.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.eugene.app.R
import com.eugene.app.viewmodel.PassListViewModel

class PasswordListFragment : Fragment() {

    private val passListViewModel : PassListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_password_list, container, false)
    }

}