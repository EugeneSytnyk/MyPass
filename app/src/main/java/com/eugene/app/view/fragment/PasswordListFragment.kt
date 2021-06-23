package com.eugene.app.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eugene.app.R
import com.eugene.app.view.adapter.PassListAdapter
import com.eugene.app.viewmodel.PassListViewModel
import io.reactivex.Completable

class PasswordListFragment : Fragment() {

    private val passListViewModel : PassListViewModel by viewModels()
    private lateinit var rvPasswordList : RecyclerView
    private lateinit var passListAdapter : PassListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_password_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        passListAdapter = PassListAdapter(resources.getIntArray(R.array.icon_colors))
        rvPasswordList = view.findViewById(R.id.rvPasswordList)
        rvPasswordList.layoutManager = LinearLayoutManager(context)
        rvPasswordList.adapter = passListAdapter
        setupViewModel()
    }

    private fun setupViewModel(){
        passListViewModel.passwordList.observe(viewLifecycleOwner){
            passListAdapter.dataList = it
        }
        passListViewModel.deletedPassInstance.observe(viewLifecycleOwner){
            Toast.makeText(context, resources.getString(R.string.deleted_message), Toast.LENGTH_SHORT).show()
        }
        passListAdapter.onItemLongClick = {passwordModel ->
            passListViewModel.deletePasswordInstance(passwordModel)
        }
    }

}