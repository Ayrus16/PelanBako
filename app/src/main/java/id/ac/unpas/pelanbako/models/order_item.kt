package id.ac.unpas.pelanbako.models

import androidx.compose.runtime.Immutable
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
@Immutable
data class order_item(
    @PrimaryKey
    val id: Int,
//    @ForeignKey(entity = item::class, parentColumns = ["id"])
    val item_id: Int,
//    @ForeignKey(entity = order::class, parentColumns = ["id"])
    val order_id: Int,
    val unitPrice: Float,
    val quantity: Int,
)
