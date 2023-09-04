package com.heixss.spendings.feature.presentation.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.heixss.spendings.composables.Screen
import com.heixss.spendings.feature.data.database.Category
import com.heixss.spendings.feature.domain.uimodel.TotalSpendingPerCategory
import com.heixss.spendings.feature.domain.uimodel.MonthlySpendingsData

@Composable
fun MonthlySpendingsDetailedScreen(uiModel: State<MonthlySpendingsData>,
                                   onItemClick: (Long) -> Unit) {
    Screen("Spending categories by month"){
        Text(
            text = uiModel.value.totalSum.toString(),
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(top = 16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        SpendingList(uiModel.value.totalSpendingPerCategoryList, onItemClick)
    }
}

@Composable
fun SpendingList(spendingList: List<TotalSpendingPerCategory>,
                 onItemClick: (Long) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        this.items(spendingList) { spendingItem ->
            SpendingListItem(spendingItem, onItemClick)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpendingListItem(spendingItem: TotalSpendingPerCategory,
                     onItemClick: (Long) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
       onClick = {
           onItemClick(spendingItem.category.id)
       }
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = spendingItem.category.name,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = spendingItem.totalSpending.toString(),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun MonthlySpendingsDetailedScreenPreview() {
    val uiModel = remember {
        MonthlySpendingsData(
            totalSum = 1500.0,
            totalSpendingPerCategoryList = listOf(
                TotalSpendingPerCategory(Category(1, "Category 1"), 600.0),
                TotalSpendingPerCategory(Category(2, "Category 2"), 700.0),
                TotalSpendingPerCategory(Category(3, "Category 3"), 200.0)
            )
        )
    }

    val uiModelState = remember { mutableStateOf(uiModel) }

    MonthlySpendingsDetailedScreen(
        uiModel = uiModelState,
        onItemClick = {}
    )
}