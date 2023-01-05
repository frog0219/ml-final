package com.example.jetsnack.database

import android.graphics.Bitmap
import androidx.compose.runtime.Composable
import com.example.jetsnack.ml.Model
import androidx.compose.ui.platform.LocalContext
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.nio.ByteBuffer
import java.nio.ByteOrder

object TensorFLowHelper {
    const val imageSize = 224
    @Composable
    fun classifyImage(image: Bitmap): Int {
        val model: Model = Model.newInstance(LocalContext.current)

        // Creates inputs for reference.
        val inputFeature0 =
            TensorBuffer.createFixedSize(intArrayOf(1, 224, 224, 3), DataType.FLOAT32)
        val byteBuffer: ByteBuffer = ByteBuffer.allocateDirect(4 * imageSize * imageSize * 3)
        byteBuffer.order(ByteOrder.nativeOrder())
        val intValues = IntArray(imageSize * imageSize)
        image.getPixels(intValues, 0, image.width, 0, 0, image.width, image.height)
        var pixel = 0
        //iterate over each pixel and extract R, G, and B values. Add those values individually to the byte buffer.
        for (i in 0 until imageSize) {
            for (j in 0 until imageSize) {
                val `val` = intValues[pixel++] // RGB
                byteBuffer.putFloat((`val` shr 16 and 0xFF) / (255f))
                byteBuffer.putFloat((`val` shr 8 and 0xFF) / (255f))
                byteBuffer.putFloat((`val` and 0xFF) / (255f))
            }
        }
        inputFeature0.loadBuffer(byteBuffer)

        // Runs model inference and gets result.
        val outputs: Model.Outputs = model.process(inputFeature0)
        val outputFeature0: TensorBuffer = outputs.getOutputFeature0AsTensorBuffer()
        val confidences = outputFeature0.floatArray
        // find the index of the class with the biggest confidence.
        var maxPos = 11
        var maxConfidence = 0.4f
        for (i in confidences.indices) {
            if (confidences[i] > maxConfidence) {
                maxConfidence = confidences[i]
                maxPos = i
            }
        }


        // Releases model resources if no longer used.
        model.close()
        return maxPos

    }

}