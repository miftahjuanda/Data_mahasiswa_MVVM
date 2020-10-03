package com.udacoding.mahasiswa_app_miftah.ui.add.view

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.udacoding.mahasiswa_app_miftah.R
import com.udacoding.mahasiswa_app_miftah.model.getData.DataItem
import com.udacoding.mahasiswa_app_miftah.ui.add.viewModel.ViewModelAddActivity
import kotlinx.android.synthetic.main.activity_add.*

class AddActivity : AppCompatActivity() {

    lateinit var viewModel: ViewModelAddActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        viewModel = ViewModelProvider(this).get(ViewModelAddActivity::class.java)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        attachObserve()
        initData()
    }

    private fun attachObserve() {

    }

    private fun initData() {
        val getData = intent.getParcelableExtra<DataItem>("data")

        if (getData != null) {
            edt_nama.setText(getData.mahasiswaNama)
            edt_hp.setText(getData.mahasiswaNohp)
            edt_alamat.setText(getData.mahasiswaAlamat)

            btn_save.text = getString(R.string.update)
        }

        when (btn_save.text) {
            "Update" -> {
                btn_save.setOnClickListener {
                    val nama = edt_nama.text.toString()
                    val hp = edt_hp.text.toString()
                    val alamat = edt_alamat.text.toString()

                    getData?.idMahasiswa?.let { it1 ->
                        viewModel?.getUpdate(it1, nama, hp, alamat)
                    }
                    finish()
                }
            }
            else -> {
                btn_save.setOnClickListener {
                    val nama = edt_nama.text.toString()
                    val hp = edt_hp.text.toString()
                    val alamat = edt_alamat.text.toString()

                    if (nama.isEmpty() != hp.isEmpty() != alamat.isEmpty()) {

                        if (nama.isEmpty()) {
                            edt_nama.error = "Lengkapi Isian Nama"
                        }

                        if (hp.isEmpty()) {
                            edt_hp.error = "Lengkapi Isian HP"
                        }

                        if (alamat.isEmpty()) {
                            edt_alamat.error = "Lengkapi Isian Alamat"
                        }

                    } else {
                        viewModel.getInput(nama, hp, alamat)
                        finish()
                    }
                }
            }
        }

        btn_cancel.setOnClickListener {
            finish()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        } else {
            Toast.makeText(applicationContext, item.title, Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }
}