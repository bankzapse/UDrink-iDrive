package co.th.udrinkidrive.datalayer.entity

import com.google.gson.JsonObject
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Item {

    @SerializedName("data")
    @Expose
    lateinit var data: ArrayList<Item>

    @SerializedName("message")
    @Expose
    lateinit var message: String

    @SerializedName("result")
    @Expose
    var result: Int = 0

    @SerializedName("_id")
    @Expose
    lateinit var _id : String


}
