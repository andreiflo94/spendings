package com.heixss.spendings.feature.presentation.ui.screen


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.heixss.spendings.composables.Screen
import com.heixss.spendings.feature.domain.uimodel.UISpendingModel

@Composable
fun SpendingsByCategoriesScreen(
    spendingList: State<List<UISpendingModel>>,
    onDelete: (Long) -> Unit
) {
    Screen(screenTitle = "Spendings by Category"){
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            this.items(spendingList.value) { spendingItem ->
                SpendingListItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(8.dp),
                    spendingItem,
                    onDelete
                )
            }
        }
    }
}

@Composable
fun SpendingListItem(
    modifier: Modifier = Modifier,
    spendingItem: UISpendingModel,
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
                    text = spendingItem.category,
                    style = MaterialTheme.typography.bodyLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = spendingItem.date,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            Text(
                modifier = Modifier.fillMaxWidth()
                    .wrapContentHeight()
                    .padding(top = 8.dp, bottom = 8.dp),
                text = spendingItem.description,
                style = MaterialTheme.typography.bodyMedium,
                overflow = TextOverflow.Visible
            )
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

//@Preview(showBackground = true)
//@Composable
//fun PreviewSpendingsByCategoriesScreen() {
//    val spendingList = remember {
//        mutableStateOf(
//            listOf(
//                UISpendingModel("Rata", "Plata ratei", 321.0, "2/12/2004"),
//                UISpendingModel("Rata", "Plata ratei", 321.0, "2/12/2004")
//            )
//        )
//    }
//    SpendingsByCategories(spendingList, {
//
//    })
//}

@Preview(showBackground = true)
@Composable
fun PreviewSpendingItem() {

    SpendingListItem(modifier =  Modifier
        .fillMaxWidth()
        .padding(16.dp),
        UISpendingModel("Rata", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", 321.0, "2/12/2004",320),
        {})
}
