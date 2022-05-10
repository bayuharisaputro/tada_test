package com.example.tada_test.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tada_test.adapter.DataListAdapter
import com.example.tada_test.databinding.FragmentHomeBinding
import com.example.tada_test.dependencyInjection.component.BaseApp
import com.example.tada_test.dialog.ErrorDialog
import com.example.tada_test.model.ArtObjects
import com.example.tada_test.ui.DetailActivity
import com.example.tada_test.ui.RegisterActivity
import com.example.tada_test.viewmodel.DataListViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

class HomeFragment : Fragment() {

    private lateinit var mAdapter: DataListAdapter
    private var mErrorDialog: ErrorDialog? = null
    @Inject
    lateinit var dataListViewModelFactory: ViewModelProvider.Factory
    private lateinit var dataListVM: DataListViewModel
    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mErrorDialog?.isShowing == true) {
            mErrorDialog?.dismiss()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as BaseApp).appComponent.dataListTaskComponent().create()
            .inject(this)
        val vm by viewModels<DataListViewModel> { dataListViewModelFactory }
        dataListVM = vm

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initData()
        initEvent()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initView() {
        mErrorDialog = ErrorDialog(context?:return)
        mAdapter = DataListAdapter(arrayListOf())
        mAdapter.itemActionListener = object : DataListAdapter.OnItemAction {
            override fun onGetDetailData(data : ArtObjects) {
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra("url", data.webImage?.url)
                intent.putExtra("title", data.longTitle)
                startActivity(intent)
            }

        }
        rv_data.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
            itemAnimator = null
        }
    }

    private fun initData() {
        dataListVM.requestGetDataList(50)
    }

    private fun initEvent() {
        sr_data.setOnRefreshListener {
            dataListVM.requestGetDataList(50)
        }

        dataListVM.ldDataList.observe(viewLifecycleOwner, Observer {
            it?.let {
                if(sr_data.isRefreshing) sr_data.isRefreshing = false

                mAdapter.listData = it
                mAdapter.notifyDataSetChanged()
            }
        })

        dataListVM.ldIsLoading.observe(viewLifecycleOwner, Observer {
            sr_data.isRefreshing = it
        })

        dataListVM.ldErrorHandler.observe(viewLifecycleOwner, Observer {
            it?.let {
                mErrorDialog?.setDialogData("Get Data Error", it.message.toString())
                mErrorDialog?.show()
            }
        })
    }
}