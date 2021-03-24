package com.pavelwintercompany.rur_190

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.NumberPicker
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.pavelwintercompany.rur_190.database.AppDatabase
import com.pavelwintercompany.rur_190.entity.HourModel
import com.pavelwintercompany.rur_190.utils.DateHelper
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            createAlertDialog()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }


    fun createAlertDialog() {
        val builder = AlertDialog.Builder(this);
        val dialogView = layoutInflater.inflate(R.layout.alert_dialog_layout, null)

        builder.setView(dialogView);

        val btnPositive = dialogView?.findViewById(R.id.dialog_positive_btn) as Button
        val btnNegative = dialogView.findViewById(R.id.dialog_negative_btn) as Button
        val descriptionEt = dialogView.findViewById(R.id.descriptionEt) as EditText

        val pickerFrom = dialogView.findViewById(R.id.minuteFrom) as NumberPicker
        val pickerTo = dialogView.findViewById(R.id.minuteTo) as NumberPicker

        pickerFrom.minValue = 0
        pickerFrom.maxValue = 60

        pickerTo.minValue = 0
        pickerTo.maxValue = 60

        val dialog = builder.create()

        btnPositive.setOnClickListener {
            Toast.makeText(this, "C: ${pickerFrom.value} до ${pickerTo.value}", Toast.LENGTH_SHORT)
                .show()

            addNewNote(
                pickerFrom.value,
                pickerTo.value - pickerFrom.value,
                descriptionEt.text.toString()
            )

            dialog.dismiss()
        }

        btnNegative.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show();

    }

    private fun addNewNote(taskStartTime: Int, taskDuration: Int, decription: String) {

        val startTime = DateHelper.millisecFromDatetime(14) + (taskDuration * 60_000)

        GlobalScope.launch {
            getDb().hourModelDao().insertAll(
                HourModel(
                    Random.nextInt(), decription,
                    startTime, taskDuration, ""
                )
            )
        }

    }

    fun getDb(): AppDatabase = Room.databaseBuilder(
        applicationContext,
        AppDatabase::class.java, "database-name"
    ).build()
}

