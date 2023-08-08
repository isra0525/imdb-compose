package com.dojo.login_presentation.login.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dojo.core.R


@Composable
fun LoginTypeImage(
    image: Int,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f),
        ) {
            drawArc(
                color = Color.White,
                startAngle = 0f,
                sweepAngle = 360f,
                useCenter = true,
                size = size,
            )
        }
        Image(
            painter = painterResource(id = image),
            contentDescription = "Facebook Logo",
            modifier = Modifier
                .fillMaxWidth(0.4f)
                .height(100.dp)
        )
    }
}

@Preview(backgroundColor = 0xFF000000)
@Composable
fun LoginTypeImagePreview() {
    LoginTypeImage(
        R.drawable.apple,
        Modifier.size(90.dp)
    )
}