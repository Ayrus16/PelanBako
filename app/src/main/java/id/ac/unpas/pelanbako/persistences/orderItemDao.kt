package id.ac.unpas.pelanbako.persistences

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import id.ac.unpas.pelanbako.models.item
import id.ac.unpas.pelanbako.models.orderItem

interface orderItemDao {
    @Insert
    suspend fun insertOrderItem(orderItem: orderItem)

    @Update
    suspend fun updateOrderItem(orderItem: orderItem)

    @Delete
    suspend fun deleteOrderItem(orderItem: orderItem)

    @Query("SELECT * FROM order_item WHERE order_id = :orderId")
    suspend fun getOrderItemsByOrderId(orderId: Int): List<orderItem>
}