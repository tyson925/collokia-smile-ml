package uy.com.collokia.kaggle.titanic

import com.fasterxml.jackson.dataformat.csv.CsvMapper
import com.fasterxml.jackson.dataformat.csv.CsvSchema
import uy.com.collokia.util.TITANIC_HOME
import uy.com.collokia.util.TitanicPassanger
import java.io.File

class TitanicML {


    companion object {
        val TITANIC_TRAIN = "$TITANIC_HOME/train/train.csv"
        val TITANIC_TEST = "$TITANIC_HOME/test/test.csv"
        @JvmStatic fun main(args: Array<String>) {
val titanicML = TitanicML()
            titanicML.readTitanicDataCSV(isTrain = true)
        }
    }


    fun readTitanicDataCSV(isTrain : Boolean){

        val schema = CsvSchema.builder()
                .addColumn("passengerId")
                .addColumn("survived")
                .addColumn("pClass")
                .addColumn("name")
                .addColumn("sex")
                .addColumn("age")
                .addColumn("sibSp")
                .addColumn("parch")
                .addColumn("ticket")
                .addColumn("fare")
                .addColumn("cabin")
                .addColumn("embarked")
                .build()

        val csvMapper = CsvMapper()

        val csvReader = csvMapper.readerWithTypedSchemaFor(TitanicPassanger::class.java).with(schema)!!


        val contentFile = if (isTrain) File(TITANIC_TRAIN) else File(TITANIC_TEST)

        val content = contentFile.readLines(Charsets.UTF_8).joinToString("\n")

        val titanicDataInRaw = csvReader.readValues<TitanicPassanger>(content)


        val titanicData = titanicDataInRaw.iterator().asSequence().map { titanicData ->
            titanicData
        }

        println(titanicData.toList())
        //titanicData.toList().joinToString("\n")

    }
}
