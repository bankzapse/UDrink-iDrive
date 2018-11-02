package co.th.udrinkidrive.datalayer.local

import androidx.room.Database
import androidx.room.RoomDatabase
import co.th.udrinkidrive.datalayer.entity.Post

@Database(entities = [Post::class], version = AppDatabase.VERSION, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun postDao(): PostDao

    companion object {
        const val VERSION = 1
    }
}

