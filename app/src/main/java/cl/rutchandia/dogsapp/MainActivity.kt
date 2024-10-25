package cl.rutchandia.dogsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import cl.rutchandia.dogsapp.presentation.navigation.DogsNav
import cl.rutchandia.dogsapp.ui.theme.DogsAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DogsAppTheme {
                Surface(modifier = Modifier.background(colorScheme.background)) {
                    DogsNav()
                }
            }
        }
    }
}


