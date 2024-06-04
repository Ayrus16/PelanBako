package id.ac.unpas.pelanbako

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import id.ac.unpas.pelanbako.ui.screens.MainScreen
import id.ac.unpas.pelanbako.ui.theme.pelanbakoTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            pelanbakoTheme {
                // A surface container using the 'background' color from the theme
                MainScreen(onExitClick = {
                    finish()
                })
            }
        }
    }
}