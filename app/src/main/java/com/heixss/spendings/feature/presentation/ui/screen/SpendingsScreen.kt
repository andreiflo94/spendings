package com.heixss.spendings.feature.presentation.ui.screen


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.heixss.spendings.composables.AppScaffold
import com.heixss.spendings.feature.domain.model.Spending

@Composable
fun SpendingsScreen(
    uiCategorySpendings: State<SpendingsScreenState>,
    onCheckImageClick: (String) -> Unit,
    onDelete: (Long) -> Unit,
    onAddClick: (pageCategory: String) -> Unit
) {
    AppScaffold(
        pageTitle = uiCategorySpendings.value.category,
        showAddButton = true,
        onAddClick = { onAddClick(uiCategorySpendings.value.category) },
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(it)
        ) {
            this.items(uiCategorySpendings.value.spendings.value) { spendingItem ->
                SpendingListItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(8.dp),
                    spendingItem,
                    onCheckImageClick = onCheckImageClick,
                    onDelete = onDelete
                )
            }
        }
    }
}

@Composable
fun SpendingListItem(
    modifier: Modifier = Modifier,
    spendingItem: Spending,
    onCheckImageClick: (String) -> Unit,
    onDelete: (Long) -> Unit
) {
    Card(
        modifier = modifier
    ) {
        Column(
            modifier = modifier
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier
                        .wrapContentWidth(Alignment.Start)
                        .weight(1.5f)
                        .wrapContentHeight()
                        .padding(top = 8.dp, bottom = 8.dp),
                    text = spendingItem.description,
                    style = MaterialTheme.typography.bodyMedium,
                    overflow = TextOverflow.Visible,
                    maxLines = Int.MAX_VALUE,
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    modifier = Modifier
                        .wrapContentWidth(Alignment.End)
                        .weight(0.5f),
                    text = String.format("%d/%d/%d", spendingItem.day, spendingItem.month, spendingItem.year),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            CheckImageRow(spendingItem, onCheckImageClick)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = spendingItem.sum.toString(),
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(8.dp))
                IconButton(
                    onClick = {
                        onDelete(spendingItem.spendingId)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Delete, // Using the Delete icon from Material Design
                        contentDescription = null, // Content description for accessibility
                        modifier = Modifier.padding(end = 8.dp),
                    )
                }
            }
        }
    }
}

@Composable
@OptIn(ExperimentalGlideComposeApi::class)
private fun CheckImageRow(spendingItem: Spending, onCheckImageClick: (String) -> Unit) {
    spendingItem.checkImagePath?.let { checkImagePath ->
        IconButton(
            modifier = Modifier
                .size(100.dp),
            onClick = {
                onCheckImageClick(checkImagePath)
            }
        ) {
            GlideImage(
                model = checkImagePath,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize(),
            )
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Preview
@Composable
fun PreviewSpendingsScreen() {
    SpendingsScreen(uiCategorySpendings = mutableStateOf(
        SpendingsScreenState(
            "test",
            spendings = mutableStateOf(listOf())
        )
    ), onDelete = {}, onCheckImageClick = {}, onAddClick = {})
}

@Preview(showBackground = true)
@Composable
fun PreviewSpendingItem() {
    SpendingListItem(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        Spending(
            "Lorem Ipsum is sLorem Ipsum is sLorem Ipsum is sLorem Ipsum is sLorem Ipsum is sLorem Ipsum is sLorem Ipsum is sLorem Ipsum is sLorem Ipsum is sLorem Ipsum is sLorem Ipsum is sLorem Ipsum is sLorem Ipsum is sLorem Ipsum is s",
            321.0,
            checkImagePath = "https://cdn.britannica.com/96/1296-050-4A65097D/gelding-bay-coat.jpg",
            day = 17,
            month = 1,
            year = 2024,
            320
        ),
        {},
        {}
    )
}
