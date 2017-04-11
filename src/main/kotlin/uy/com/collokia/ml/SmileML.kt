package uy.com.collokia.ml

import smile.data.parser.ArffParser
import uy.com.collokia.common.utils.array2dOfDouble


class SmileML {
    companion object {
        @JvmStatic fun main(args: Array<String>) {
            println("lamer")

            val smile = SmileML()
            smile.arffReader()
        }
    }

    fun arffReader() {
        val arffParser = ArffParser()
        arffParser.responseIndex = 4
        val weather = arffParser.parse("./data/arff/test/iris/iris.arff")
        DoubleArray(2)

        val x = weather.toArray(array2dOfDouble(weather.size(), 0))
        val y = weather.toArray(intArrayOf(weather.size()))

        println(x.joinToString("\n"))
        println(y.joinToString("\n"))
    }
}


