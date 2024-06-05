package id.ac.unpas.pelanbako.persistences

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import id.ac.unpas.pelanbako.models.order
@Dao
interface orderDao {



    @Insert
    suspend fun insertOrder(order: order)

    @Update
    suspend fun updateOrder(order: order)

    @Delete
    suspend fun deleteOrder(order: order)

//    @Query("SELECT * FROM order")
//    suspend fun getAllOrders(): List<order>
//
//    @Query("SELECT * FROM order WHERE id = :id")
//    suspend fun getOrderById(id: Int): order
}