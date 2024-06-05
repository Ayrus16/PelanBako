package id.ac.unpas.pelanbako.ui.screens


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import id.ac.unpas.pelanbako.models.*


@Composable
fun OrderDialog(
    order: order,
    orderItems: List<orderItem>,
    items: List<item>,
    onConfirm: () -> Unit,
    onAddItem: () -> Unit,
    onEditItem: (orderItem) -> Unit,
    onDismiss: () -> Unit
) {
    var showItemDialog by remember { mutableStateOf(false) }
    val currentItem = remember { mutableStateOf(item()) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Order Details") },
        text = {
            Column(modifier = Modifier.padding(16.dp)) {
                OutlinedTextField(
                    value = order.date,
                    onValueChange = { order.date = it },
                    label = { Text("Date") }
                )
                Spacer(modifier = Modifier.height(8.dp))
                LazyColumn(modifier = Modifier.height(200.dp)) {
                    items(orderItems.filter { it.orderId == order.id }) { orderItem ->
                        OrderItemCard(
                            orderItem = orderItem,
                            items = items,
                            onEdit = {
                                onEditItem(orderItem)
                            },
                            onDelete = {
                                scope.launch {
                                    orderItemDao.deleteOrderItem(orderItem)
                                }
                            }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = onAddItem) {
                    Text("Add Item")
                }
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = onConfirm) {
                    Text("Confirm")
                }
            }
        },
        confirmButton = {
            Button(onClick = onConfirm) {
                Text("Confirm")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )

    if (showItemDialog) {
        ItemDialog(
            item = currentItem.value,
            items = items,
            onConfirm = {
                scope.launch {
                    if (currentItem.value.id == 0L) {
                        itemDao.insertItem(currentItem.value)
                        currentItem.value.id = itemDao.getLastItemId()
                    } else {
                        itemDao.updateItem(currentItem.value)
                    }
                }
                showItemDialog = false
            },
            onDismiss = { showItemDialog = false }
        )
    }
}

@Composable
fun ItemDialog(
    item: item,
    items: List<item>,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Item Details") },
        text = {
            Column(modifier = Modifier.padding(16.dp)) {
                OutlinedTextField(
                    value = item.name,
                    onValueChange = { item.name = it },
                    label = { Text("Name") }
                )
                OutlinedTextField(
                    value = item.description,
                    onValueChange = { item.description = it },
                    label = { Text("Description") }
                )
                OutlinedTextField(
                    value = item.price.toString(),
                    onValueChange = { item.price = it.toFloatOrNull() ?: 0f },
                    label = { Text("Price") }
                )
                OutlinedTextField(
                    value = item.stock.toString(),
                    onValueChange = { item.stock = it.toIntOrNull() ?: 0 },
                    label = { Text("Stock") }
                )
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = onConfirm) {
                    Text("Confirm")
                }
            }
        },
        confirmButton = {
            Button(onClick = onConfirm) {
                Text("Confirm")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}

@Composable
fun OrderItemCard(
    orderItem: orderItem,
    items: List<item>,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            val item = items.find { it.id == orderItem.id }
            item?.let {
                Text(text = "Item: ${item.name}", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(text = "Description: ${item.description}", fontSize = 14.sp)
                Text(text = "Price: ${item.price}", fontSize = 14.sp)
                Text(text = "Quantity: ${orderItem.quantity}", fontSize = 14.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(onClick = onEdit, colors = ButtonDefaults.buttonColors( Color(0xFF6200EE))) {
                        Text(text = "Edit", color = Color.White)
                    }
                    Button(onClick = onDelete, colors = ButtonDefaults.buttonColors( Color.Red)) {
                        Text(text = "Delete", color = Color.White)
                    }
                }
            }
        }
    }
}