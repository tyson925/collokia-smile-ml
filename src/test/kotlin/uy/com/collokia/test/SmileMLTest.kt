package uy.com.collokia.test

import smile.data.parser.ArffParser
import uy.com.collokia.util.array2dOfDouble
import java.io.File

class SmileMLTest() {
    companion object {
         fun main(args: Array<String>) {
            val smileTest = SmileMLTest()
            smileTest.arffReader()
        }
    }


    fun arffReader() {
        val arffParser = ArffParser()
        arffParser.responseIndex = 5
        println(File("./data/arff/test/iris/iris.arff").exists())
        val weather = arffParser.parse("./data/arff/test/iris/iris.arff")
        DoubleArray(2)

        val x = weather.toArray(array2dOfDouble(weather.size(), 0))
        val y = weather.toArray(intArrayOf(weather.size()))

        println(x.joinToString("\n"))
        println(y.joinToString("\n"))
    }

}