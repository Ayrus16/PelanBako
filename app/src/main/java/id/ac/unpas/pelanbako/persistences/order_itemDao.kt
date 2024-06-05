package id.ac.unpas.pelanbako.persistences

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import id.ac.unpas.pelanbako.models.order_item

@Dao
interface order_itemDao {
    @Insert
    suspend fun insertOrderItem(order_item: order_item)

    @Update
    suspend fun updateOrderItem(order_item: order_item)

    @Delete
    suspend fun deleteOrderItem(order_item: order_item)

    @Query("SELECT * FROM order_item WHERE order_id = :orderId")
    suspend fun getOrderItemsByOrderId(orderId: Int): List<order_item>
}