package com.heixss.spendings.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun Screen(screenTitle: String, content: @Composable () -> Unit){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentHeight()
            .padding(16.dp)
    ){
        Text(
            modifier = Modifier.fillMaxWidth().wrapContentHeight()
                .padding(bottom = 16.dp),
            text = screenTitle,
            style = MaterialTheme.typography.headlineSmall,
            overflow = TextOverflow.Ellipsis)
        content()
    }
}