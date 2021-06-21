package com.example.tictactoe

import android.os.Build.VERSION_CODES.Q
import android.widget.TextView
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.CoreMatchers.anyOf

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun should_fill_cell_textview_when_clicked() {
        onView(withId(R.id.cell1))
            .perform(click())
            .check(matches(anyOf(
                withText("X"),
                withText("O")
            )))
    }

    @Test
    fun should_update_text_in_current_player_when_clicked() {
        onView(withId(R.id.current_player))
            .check(matches(withText("Player X, make a move")))

        onView(withId(R.id.cell1))
            .perform(click())

        onView(withId(R.id.current_player))
            .check(matches(withText("Player O, make a move")))
    }

    @Test
    fun should_update_text_to_x_win_message_when_game_over() {
        setUpXWinState()

        onView(withId(R.id.game_over_message))
            .check(matches(withText("GAME OVER/nPlayer X wins!")))
    }

    @Test
    fun should_update_text_to_o_win_message_when_game_over() {
        setUpOWinState()

        onView(withId(R.id.game_over_message))
            .check(matches(withText("GAME OVER/nPlayer O wins!")))
    }

    @Test
    fun should_update_text_to_draw_message_when_game_over() {
        setUpDrawState()

        onView(withId(R.id.game_over_message))
            .check(matches(withText("GAME OVER/nDraw!")))
    }

    private fun setUpXWinState() {
        onView(withId(R.id.cell1)) // X
            .perform(click())

        onView(withId(R.id.cell4)) // O
            .perform(click())

        onView(withId(R.id.cell2)) // X
            .perform(click())

        onView(withId(R.id.cell5)) // O
            .perform(click())

        onView(withId(R.id.cell3)) // X
            .perform(click())
    }

    private fun setUpOWinState() {
        onView(withId(R.id.cell4)) // X
            .perform(click())

        onView(withId(R.id.cell1)) // O
            .perform(click())

        onView(withId(R.id.cell5)) // X
            .perform(click())

        onView(withId(R.id.cell2)) // O
            .perform(click())

        onView(withId(R.id.cell7)) // X
            .perform(click())

        onView(withId(R.id.cell3)) // O
            .perform(click())
    }

    private fun setUpDrawState() {
        onView(withId(R.id.cell1)) // X
            .perform(click())

        onView(withId(R.id.cell5)) // O
            .perform(click())

        onView(withId(R.id.cell2)) // X
            .perform(click())

        onView(withId(R.id.cell3)) // O
            .perform(click())

        onView(withId(R.id.cell7)) // X
            .perform(click())

        onView(withId(R.id.cell4)) // O
            .perform(click())

        onView(withId(R.id.cell6)) // X
            .perform(click())

        onView(withId(R.id.cell8)) // O
            .perform(click())

        onView(withId(R.id.cell9)) // X
            .perform(click())
    }
}