package pl.wsei.pam.lab03

import android.view.Gravity
import android.view.View
import android.widget.ImageButton
import androidx.gridlayout.widget.GridLayout
import pl.wsei.pam.lab01.R
import java.util.Stack


class MemoryBoardView(
    private val gridLayout: GridLayout,
    private val cols: Int,
    private val rows: Int
) {
    private lateinit var mBoard: android.widget.GridLayout
    private val tiles: MutableMap<String, Tile> = mutableMapOf()
    private val icons: List<Int> = listOf(
        R.drawable.baseline_rocket_24,
    )
    init {
        val shuffledIcons: MutableList<Int> = mutableListOf<Int>().also {
            it.addAll(icons.subList(0, cols * rows / 2))
            it.addAll(icons.subList(0, cols * rows / 2))
            it.shuffle()
            // kod do edycji
            for (row in 0 until rows) {
                for (col in 0 until cols) {
                    val btn = ImageButton(gridLayout.context).also {
                        it.tag = "${row}x${col}"
                        val layoutParams = android.widget.GridLayout.LayoutParams()
                        it.setImageResource(R.drawable.baseline_audiotrack_24) // Tutaj ustaw identyfikator zasobu ikony
                        layoutParams.width = 0
                        layoutParams.height = 0
                        layoutParams.setGravity(Gravity.CENTER)
                        layoutParams.columnSpec = android.widget.GridLayout.spec(col, 1, 1f)
                        layoutParams.rowSpec = android.widget.GridLayout.spec(row, 1, 1f)
                        it.layoutParams = layoutParams
                        mBoard.addView(it)
                    }
                }
            }
        }


    }
    private val deckResource: Int = R.drawable.deck
    private var onGameChangeStateListener: (MemoryGameEvent) -> Unit = { (e) -> }
    private val matchedPair: Stack<Tile> = Stack()
    private val logic: MemoryGameLogic = MemoryGameLogic(cols * rows / 2)

    private fun onClickTile(v: View) {
        val tile = tiles[v.tag]
        matchedPair.push(tile)
        val matchResult = logic.process {
            tile?.tileResource?:-1
        }
        onGameChangeStateListener(MemoryGameEvent(matchedPair.toList(), matchResult))
        if (matchResult != GameStates.Matching) {
            matchedPair.clear()
        }
    }

    fun setOnGameChangeListener(listener: (event: MemoryGameEvent) -> Unit) {
        onGameChangeStateListener = listener
    }

    private fun addTile(button: ImageButton, resourceImage: Int) {
        button.setOnClickListener(::onClickTile)
        val tile = Tile(button, resourceImage, deckResource)
        tiles[button.tag.toString()] = tile
    }
}