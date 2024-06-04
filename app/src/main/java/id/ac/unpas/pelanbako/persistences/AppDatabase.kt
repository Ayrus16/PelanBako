package id.ac.unpas.pelanbako.persistences

import androidx.room.Database
import androidx.room.RoomDatabase
import id.ac.unpas.pelanbako.models.item


@Database(entities = [item::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun ItemDao(): ItemDao
    abstract fun orderItemDao(): OrderItemDao
    abstract fun orderDao(): OrderDao

    companion object {
        const val DATABASE_NAME = "pelanbako-database"
    }
}