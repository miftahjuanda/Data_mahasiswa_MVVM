package com.udacoding.mahasiswa_app_miftah.ui.beranda.view

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.udacoding.mahasiswa_app_miftah.R
import com.udacoding.mahasiswa_app_miftah.adapter.AdapterMahasiswa
import com.udacoding.mahasiswa_app_miftah.model.getData.DataItem
import com.udacoding.mahasiswa_app_miftah.model.getData.ResponseGetData
import com.udacoding.mahasiswa_app_miftah.ui.add.view.AddActivity
import com.udacoding.mahasiswa_app_miftah.ui.beranda.viewModel.ViewModelMainActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.delete.*
import kotlinx.android.synthetic.main.no_internet.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: ViewModelMainActivity


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(ViewModelMainActivity::class.java)

        setSupportActionBar(toolbar)

        fab.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }

        viewModel.getData()
        attachObserve()
    }

    private fun attachObserve() {
        viewModel.responseGetData.observe(this, Observer { onSucces(it) })
        viewModel.isError.observe(this, Observer { showError(it) })
    }

    private fun showError(it: Throwable?) {
        Toast.makeText(applicationContext, it?.message, Toast.LENGTH_SHORT).show()
    }

    fun onSucces(responseGetData: ResponseGetData) {

        val adapter = AdapterMahasiswa(responseGetData.data, object : AdapterMahasiswa.ItemClick {
            override fun onClick(item: DataItem?) {
                val intent = Intent(applicationContext, AddActivity::class.java)
                intent.putExtra("data", item)
                startActivity(intent)
            }

            override fun delete(item: DataItem?) {
                showDeleteDialog(item)
            }
        })
        rv_list.adapter = adapter
    }

    private fun isConnect(): Boolean {
        val connect: ConnectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        return connect.activeNetworkInfo != null && connect.activeNetworkInfo.isConnected
    }

    private fun showDeleteDialog(item: DataItem?) {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.delete)

        dialog.btn_hapus.setOnClickListener {
            item?.idMahasiswa?.let {
                viewModel.getDelete(it)
                viewModel.getData()
            }
            viewModel.getData()
            dialog.dismiss()
        }

        dialog.btn_batal.setOnClickListener {
            dialog.dismiss()
        }
        viewModel.getData()
        dialog.show()
    }

    private fun showCustomDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.no_internet)
        dialog.setCancelable(true)

        dialog.btn_close.setOnClickListener {
            finishAffinity()
            finish()
            dialog.dismiss()
        }
        dialog.show()
    }

    override fun onBackPressed() {
        finishAffinity()
        finish()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getData()
    }
}