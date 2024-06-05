package id.ac.unpas.pelanbako.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController, modifier: Modifier = Modifier, onLihat: () -> Unit) {
    Column(modifier = modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Text(
            text = "Selamat Datang",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth() .padding(30.dp))
        
        Row {

        }
        Button(onClick = {
            navController.navigate(NavScreen.Add.route)
        }, modifier = Modifier.fillMaxWidth().padding(10.dp) .height(50.dp)) {
            Text(text = "Tambah Item", fontSize = 18.sp,)
        }
        Button(onClick = {
            onLihat()
        }, modifier = Modifier.fillMaxWidth().padding(10.dp) .height(50.dp)) {
            Text(text = "Cek Item", fontSize = 18.sp,)
        }
    }
}