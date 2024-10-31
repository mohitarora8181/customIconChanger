package com.mohit.customlaunchertheme

import android.content.ComponentName
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mohit.customlaunchertheme.ui.theme.CustomLauncherThemeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CustomLauncherThemeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Home(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

fun changeIcon(context: Context,choice:String){
    val pm = context.packageManager
    val aliases = listOf(".JK",".Eyantra",".Chess",".Mohit")
    aliases.forEach({
        val state = if (it == choice)
            PackageManager.COMPONENT_ENABLED_STATE_ENABLED
        else
            PackageManager.COMPONENT_ENABLED_STATE_DISABLED
        pm.setComponentEnabledSetting(
            ComponentName(context,"com.mohit.customlaunchertheme$it"),
            state,
            PackageManager.DONT_KILL_APP
        )
    })
}

@Composable
fun Home(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Column (modifier=modifier.fillMaxSize(), verticalArrangement = Arrangement.Center){
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically)
            {
                CustomButton(Modifier.weight(0.5f), context,".JK","jk")
                Spacer(modifier = Modifier.width(10.dp))
                CustomButton(Modifier.weight(0.5f), context,".Eyantra","eyantra")
            }
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically)
            {
                CustomButton(Modifier.weight(0.5f), context,".Chess","chess")
                Spacer(modifier = Modifier.width(10.dp))
                CustomButton(Modifier.weight(0.5f), context,".Mohit","mohit")
            }
    }
}
