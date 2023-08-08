package com.dojo.core_ui.components

import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.dojo.core_ui.theme.ImdbTheme

@Composable
fun TrackButton(
    text: String = "",
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.secondary ,
        ),
    ) {
        Text(
            text = text,
            color = MaterialTheme.colors.primary
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TrackButtonPreview(
) {
    ImdbTheme() {
        TrackButton(
            text = "Empieza tu lista de seguimiento",
            onClick = { /*TODO*/ },
        )
    }
}