package com.udacoding.mahasiswa_app_miftah.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.udacoding.mahasiswa_app_miftah.R
import com.udacoding.mahasiswa_app_miftah.model.getData.DataItem
import kotlinx.android.synthetic.main.list_item.view.*

class AdapterMahasiswa (val data : List<DataItem>?, val itemClick : ItemClick) : RecyclerView.Adapter<AdapterMahasiswa.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data?.get(position)

        holder.nama.text = item?.mahasiswaNama
        holder.noHp.text = item?.mahasiswaNohp
        holder.alamat.text = item?.mahasiswaAlamat

        holder.view.setOnClickListener {
            itemClick.onClick(item)
        }
        holder.btnDelete.setOnClickListener {
            itemClick.delete(item)
        }

    }

    override fun getItemCount(): Int = data?.size ?: 0

    class ViewHolder (val view : View) : RecyclerView.ViewHolder(view) {

        val nama = view.tv_nama
        val noHp = view.tv_noHp
        val alamat = view.tv_alamat
        val btnDelete = view.btn_delete

    }

    interface ItemClick {
        fun onClick(item: DataItem?)
        fun delete(item: DataItem?)
    }
}