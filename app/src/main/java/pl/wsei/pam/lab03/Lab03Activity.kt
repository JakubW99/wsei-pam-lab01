package pl.wsei.pam.lab03

import android.os.Bundle
import android.view.Gravity
import android.widget.GridLayout
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import pl.wsei.pam.lab01.R


private lateinit var mBoard: GridLayout
class Lab03Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lab03)

        val columns = intent.getIntExtra("columns", 3)

        mBoard = findViewById(R.id.mBoard)

        val rows = intent.getIntExtra("rows", 3)

        // Ustaw liczbę kolumn i wierszy w układzie
        mBoard.columnCount = columns
        mBoard.rowCount = rows
        val mBoardModel = MemoryBoardView(mBoard, <columns>, <rows>)


        }
    }