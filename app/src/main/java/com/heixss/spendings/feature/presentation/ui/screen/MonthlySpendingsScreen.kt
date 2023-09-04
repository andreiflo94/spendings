package com.heixss.spendings.feature.presentation.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.heixss.spendings.composables.Screen
import com.heixss.spendings.feature.data.database.Spending

@Composable
fun MonthlySpendingsScreen(
    spendings: State<List<Spending>>,
    onAddClick: () -> Unit,
    onItemClick: (Int, Int) -> Unit
) {
    Screen("Spendings by month") {
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
                MonthlySpendingItem(
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
fun MonthlySpendingItem(month: Int, year: Int, onItemClick: (Int, Int) -> Unit) {
    Button(
        onClick = { onItemClick(month, year) },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = "${month}/$year")
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMonthlySpendingItem() {
    MonthlySpendingItem(8, 2023, onItemClick = { _, _ ->

    })
}