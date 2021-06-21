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
    private var playerTurn : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var cells : ArrayList<TextView> = arrayListOf()
        cells = Utils.getChildViewsByClass(table_layout, TextView::class.java)

        cells.forEach { it.setOnClickListener {
                println("cell with name " + it.id + " was clicked!")
                if ((it as TextView).text != "") {
                    println("cell with name " + it.id + " already has a value!")
                    return@setOnClickListener
                }

                if (playerTurn == 0) {
                    (it as TextView).text = "X"
                    playerTurn = 1
                } else if (playerTurn == 1) {
                    (it as TextView).text = "O"
                    playerTurn = 0
                }
            }
        }
    }
}