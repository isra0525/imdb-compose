package com.dojo.core_ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dojo.core_ui.styles.inputStyle
import com.dojo.core_ui.theme.ImdbTheme

@Composable
fun ImdbInput(
    modifier: Modifier = Modifier,
    inputName: String,
    text: String,
    placeholder: String = "",
    onValueChange: (String) -> Unit,
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {
    Row(modifier = modifier.height(70.dp)) {
        Column {
            Text(
                text = inputName,
                style = inputStyle,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Card(
                elevation = 2.dp,
                shape = MaterialTheme.shapes.medium,
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ){
                    BasicTextField(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(14.dp),
                        value = text,
                        onValueChange = onValueChange,
                        singleLine = true,
                        visualTransformation = visualTransformation,
                        decorationBox = { innerTextField ->
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(14.dp)
                            ) {
                                if (placeholder.isNotEmpty() && text.isEmpty()) {
                                    Text(
                                        text = placeholder,
                                        style = inputStyle.copy(
                                            color = Color.LightGray,
                                            fontWeight = FontWeight.Normal
                                        )
                                    )
                                }
                            }
                            innerTextField()
                        },
                    )
                }

            }
        }
    }
}

@Preview(
    showBackground = true,
    widthDp = 320,
    heightDp = 100
)
@Composable
fun ImdbInputPreview(
) {

    val aaaa = remember {
        mutableStateOf("aaaa")
    }
    ImdbTheme {
        Box (modifier = Modifier.padding(16.dp)) {
            ImdbInput(
                modifier = Modifier.fillMaxWidth(),
                inputName = "Email",
                text = aaaa.value,
                placeholder = "jperez@aaa.com",
                onValueChange = { print(it) },
            )
        }
    }
}

@Preview(
    showBackground = true,
    widthDp = 320,
    heightDp = 100
)
@Composable
fun ImdbInputUserPreview(
) {
    val aaaa = remember {
        mutableStateOf("aaaa")
    }
    ImdbTheme {
        Box (modifier = Modifier.padding(16.dp)) {
            ImdbInput(
                modifier = Modifier.fillMaxWidth(),
                inputName = "Password",
                placeholder = "Password",
                text = aaaa.value,
                onValueChange = { println(it) },
                visualTransformation = PasswordVisualTransformation()
            )
        }
    }
}