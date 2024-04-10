package pl.wsei.pam.lab03
import android.os.Bundle
import android.view.Gravity
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.gridlayout.widget.GridLayout
import pl.wsei.pam.lab01.R
class Lab03Activity : AppCompatActivity() {
    private lateinit var mBoard: GridLayout
    private lateinit var mBoardModel: MemoryBoardView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lab03)
        mBoard = findViewById(R.id.mBoard)
        val columns = intent.getIntExtra("columns", 3)
        val size = intent.getIntArrayExtra("size") ?: intArrayOf(3, 3)
        mBoard.columnCount = size[0]
        mBoard.rowCount = size[1]
        for (row in 0 until mBoard.rowCount) {
            for (col in 0 until mBoard.columnCount) {
                val btn = ImageButton(this).also {
                    it.tag = "${row}x${col}"
                    val layoutParams = GridLayout.LayoutParams()
                    it.setImageResource(R.drawable.ic_launcher_background)
                    layoutParams.width = 0
                    layoutParams.height = 0
                    layoutParams.setGravity(Gravity.CENTER)
                    layoutParams.columnSpec = GridLayout.spec(col, 1, 1f)
                    layoutParams.rowSpec = GridLayout.spec(row, 1, 1f)
                    it.layoutParams = layoutParams
                }
                mBoard.addView(btn)
            }
        }
    }

}