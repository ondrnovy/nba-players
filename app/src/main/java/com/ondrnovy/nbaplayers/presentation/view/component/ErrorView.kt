package com.ondrnovy.nbaplayers.presentation.view.component

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

/**
 * View that shows an error message.
 */
@Composable
fun ErrorView(
    modifier: Modifier = Modifier,
    message: String,
){
    Box(
        modifier = modifier,
    ){
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = message,
            textAlign = TextAlign.Center,
        )
    }
}