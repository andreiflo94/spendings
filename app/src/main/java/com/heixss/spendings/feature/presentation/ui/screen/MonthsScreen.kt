package com.heixss.spendings.feature.presentation.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.heixss.spendings.composables.AppScaffold
import com.heixss.spendings.feature.domain.model.Spending

@Composable
fun MonthsScreen(
    spendings: State<List<Spending>>,
    onAddClick: () -> Unit,
    onItemClick: (Int, Int) -> Unit
) {
    AppScaffold(
        pageTitle = "Months",
        showAddButton = true,
        onAddClick = { onAddClick() },
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
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