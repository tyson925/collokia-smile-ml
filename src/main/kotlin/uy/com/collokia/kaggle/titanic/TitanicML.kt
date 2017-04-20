package uy.com.collokia.kaggle.titanic

import com.fasterxml.jackson.dataformat.csv.CsvMapper
import com.fasterxml.jackson.dataformat.csv.CsvSchema
import org.apache.commons.math3.stat.descriptive.SummaryStatistics
import uy.com.collokia.ml.features.calculateCategoricalStat
import uy.com.collokia.util.TITANIC_HOME
import uy.com.collokia.util.TitanicPassenger
import java.io.File

class TitanicML {


    companion object {

        val TITANIC_TRAIN = "$TITANIC_HOME/train/train.csv"
        val TITANIC_TEST = "$TITANIC_HOME/test/test.csv"

        @JvmStatic fun main(args: Array<String>) {
            val titanicML = TitanicML()
            val titanicData = titanicML.readTitanicDataCSV(isTrain = true)
            //titanicML.numericalDataStats(titanicData)
            titanicML.categoricalDataStats(titanicData)
        }
    }

    fun categoricalDataStats(data: List<TitanicPassenger>) {

        val names = data.map { titanicPassenger -> titanicPassenger.name }.filterNotNull()
        val sexs = data.map { titanicPassenger -> titanicPassenger.sex }.filterNotNull()
        val tickets = data.map { titanicPassenger -> titanicPassenger.ticket }.filterNotNull()
        val cabins = data.map { titanicPassenger -> titanicPassenger.cabin }.filterNotNull()
        val embarkeds = data.map { titanicPassenger -> titanicPassenger.embarked }.filterNotNull()

        val namesStats = calculateCategoricalStat(names)
        val sexsStats = calculateCategoricalStat(sexs)
        val ticketStats = calculateCategoricalStat(tickets)
        val cabinsStats = calculateCategoricalStat(cabins)
        val embarkedsStats = calculateCategoricalStat(embarkeds)
        println(namesStats)
        println(sexsStats)
        println(ticketStats)
        println(cabinsStats)
        println(embarkedsStats)
    }

    fun numericalDataStats(data: List<TitanicPassenger>) {

        val idStat = SummaryStatistics()

        val survivedStat = SummaryStatistics()
        val pClassStat = SummaryStatistics()
        val ageStat = SummaryStatistics()
        val sibSbStat = SummaryStatistics()
        val parchStat = SummaryStatistics()
        val fareStat = SummaryStatistics()

        data.forEach { titanicPassenger ->
            idStat.addValue(titanicPassenger.passengerId.toDouble())
            survivedStat.addValue(titanicPassenger.survived.toDouble())
            titanicPassenger.pClass?.toDoubleOrNull()?.let { it -> pClassStat.addValue(it) }
            titanicPassenger.age?.toDoubleOrNull()?.let { it -> ageStat.addValue(it) }
            titanicPassenger.sibSp?.toDoubleOrNull()?.let { it -> sibSbStat.addValue(it) }
            titanicPassenger.parch?.toDoubleOrNull()?.let { it -> parchStat.addValue(it) }
            titanicPassenger.fare?.let { it -> fareStat.addValue(it) }
        }

        println("id: ${idStat.summary}")
        println("survived: ${survivedStat.summary}")
        println("class: ${pClassStat.summary}")
        println("age: ${ageStat.summary}")
        println("sibSb: ${sibSbStat.summary}")
        println("parch: ${parchStat.summary}")
        println("fare: ${fareStat.summary}")
    }


    fun readTitanicDataCSV(isTrain: Boolean): List<TitanicPassenger> {

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

        val csvReader = csvMapper.readerWithTypedSchemaFor(TitanicPassenger::class.java).with(schema)!!

        val contentFile = if (isTrain) File(TITANIC_TRAIN) else File(TITANIC_TEST)

        val content = contentFile.readLines(Charsets.UTF_8).joinToString("\n")

        val titanicDataInRaw = csvReader.readValues<TitanicPassenger>(content)


        val titanicData = titanicDataInRaw.iterator().asSequence().map { titanicData ->
            titanicData
        }

        //println(titanicData.toList())
        //println(titanicData.toList().joinToString("\n"))

        return titanicData.toList()
    }
}
