package id.ac.unpas.pelanbako.persistences

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import id.ac.unpas.pelanbako.models.item
import id.ac.unpas.pelanbako.models.order_item

interface orderItemDao {
    @Insert
    suspend fun insertOrderItem(orderItem: order_item)

    @Update
    suspend fun updateOrderItem(orderItem: order_item)

    @Delete
    suspend fun deleteOrderItem(orderItem: order_item)

    @Query("SELECT * FROM order_item WHERE order_id = :orderId")
    suspend fun getOrderItemsByOrderId(orderId: Int): List<order_item>
}