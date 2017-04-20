package uy.com.collokia.util

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty


open class KaggleTestData @JsonCreator constructor(@JsonProperty("id") val id: String,
                                                   @JsonProperty("title") val title: String,
                                                   @JsonProperty("content") val content: String) {

    override fun toString(): String {

        return "KaggleTestData(id='$id', title='$title', content='$content')"
    }
}


open class TitanicPassenger @JsonCreator constructor(@JsonProperty("passengerId")val passengerId: Int, @JsonProperty("survived")val survived :Int,
                                                     @JsonProperty("pClass") val pClass : String?, @JsonProperty("name") val name : String?,
                                                     @JsonProperty("sex")  val sex : String?, @JsonProperty("age") val age : String?,
                                                     @JsonProperty("sibSp")  val sibSp : String?, @JsonProperty("parch") val parch : String?,
                                                     @JsonProperty("ticket")  val ticket : String?, @JsonProperty("fare") val fare : Double?,
                                                     @JsonProperty("cabin")  val cabin : String?, @JsonProperty("embarked") val embarked : String?) {


    override fun toString(): String {
        return "TitanicPassanger(passengerId=$passengerId, survived=$survived, pClass=$pClass, name=$name, sex=$sex, age=$age, sibSp=$sibSp, parch=$parch, ticket=$ticket, fare=$fare, cabin=$cabin, embarked=$embarked)"
    }
}




const val TITANIC_HOME = "./data/kaggle/titanic/"

