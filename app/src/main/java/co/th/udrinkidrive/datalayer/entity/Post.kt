package co.th.udrinkidrive.datalayer.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "post")
class Post {

    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id")
    lateinit var id: String

    @ColumnInfo(name = "title")
    @SerializedName("title")
    lateinit var title: String

    @ColumnInfo(name = "body")
    @SerializedName("body")
    lateinit var body: String
}
