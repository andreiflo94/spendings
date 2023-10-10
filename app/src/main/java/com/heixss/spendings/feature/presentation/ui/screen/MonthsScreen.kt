package com.heixss.spendings.feature.presentation.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.heixss.spendings.composables.Screen
import com.heixss.spendings.feature.data.database.SpendingEntity

@Composable
fun MonthsScreen(
    spendings: State<List<SpendingEntity>>,
    onAddClick: () -> Unit,
    onItemClick: (Int, Int) -> Unit
) {
    Screen("Months") {
        Button(
            onClick = onAddClick,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(text = "Add")
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            this.items(spendings.value.distinctBy {
                "${it.month}/${it.year}"
            }) { spending ->
                MonthItem(
                    month = spending.month,
                    year = spending.year,
                    onItemClick = { month, year ->
                        onItemClick(month, year)
                    }
                )
            }
        }
    }
}

@Composable
fun MonthItem(month: Int, year: Int, onItemClick: (Int, Int) -> Unit) {
    Button(
        onClick = { onItemClick(month, year) },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = "${month}/$year")
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMonthItem() {
    MonthItem(8, 2023, onItemClick = { _, _ ->

    })
}