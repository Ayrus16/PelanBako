package id.ac.unpas.pelanbako.models

import androidx.compose.runtime.Immutable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
@Entity
@Immutable
data class orderItem(
    @PrimaryKey
    val id: Int,
    @ForeignKey(entity = item::class, parentColumns = ["id"])
    val itemId: Int,
    @ForeignKey(entity = order::class, parentColumns = ["id"])
    val orderId: Int,
    val unitPrice: Float,
    val quantity: Int,
)
