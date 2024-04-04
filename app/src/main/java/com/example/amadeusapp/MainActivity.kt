package com.example.amadeusapp

import android.media.Image
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import com.example.amadeusapp.ui.theme.AmadeusAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AmadeusAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.primary) {
                    LoginPage()
                }
            }
        }
    }
}

@Composable
fun LoginPage() {
    Column {
        Row(){
            Image(bitmap = ImageBitmap.imageResource(
                id = R.drawable.amadeus_logo2),
                contentDescription = "",
                modifier = Modifier.padding(PaddingValues(horizontal = Dp(5f), vertical = Dp(10f)))
            )
        }

        Column {
            Row(){
                Text( text = "USER_ID:" )
                Spacer(modifier = Modifier.width(Dp(5f)))
                TextField(
                    value = "",
                    onValueChange = {}
                )
            }

            Row(){
                Text(
                    text = "PASSWORD:",
                )
                Spacer(modifier = Modifier.width(Dp(5f)))
                TextField(
                    value = "",
                    onValueChange = {}
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AmadeusAppTheme {
        LoginPage()
    }
}