package id.ac.unpas.pelanbako.models
import androidx.compose.runtime.Immutable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.type.DateTime

@Entity
@Immutable
data class order(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "date")
    val date: String,
    val totalPrice: Float,
)
