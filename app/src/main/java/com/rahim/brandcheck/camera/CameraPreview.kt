package com.rahim.brandcheck.camera

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.core.CameraSelector
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.camera.view.PreviewView
import androidx.compose.ui.tooling.preview.Preview as ComposePreview
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CameraPreview(modifier: Modifier = Modifier) {
    AndroidView(
        factory = { context ->
            // Create the PreviewView
            val previewView = PreviewView(context)

            // Setup CameraX
            val cameraProvider = ProcessCameraProvider.getInstance(context).get()
            val preview = androidx.camera.core.Preview.Builder().build()
            preview.setSurfaceProvider(previewView.surfaceProvider)

            val cameraSelector = CameraSelector.Builder()
                .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                .build()

            // Bind the camera to the lifecycle
            cameraProvider.bindToLifecycle(
                context as ComponentActivity, // Make sure it's an Activity context
                cameraSelector,
                preview
            )

            // Set layoutParams only if necessary
            // Compose modifier is handling most of the layout, but you can still adjust manually if needed
            previewView.layoutParams = android.widget.FrameLayout.LayoutParams(
                android.widget.FrameLayout.LayoutParams.MATCH_PARENT,
                android.widget.FrameLayout.LayoutParams.MATCH_PARENT
            )

            previewView // Return the PreviewView
        },
        modifier = modifier.fillMaxSize() // Set the modifier for layout
    )
}

@ComposePreview(showBackground = true)
@Composable
fun CameraPreviewPreview() {
    CameraPreview() // Preview for Compose
}
