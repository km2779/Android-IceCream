package com.example.icecreamapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_order_history.*
import java.util.*

class OrderHistoryActivity : AppCompatActivity() {
    var orders: ArrayList<OrderItem>? = null
    var string_orders: ArrayList<String>? = null
    //var listview: ListView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_history)
        title = "Order History"
        supportActionBar!!.setHomeButtonEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val i = intent
        orders = i.getSerializableExtra("KEY") as ArrayList<OrderItem>
        Log.d("DEBUG", orders.toString())
        string_orders = ArrayList()
        for (item in orders!!) string_orders!!.add(item.toString())
        //listview = findViewById(R.id.listview)
        val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                string_orders!!
        )
        listview.adapter = adapter
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean { // Handle action bar item clicks here. The action bar will
// automatically handle clicks on the Home/Up button, so long
// as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId
        if (id == android.R.id.home) {
            Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show()
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}