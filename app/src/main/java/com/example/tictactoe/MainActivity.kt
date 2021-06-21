package com.example.tictactoe

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.TableRow
import android.widget.TextView
import androidx.core.view.children
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Activity() {

    private val rowCells : MutableList<TextView> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var cells : ArrayList<TextView> = arrayListOf()
        cells = Utils.getChildViewsByClass(table_layout, TextView::class.java)

        cells.forEach { it.setOnClickListener {
                println("cell with name " + it.id + " was clicked!")
            }
        }
    }


}