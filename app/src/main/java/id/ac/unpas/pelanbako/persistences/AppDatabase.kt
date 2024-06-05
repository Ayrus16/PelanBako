package id.ac.unpas.pelanbako.persistences

import androidx.room.Database
import androidx.room.RoomDatabase
import id.ac.unpas.pelanbako.models.item
import id.ac.unpas.pelanbako.models.order
import id.ac.unpas.pelanbako.models.order_item


@Database(entities = [item::class,order_item::class,order::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun ItemDao(): ItemDao
    abstract fun orderItemDao(): order_itemDao
    abstract fun orderDao(): orderDao

    companion object {
        const val DATABASE_NAME = "pelanbako-database"
    }
}