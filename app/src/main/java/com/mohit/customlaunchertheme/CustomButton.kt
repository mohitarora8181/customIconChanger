package com.mohit.customlaunchertheme

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.alpha
import androidx.core.graphics.drawable.toBitmap
import androidx.palette.graphics.Palette

@Composable
fun CustomButton(modifier: Modifier,context:Context,choice:String,image:String) {
    val resourceID = context.resources.getIdentifier(image,"drawable",context.packageName)
    val dominantColor = getColorFromImage(context.resources.getDrawable(resourceID,null))
    val colorWithAlpha = dominantColor.copy(alpha = 0.5f)
    Button(
        onClick = { changeIcon(context,choice) },
        shape = RoundedCornerShape(15.dp),
        border = ButtonDefaults.outlinedButtonBorder,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = colorWithAlpha,
        )
    ) {

        Column (modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = resourceID),
                contentDescription = choice,
                modifier=Modifier.clip(RoundedCornerShape(10.dp)))
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = choice.removePrefix("."), color = getComplementaryColor(dominantColor), fontSize = 20.sp)
        }
    }
}

fun getColorFromImage(image:Drawable):Color{
    val bitmap = image.toBitmap()
    val palette = Palette.from(bitmap).generate()
    var dominantcolor = Color.White.toArgb()
    return Color(palette.getDominantColor(dominantcolor))
}


fun getComplementaryColor(color: Color): Color {
    val red = (color.red * 255).toInt()
    val green = (color.green * 255).toInt()
    val blue = (color.blue * 255).toInt()

    return Color(
        red = 255 - red,
        green = 255 - green,
        blue = 255 - blue,
        alpha = 255
    )
}