package com.ondrnovy.nbaplayers.presentation.view.component

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

/**
 * View that shows a loader.
 */
@Composable
fun LoaderView(
    modifier: Modifier = Modifier,
){
    Box(
        modifier = modifier,
    ){
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center)
        )
    }
}