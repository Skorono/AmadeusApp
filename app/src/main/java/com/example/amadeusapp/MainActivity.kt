package com.example.amadeusapp

import android.media.Image
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.room.*
import com.example.amadeusapp.ui.theme.AmadeusAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        val db= Room.databaseBuilder(
            applicationContext,
            UserDataBase::class.java,
            "userDatabase"
        ).build()

        super.onCreate(savedInstanceState)
        setContent {
            AmadeusAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.primary) {
                    LoginPage(db)
                }
            }
        }
    }
}

@Composable
fun LoginPage(db: RoomDatabase?) {
    var userId by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }


    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.Black)) {
        Row(){
            Image(bitmap = ImageBitmap.imageResource(
                id = R.drawable.amadeus_logo2),
                contentDescription = "",
                modifier = Modifier.padding(PaddingValues(horizontal = Dp(5f), vertical = Dp(10f)))
            )
        }

        Column(
            Modifier
                .padding(top = Dp(85f))
                .align(Alignment.End)) {

            EnterField(prefixText = "USER_ID:", onValueChange = {it -> userId += it}, modifier = Modifier.align(Alignment.End))

            Spacer(modifier = Modifier.height(Dp(10f)))
            EnterField(prefixText = "PASSWORD:", onValueChange = {it -> password += it}, modifier = Modifier.align(Alignment.End))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainPreview() {
    AmadeusAppTheme {
        LoginPage(null)
    }
}

@Composable
fun EnterField(prefixText: String, onValueChange: (String) -> Unit, modifier: Modifier = Modifier ){
    Row(modifier.padding(end = Dp(15f))){
        Text(
            text = prefixText,
            color = Color.LightGray,
        )
        Spacer(modifier = Modifier.width(Dp(5f)))
        TextField(
            value = "",
            onValueChange = { onValueChange },
            modifier = Modifier
                .width(Dp(250f))
                .height(Dp(25f))
        )
    }
}

@Entity
data class User(
    @PrimaryKey(autoGenerate = true) val Id: Int,
    @ColumnInfo(name = "Name") val name: String,
    @ColumnInfo(name = "Password") val password: String
)

@Dao
interface UserDAO{
    @Query("SELECT * FROM user")
    fun getAll(): List<User>

    @Query("SELECT * FROM user WHERE user.Id = (:id)")
    fun getById(id: Int): User
    @Query("SELECT * FROM user WHERE user.Name = (:name)")
    fun getByName(name: String): List<User>
}

@Database(entities = [User::class], version = 1)
abstract class UserDataBase(): RoomDatabase() {
    abstract fun userDAO(): UserDAO
}

