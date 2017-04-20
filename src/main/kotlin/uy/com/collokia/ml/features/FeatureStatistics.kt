package uy.com.collokia.ml.features

import uy.com.collokia.common.data.dataClasses.MutableIntBean
import uy.com.collokia.common.utils.incrementMapValue
import java.io.Serializable
import java.util.*

data class NumericalFeatureStat(val count : Int,val mean : Double,val std : Double,
                                val median : Double,val min : Int, val max : Int,
                                val lowQuartiles : Double,val highQuartiles : Double) : Serializable

data class CategoricalFeatureStat(val count : Int,val unique : Int,val top : String,val freq : Int) : Serializable

data class FeatureStat(val featureName : String, val column : Int, val attributeType : smile.data.Attribute.Type) : Serializable

class FeatureStatistics {




    companion object {


    }

}

fun calculateCategoricalStat(values : List<String>) : CategoricalFeatureStat{
    val frequencyStat = HashMap<String,MutableIntBean>()
    values.forEach { value ->
        frequencyStat.incrementMapValue(value)
    }

    val topItem = frequencyStat.minBy { it -> it.value }


    return  CategoricalFeatureStat(count = values.count(),unique = frequencyStat.size,top = topItem?.key ?: "",freq = topItem?.value?.value ?: -1)
}



