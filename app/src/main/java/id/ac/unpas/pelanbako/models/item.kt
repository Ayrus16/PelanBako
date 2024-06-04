package id.ac.unpas.pelanbako.models

import androidx.compose.runtime.Immutable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
@Immutable
data class item(
    @PrimaryKey
    val id: String,
    val name: String,
    val description: String,
    val price: Int,
    val stock: Int,
)
