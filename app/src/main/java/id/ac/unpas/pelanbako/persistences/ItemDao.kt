package id.ac.unpas.pelanbako.persistences

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.ac.unpas.pelanbako.models.item

@Dao
interface ItemDao {

    @Query("select * from item")
    fun loadAll(): LiveData<List<item>>

    @Query("select * from item")
    suspend fun findAll(): List<item>

    @Query("select * from item where id = :id")
    fun load(id: String): LiveData<item>

    @Query("select * from item where id = :id")
    suspend fun getById(id: String): item?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(vararg items: item)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(list: List<item>)

    @Query("delete from item where id = :id")
    suspend fun delete(id: String)

    @Query("select * from item where id = :id")
    suspend fun find(id: String): item?

    @Delete
    suspend fun delete(item: item)
}