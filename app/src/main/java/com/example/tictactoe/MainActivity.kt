package com.example.tictactoe

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.TableRow
import android.widget.TextView
import androidx.core.view.children
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Activity() {

    private var cells : ArrayList<TextView> = arrayListOf()
    private var playerTurn : Int = 0 // 0 -> X, 1 -> O
    private var xCounter : Int = 0
    private var oCounter : Int = 0
    private var xWins : Boolean = false
    private var oWins : Boolean = false
    private var draw : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cells = Utils.getChildViewsByClass(tl_cell_table, TextView::class.java)

        if (savedInstanceState != null) {
            var cellValues : ArrayList<Int> = arrayListOf()
            cellValues = savedInstanceState.getIntegerArrayList("cell_values") as ArrayList<Int>

            // 0 -> "", 1 -> "X", 2 -> "O"
            cells.forEachIndexed { index, it ->
                when (cellValues[index]) {
                    0 -> it.text = ""
                    1 -> it.text = "X"
                    2 -> it.text = "O"
                }
            }

            playerTurn = savedInstanceState.getInt("player_turn")
            xCounter = savedInstanceState.getInt("x_counter")
            oCounter = savedInstanceState.getInt("o_counter")
            xWins = savedInstanceState.getBoolean("x_wins")
            oWins = savedInstanceState.getBoolean("o_wins")
            draw = savedInstanceState.getBoolean("draw")
        }

        setCurrentPlayer()

        cells.forEach { it.setOnClickListener {
            println("cell with name " + it.id + " was clicked!")
            if ((it as TextView).text != "" || xWins || oWins || draw) {
                println("no action taken")
                return@setOnClickListener
            }

            if (playerTurn == 0) {
                (it as TextView).text = "X"
                playerTurn = 1
            } else if (playerTurn == 1) {
                (it as TextView).text = "O"
                playerTurn = 0
            }

            if (checkIfGameOver()) {
                setGameOverMessage()
                showPlayAgainButton(true)
                updateWinCounter()
            } else {
                setCurrentPlayer()
            }
        }
        }

        btn_play_again.setOnClickListener {
            // reset variables, clear text in cells
            cells.forEach { it.text="" }
            tv_game_over.text = ""
            playerTurn = 0
            xWins = false
            oWins = false
            draw = false
            setCurrentPlayer()
            setGameOverMessage()
            showPlayAgainButton(false)
        }
    }

    override fun onBackPressed() {
        moveTaskToBack(true)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        val cellValues : ArrayList<Int> = arrayListOf()

        // 0 -> "", 1 -> "X", 2 -> "O"
        cells.forEach {
            when (it.text) {
                "" -> cellValues.add(0)
                "X" -> cellValues.add(1)
                "O" -> cellValues.add(2)
            }
        }

        outState.putIntegerArrayList("cell_values", cellValues)
        outState.putInt("player_turn", playerTurn)
        outState.putInt("x_counter", xCounter)
        outState.putInt("o_counter", oCounter)
        outState.putBoolean("x_wins", xWins)
        outState.putBoolean("o_wins", oWins)
        outState.putBoolean("draw", draw)
    }

    private fun showPlayAgainButton(show : Boolean) {
        if (show)
            btn_play_again.visibility = View.VISIBLE
        else
            btn_play_again.visibility = View.INVISIBLE
    }

    private fun setCurrentPlayer() {
        if (playerTurn == 0)
            tv_current_player.text = "Player X, make a move"
        else if (playerTurn == 1)
            tv_current_player.text = "Player O, make a move"
    }

    private fun checkIfGameOver() : Boolean {
        var endStateReached : Boolean = false
        val cellValues : ArrayList<String> = arrayListOf()

        cells.forEach {
            cellValues.add(it.text.toString())
        }

        // win combinations for player X
        val xRow1 = (cellValues[0] == "X" && cellValues[1] == "X" && cellValues[2] == "X")
        val xRow2 = (cellValues[3] == "X" && cellValues[4] == "X" && cellValues[5] == "X")
        val xRow3 = (cellValues[6] == "X" && cellValues[7] == "X" && cellValues[8] == "X")
        val xCol1 = (cellValues[0] == "X" && cellValues[3] == "X" && cellValues[6] == "X")
        val xCol2 = (cellValues[1] == "X" && cellValues[4] == "X" && cellValues[7] == "X")
        val xCol3 = (cellValues[2] == "X" && cellValues[5] == "X" && cellValues[8] == "X")
        val xDiag1 = (cellValues[0] == "X" && cellValues[4] == "X" && cellValues[8] == "X") // left to right diagonal
        val xDiag2 = (cellValues[2] == "X" && cellValues[4] == "X" && cellValues[6] == "X") // right to left diagonal

        // win combinations for player O
        val oRow1 = (cellValues[0] == "O" && cellValues[1] == "O" && cellValues[2] == "O")
        val oRow2 = (cellValues[3] == "O" && cellValues[4] == "O" && cellValues[5] == "O")
        val oRow3 = (cellValues[6] == "O" && cellValues[7] == "O" && cellValues[8] == "O")
        val oCol1 = (cellValues[0] == "O" && cellValues[3] == "O" && cellValues[6] == "O")
        val oCol2 = (cellValues[1] == "O" && cellValues[4] == "O" && cellValues[7] == "O")
        val oCol3 = (cellValues[2] == "O" && cellValues[5] == "O" && cellValues[8] == "O")
        val oDiag1 = (cellValues[0] == "O" && cellValues[4] == "O" && cellValues[8] == "O") // left to right diagonal
        val oDiag2 = (cellValues[2] == "O" && cellValues[4] == "O" && cellValues[6] == "O") // right to left diagonal

        // check if player X won
        if (xRow1 || xRow2 || xRow3 || xCol1 || xCol2 || xCol3 || xDiag1 || xDiag2) {
            xWins = true
            endStateReached = true
        }

        // check if player O won
        else if (oRow1 || oRow2 || oRow3 || oCol1 || oCol2 || oCol3 || oDiag1 || oDiag2) {
            oWins = true
            endStateReached = true
        }

        // check if draw
        else if (cellValues[0] != "" && cellValues[1] != "" && cellValues[2] != "" &&
            cellValues[3] != "" && cellValues[4] != "" && cellValues[5] != "" &&
            cellValues[6] != "" && cellValues[7] != "" && cellValues[8] != "") {
            draw = true
            endStateReached = true
        }

        return endStateReached
    }

    private fun setGameOverMessage() {
        if (xWins)
            tv_game_over.text = "GAME OVER\nPlayer X wins!"
        else if (oWins)
            tv_game_over.text = "GAME OVER\nPlayer O wins!"
        else if (draw)
            tv_game_over.text = "GAME OVER\nDraw!"
        else
            tv_game_over.text = ""
    }

    private fun updateWinCounter() {
        if (xWins) {
            xCounter++
            tv_x_counter.text = "Player X: " + xCounter
        } else if (oWins) {
            oCounter++
            tv_o_counter.text = "Player O: " + oCounter
        }
    }
}