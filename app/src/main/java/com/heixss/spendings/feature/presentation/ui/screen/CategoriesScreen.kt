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
import com.heixss.spendings.composables.AppScaffold
import com.heixss.spendings.feature.domain.model.TotalSpendingPerCategory

@Composable
fun CategoriesScreen(
    uiModel: State<CategoriesScreenState>, onItemClick: (Long) -> Unit
) {
    AppScaffold(pageTitle = "Categories", onAddClick = {}) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Text(
                text = "Total Sum: " + uiModel.value.totalSum.toString(),
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(top = 16.dp)
            )
            Text(
                text = "Month: " + uiModel.value.month, style = MaterialTheme.typography.bodySmall, modifier = Modifier.padding(top = 16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            SpendingList(uiModel.value.totalSpendingPerCategoryList, onItemClick)
        }
    }
}

@Composable
fun SpendingList(
    spendingList: List<TotalSpendingPerCategory>, onItemClick: (Long) -> Unit
) {
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
fun SpendingListItem(
    spendingItem: TotalSpendingPerCategory, onItemClick: (Long) -> Unit
) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 8.dp, bottom = 8.dp), onClick = {
        onItemClick(spendingItem.categoryId)
    }) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = spendingItem.categoryName, style = MaterialTheme.typography.bodyMedium, maxLines = 1, overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = spendingItem.totalSpendingValue.toString(),
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
        CategoriesScreenState(
            totalSum = 1500.0, month = "15/08", totalSpendingPerCategoryList = listOf(
                TotalSpendingPerCategory(categoryId = 1, categoryName = "Category 1", totalSpendingValue = 600.0),
                TotalSpendingPerCategory(categoryId = 2, categoryName = "Category 2", totalSpendingValue = 700.0),
                TotalSpendingPerCategory(categoryId = 3, categoryName = "Category 3", totalSpendingValue = 200.0)
            )
        )
    }

    val uiModelState = remember { mutableStateOf(uiModel) }

    CategoriesScreen(uiModel = uiModelState, onItemClick = {})
}