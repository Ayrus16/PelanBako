package id.ac.unpas.pelanbako.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.benasher44.uuid.uuid4
import kotlinx.coroutines.launch

@Composable
fun FormItemScreen(modifier: Modifier = Modifier, id : String? = null) {

    val viewModel = hiltViewModel<ItemViewModel>()
    val scope = rememberCoroutineScope()

    val showError = remember { mutableStateOf(false) }


    val name = remember { mutableStateOf(TextFieldValue("")) }
    val description = remember { mutableStateOf(TextFieldValue("")) }
    val price = remember { mutableStateOf(TextFieldValue("")) }
    val stock = remember { mutableStateOf(TextFieldValue("")) }

    Column(modifier = modifier
        .fillMaxWidth()) {

        Column(modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()) {
            OutlinedTextField(
                label = { Text(text = "Name") },
                modifier = Modifier.fillMaxWidth(),
                value = name.value, onValueChange = {
                    name.value = it
                })

            OutlinedTextField(
                label = { Text(text = "Description") },
                modifier = Modifier.fillMaxWidth(),
                value = description.value, onValueChange = {
                    description.value = it
                })

            OutlinedTextField(
                label = { Text(text = "Price") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                value = price.value, onValueChange = {
                    price.value = it
                })
            OutlinedTextField(
                label = { Text(text = "Stock") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    value = stock.value, onValueChange = {
                    stock.value = it
                }

            )


            Row {
                Button(modifier = Modifier.weight(5f), onClick = {
                    if (id != null) {
                        val priceInt = price.value.text.toIntOrNull()
                        val stockInt = stock.value.text.toIntOrNull()
                        scope.launch {
                            viewModel.update(id, name.value.text, description.value.text, price.value.text.toInt(), stock.value.text.toInt())
                        }
                    } else {
                        scope.launch {
                            viewModel.insert(uuid4().toString(), name.value.text, description.value.text,  price.value.text.toInt(), stock.value.text.toInt())
                        }
                    }
                }) {
                    Text(text = "Simpan")
                }

                Button(modifier = Modifier.weight(5f), onClick = {
                    name.value = TextFieldValue("")
                    description.value = TextFieldValue("")
                    price.value = TextFieldValue("")
                    stock.value = TextFieldValue("")
                }) {
                    Text(text = "Batal")
                }
            }

        }


        viewModel.isDone.observe(LocalLifecycleOwner.current) {
            if (it) {
                name.value = TextFieldValue("")
                description.value = TextFieldValue("")
                price.value = TextFieldValue("")
                stock.value = TextFieldValue("")
            }
        }

        LaunchedEffect(id) {
            if (id != null) {
                scope.launch {
                    viewModel.find(id)
                }
            }
        }

        viewModel.item.observe(LocalLifecycleOwner.current) {
            name.value = TextFieldValue(it.name)
            description.value = TextFieldValue(it.description)
            price.value = TextFieldValue(it.price.toString())
            stock.value = TextFieldValue(it.stock.toString())
        }
    }

}