package com.heixss.spendings.composables

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.heixss.spendings.utils.ImageUtils

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CameraGalleryChooser() {
    val context = LocalContext.current

    val imageUtils = ImageUtils(context)

    var currentPhoto by remember {
        mutableStateOf<String?>(null)
    }

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == Activity.RESULT_OK) {
            val data = it.data?.data
            currentPhoto = if (data == null) {
                // Camera intent
                imageUtils.currentPhotoPath
            } else {
                // Gallery Pick Intent
                imageUtils.getPathFromGalleryUri(data)
            }
        }
    }

    Column {
        Button(
            onClick = { launcher.launch(imageUtils.getIntent()) }
        ) {
            Text(text = "Choose or take image")
        }

        if (currentPhoto != null) {
            // You can also use a normal Image by decoding the currentPhoto to a Bitmap using the BitmapFactory
            GlideImage(
                model = currentPhoto,
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .padding(16.dp)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun CameraGalleryChooserPreview() {
    CameraGalleryChooser()
}