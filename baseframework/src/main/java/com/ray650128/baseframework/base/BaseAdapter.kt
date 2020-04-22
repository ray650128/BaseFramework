package com.ray650128.baseframework.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter(private val layoutId: Int) : RecyclerView.Adapter<BaseAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = sizeInit()

    inner class ViewHolder(private val mView: View) : RecyclerView.ViewHolder(mView) {
        var obj: Any? = null

        init {
            viewInit(mView, this)
        }

        override fun toString(): String {
            return super.toString() + " ''"
        }
    }

    abstract fun sizeInit(): Int
    abstract override fun onBindViewHolder(holder: ViewHolder, position: Int)
    open fun viewInit(mView: View, holder: ViewHolder) {}

}