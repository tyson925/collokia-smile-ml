package uy.com.collokia.kaggle.titanic.visualization.histograms

import smile.plot.Histogram
import uy.com.collokia.kaggle.titanic.TitanicML
import java.awt.GridLayout
import javax.swing.JFrame
import javax.swing.JPanel


class PClassHistograms(values: DoubleArray) : JPanel(GridLayout(1, 2)) {

    companion object {


        @JvmStatic fun main(args: Array<String>) {
            val titanicML = TitanicML()
            val titanicData = titanicML.readTitanicDataCSV(isTrain = true)
            val pClassValues = titanicData.filter { titanicPassenger -> titanicPassenger.survived == 0 }.
                    map { titanicPassenger -> titanicPassenger.age?.toDoubleOrNull() }.filterNotNull().toDoubleArray()
            println(pClassValues.joinToString(" "))
            val frame = JFrame("Histogram")
            frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
            frame.setLocationRelativeTo(null)

            frame.contentPane.add(PClassHistograms(pClassValues))
            frame.isVisible = true
        }
    }

    fun showHistogram(){

    }

    init {
        val canvas = Histogram.plot("Dataset 1", values)
        canvas.title = "Survived = 0"
        canvas.setAxisLabel(0,"Age")
        canvas.setAxisLabel(1,"Percentage")
        add(canvas)
    }
}
