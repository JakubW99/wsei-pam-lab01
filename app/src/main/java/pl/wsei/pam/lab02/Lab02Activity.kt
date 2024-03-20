package pl.wsei.pam.lab02
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.GridLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import pl.wsei.pam.lab03.Lab03Activity
import pl.wsei.pam.lab01.R

class Lab02Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lab02)

        val gridLayout: GridLayout = findViewById(R.id.favorites_grid)
        for (i in 0 until gridLayout.childCount) {
            val child: View = gridLayout.getChildAt(i)
            if (child is Button) {
                child.setOnClickListener {
                    val tag: String? = it.tag as String?
                    val tokens: List<String>? = tag?.split(" ")
                    val rows = tokens?.get(0)?.toInt()
                    val columns = tokens?.get(1)?.toInt()
                    Toast.makeText(this, "Rows: ${rows}, Columns: ${columns}", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this, Lab03Activity::class.java)

                    intent.putExtra("columns", columns)
                    intent.putExtra("rows", rows)
                    startActivity(intent)
                }
            }
        }
    }
}
