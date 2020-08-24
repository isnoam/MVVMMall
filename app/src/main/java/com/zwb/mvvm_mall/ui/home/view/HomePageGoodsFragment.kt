package com.zwb.mvvm_mall.ui.home.view

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.zwb.mvvm_mall.R
import com.zwb.mvvm_mall.base.view.BaseVMFragment
import com.zwb.mvvm_mall.bean.GoodsEntity
import com.zwb.mvvm_mall.common.utils.dp2px
import com.zwb.mvvm_mall.common.view.GridItemDecoration
import com.zwb.mvvm_mall.common.view.PersistentStaggeredGridLayoutManager
import com.zwb.mvvm_mall.ui.goods.view.GoodsDetailActivity
import com.zwb.mvvm_mall.ui.home.adapter.PagerListAdapter
import com.zwb.mvvm_mall.ui.home.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home_page_list.*

class HomePageGoodsFragment : BaseVMFragment<HomeViewModel>(){
    var mAdapter = PagerListAdapter(R.layout.item_goods_big_layout,null)
    override var layoutId = R.layout.fragment_home_page_list

    override fun initView() {
        super.initView()
        childRecyclerView.layoutManager =  PersistentStaggeredGridLayoutManager(2)
        childRecyclerView.addItemDecoration(GridItemDecoration(activity!!.dp2px(8f)))
        childRecyclerView.adapter = mAdapter

        mAdapter.setOnItemClickListener { adapter, _, position ->
            GoodsDetailActivity.launch(requireActivity(),
                (adapter.getItem(position) as GoodsEntity).goodsName)
        }
    }

    override fun initData() {
        super.initData()
        mViewModel.loadBoutiqueGoodsData0()
    }

    override fun initDataObserver() {
        super.initDataObserver()
        mViewModel.mBoutiqueGoods.observe(this, Observer {
            mAdapter.setNewData(it.toMutableList())
        })
    }

    companion object {
        const val FRAME_TYPE_BOUTIQUE = 0
        const val FRAME_TYPE_NEW = 1
        const val FRAME_TYPE_CHEAP = 2

    }
}