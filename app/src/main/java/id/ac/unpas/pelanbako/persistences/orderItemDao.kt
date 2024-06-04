package id.ac.unpas.pelanbako.persistences

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.ac.unpas.pelanbako.models.item
interface orderItemDao {
    @Insert
    suspend fun insertOrderItem(orderItem: OrderItem)

    @Update
    suspend fun updateOrderItem(orderItem: OrderItem)

    @Delete
    suspend fun deleteOrderItem(orderItem: OrderItem)

    @Query("SELECT * FROM order_item WHERE order_id = :orderId")
    suspend fun getOrderItemsByOrderId(orderId: Int): List<OrderItem>
}