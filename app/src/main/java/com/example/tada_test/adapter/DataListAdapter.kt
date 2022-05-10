package com.example.tada_test.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tada_test.R
import com.example.tada_test.model.ArtObjects
import kotlinx.android.synthetic.main.item_data_list.view.*
import java.util.ArrayList


class DataListAdapter(var listData: ArrayList<ArtObjects>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface OnItemAction {
        fun onGetDetailData(data : ArtObjects)
    }

    var itemActionListener: OnItemAction? = null

    override fun getItemCount(): Int = listData.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return HolderContent(
                        LayoutInflater.from(parent.context).inflate(R.layout.item_data_list, parent, false))

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? BindHolder)?.bind(position)
    }

    inner class HolderContent(itemView: View) : RecyclerView.ViewHolder(itemView), BindHolder {
        private val mTitle = itemView.tv_title
        private val mThumbnail = itemView.iv_thumbnail


        init {
            itemView.setOnClickListener {
                if(adapterPosition != -1) {
                    itemActionListener?.onGetDetailData(listData[adapterPosition])
                }
            }
        }

        override fun bind(pos: Int) {
            val data = listData[pos]
            mTitle.text = data.title
            Glide.with(itemView.context)
                .load(data.webImage?.url)
                .override(100)
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .into(mThumbnail)

        }
    }
}