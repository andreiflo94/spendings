package com.heixss.spendings.feature.presentation.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.heixss.spendings.composables.AppScaffold
import com.heixss.spendings.composables.CameraGalleryChooser
import com.heixss.spendings.utils.ImageUtils

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun AddSpendingScreen(
    predefinedCategory: String,
    onAddClick: (category: String, description: String, checkImagePath: String?, value: String) -> Unit
) {
    var category by remember { mutableStateOf(predefinedCategory) }
    var description by remember { mutableStateOf("") }
    var value by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val context = LocalContext.current
    val imageUtils = ImageUtils(context)

    AppScaffold(pageTitle = "Add spending", onAddClick = {}) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = category,
                onValueChange = {
                    if (predefinedCategory.isEmpty()) {
                        category = it
                    }
                },
                label = { Text("Category") },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .semantics { testTag = "CategoryTextField" },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next
                ),
                enabled = predefinedCategory.isEmpty()
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Description") },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .semantics { testTag = "DescriptionTextField" },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = value,
                onValueChange = { value = it },
                label = { Text("Value") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Number
                ),
                keyboardActions = KeyboardActions {
                    keyboardController?.hide()
                },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .semantics { testTag = "ValueTextField" }
            )

            Spacer(modifier = Modifier.height(24.dp))

            CameraGalleryChooser(imageUtils)

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    onAddClick(
                        category,
                        description,
                        if (imageUtils.currentPhotoPath.isNullOrBlank())
                            null
                        else
                            imageUtils.currentPhotoPath,
                        value
                    )
                }
            ) {
                Text("Add",
                    modifier = Modifier.semantics { testTag = "Add" })
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewAddSpending() {
    AddSpendingScreen(predefinedCategory = "categolll", onAddClick = { category, description, imagePath, value -> })
}