package com.example.icecreamapp

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import kotlinx.android.synthetic.main.content_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    var SpinSize: String? = null
    var Flavor: String? = null
    var totalSale = 0.0
    var peanut = 0.0
    var size = 0.0
    var strawberry = 0.0
    var mm = 0.0
    var almond = 0.0
    var price = 0.0
    var brownie = 0.0
    var oreo = 0.0
    var gummy = 0.0
    var marshmellow = 0.0
    var Size: String? = null
    var orders: ArrayList<OrderItem>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        orders = ArrayList()
        OrderButton.setOnClickListener(View.OnClickListener {
            Toast.makeText(this@MainActivity, "Order Placed", Toast.LENGTH_SHORT).show()
            val currentTime = Calendar.getInstance().time
            orders!!.add(OrderItem("Date: $currentTime", "\nFlavor: $Flavor", "\nSize: $Size", "\nCost: $totalSale"))
        })
        TheWorksButton.setOnClickListener(View.OnClickListener {
            Toast.makeText(this@MainActivity, "The Works", Toast.LENGTH_SHORT).show()
            PeanutCheckBox.setChecked(true)
            AlmondCheckBox.setChecked(true)
            StrawberryCheckBox.setChecked(true)
            GummyBearCheckBox.setChecked(true)
            MMCheckBox.setChecked(true)
            BrownieCheckBox.setChecked(true)
            OreoCheckBox.setChecked(true)
            MarshmellowCheckBox.setChecked(true)
            calculate()
        })
        ResetButton.setOnClickListener(View.OnClickListener {
            Toast.makeText(this@MainActivity, "Reset", Toast.LENGTH_SHORT).show()
            PeanutCheckBox.setChecked(false)
            AlmondCheckBox.setChecked(false)
            StrawberryCheckBox.setChecked(false)
            GummyBearCheckBox.setChecked(false)
            MMCheckBox.setChecked(false)
            BrownieCheckBox.setChecked(false)
            OreoCheckBox.setChecked(false)
            MarshmellowCheckBox.setChecked(false)
            SeekBar.setProgress(0)
            SizeSpinner!!.setSelection(0)
            IceCreamSpinner!!.setSelection(0)
            calculate()
        })
        SeekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                SeekBarTextView.setText(Integer.toString(progress) + " oz")
                calculate()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
        //IceCreamSpinner = findViewById(R.id.IceCreamSpinner)
        IceCreamSpinner.setOnItemSelectedListener(object : OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View, position: Int, id: Long) { //Toast.makeText(MainActivity.this, "Selected Item " + position, Toast.LENGTH_SHORT).show();
                if (position == 0) {
                    Flavor = "Vanilla"
                }
                if (position == 1) {
                    Flavor = "Chocolate"
                } else if (position == 2) {
                    Flavor = "Strawberry"
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        })
        //SizeSpinner = findViewById(R.id.SizeSpinner)
        SizeSpinner.setOnItemSelectedListener(object : OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View, position: Int, id: Long) { //Toast.makeText(MainActivity.this, "Selected Item " + position, Toast.LENGTH_SHORT).show();
                if (position == 0) {
                    size = 2.99
                    Size = "Small"
                }
                if (position == 1) {
                    size = 3.99
                    Size = "Medium"
                } else if (position == 2) {
                    size = 4.99
                    Size = "Large"
                }
                calculate()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        })
        PeanutCheckBox.setOnClickListener(View.OnClickListener {
            Toast.makeText(this@MainActivity, "Peanut Checked", Toast.LENGTH_SHORT).show()
            if (PeanutCheckBox.isChecked()) {
                peanut = 0.15
                calculate()
            } else {
                peanut = 0.00
                calculate()
            }
        })
        AlmondCheckBox.setOnClickListener(View.OnClickListener {
            if (AlmondCheckBox.isChecked()) {
                almond = 0.15
                calculate()
            } else {
                almond = 0.00
                calculate()
            }
        })
        StrawberryCheckBox.setOnClickListener(View.OnClickListener {
            if (StrawberryCheckBox.isChecked()) {
                strawberry = 0.20
                calculate()
            } else {
                strawberry = 0.00
                calculate()
            }
        })
        GummyBearCheckBox.setOnClickListener(View.OnClickListener {
            if (GummyBearCheckBox.isChecked()) {
                gummy = 0.20
                calculate()
            } else {
                gummy = 0.00
                calculate()
            }
        })
        MMCheckBox.setOnClickListener(View.OnClickListener {
            if (MMCheckBox.isChecked()) {
                mm = 0.25
                calculate()
            } else {
                mm = 0.00
                calculate()
            }
        })
        BrownieCheckBox.setOnClickListener(View.OnClickListener {
            if (BrownieCheckBox.isChecked()) {
                brownie = 0.20
                calculate()
            } else {
                brownie = 0.00
                calculate()
            }
        })
        OreoCheckBox.setOnClickListener(View.OnClickListener {
            if (OreoCheckBox.isChecked()) {
                oreo = 0.20
                calculate()
            } else {
                oreo = 0.00
                calculate()
            }
        })
        MarshmellowCheckBox.setOnClickListener(View.OnClickListener {
            if (MarshmellowCheckBox.isChecked()) {
                marshmellow = 0.15
                calculate()
            } else {
                marshmellow = 0.00
                calculate()
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean { // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean { // Handle action bar item clicks here. The action bar will
// automatically handle clicks on the Home/Up button, so long
// as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId
        if (id == R.id.action_order_history) {
            Toast.makeText(this, "History", Toast.LENGTH_SHORT).show()
            val i = Intent(this@MainActivity, OrderHistoryActivity::class.java)
            i.putExtra("KEY", orders)
            startActivity(i)
            return true
        }
        if (id == R.id.action_about) {
            Toast.makeText(this, "About", Toast.LENGTH_SHORT).show()
            val i = Intent(this@MainActivity, AboutActivity::class.java)
            i.putExtra("KEY", "Marius was here. May the 4th be with you!!")
            startActivity(i)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    fun calculate() {
        totalSale = 0.0
        if (PeanutCheckBox!!.isChecked) {
            totalSale = totalSale + .15
        }
        if (AlmondCheckBox!!.isChecked) {
            totalSale = totalSale + .15
        }
        if (StrawberryCheckBox!!.isChecked) {
            totalSale = totalSale + .20
        }
        if (GummyBearCheckBox!!.isChecked) {
            totalSale = totalSale + .20
        }
        if (MMCheckBox!!.isChecked) {
            totalSale = totalSale + .25
        }
        if (BrownieCheckBox!!.isChecked) {
            totalSale = totalSale + .20
        }
        if (OreoCheckBox!!.isChecked) {
            totalSale = totalSale + .20
        }
        if (MarshmellowCheckBox!!.isChecked) {
            totalSale = totalSale + .15
        }
        if (SeekBar!!.progress == 0) {
            totalSale = totalSale + 0
        }
        if (SeekBar!!.progress == 1) {
            totalSale = totalSale + .15
        }
        if (SeekBar!!.progress == 2) {
            totalSale = totalSale + .25
        }
        if (SeekBar!!.progress == 3) {
            totalSale = totalSale + .30
        }
        if (SizeSpinner!!.selectedItemPosition == 0) {
            totalSale = totalSale + 2.99
            SpinSize = "Small"
        }
        if (SizeSpinner!!.selectedItemPosition == 1) {
            totalSale = totalSale + 3.99
            SpinSize = "Medium"
        }
        if (SizeSpinner!!.selectedItemPosition == 2) {
            totalSale = totalSale + 4.99
            SpinSize = "Large"
        }
        if (SizeSpinner!!.selectedItemPosition == 0) {
            totalSale = totalSale + 0
            Flavor = "Vanilla"
        }
        if (SizeSpinner!!.selectedItemPosition == 1) {
            totalSale = totalSale + 0
            Flavor = "Strawberry"
        }
        if (SizeSpinner!!.selectedItemPosition == 2) {
            totalSale = totalSale + 0
            Flavor = "Chocolate"
        }
        TotalTextView!!.text = totalSale.toString()
    }
}