package org.unizd.rma.roncevic.presentation.contact.create

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import kotlinx.coroutines.runBlocking
import org.unizd.rma.roncevic.R
import org.unizd.rma.roncevic.presentation.components.DropdownMenuExample
import org.unizd.rma.roncevic.presentation.components.TextInput
import org.unizd.rma.roncevic.presentation.components.showDatePicker
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Objects

@Composable
fun CreateExpenseScreen(
    navController: NavController,
    createExpenseViewModel: CreateExpenseViewModel = hiltViewModel()
) {
    Log.d("CreateExpenseScreen", "Create")

    val context = LocalContext.current

    val expense = LocalContext.current
    var file = expense.createImageFile()
    val uri = FileProvider.getUriForFile(
        Objects.requireNonNull(expense),
        expense.packageName + ".provider",
        file
    )

    var capturedImageUri by remember {
        mutableStateOf<Uri>(Uri.EMPTY)
    }

    val cameraLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.TakePicture()) {
        Log.d("CreateContactScreen", "CameraLauncher $it")
        capturedImageUri = uri
        createExpenseViewModel.onImageUriChange(capturedImageUri.toString())
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()) {

        if(it) {
            Toast.makeText(expense, "Permission granted", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(expense, "Permission denied", Toast.LENGTH_SHORT).show()
        }
    }

    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "New expense")
                },
                actions = {
                    Button(onClick = {
                        runBlocking {
                            createExpenseViewModel.createExpense()
                            navController.popBackStack()
                        }
                    }) {
                        Text(text = "Save")
                    }
                }
            )
        }
    ) {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ){
            
            Text(text = "Name of expense")
            Spacer(modifier = Modifier.width(16.dp))
            TextInput(
                value = createExpenseViewModel.name,
                onChange = {
                        value ->
                    createExpenseViewModel.onNameChange(value)
                    // CreateExpenseViewModel.onNameChange(it)
                },
                placeholder = "Enter expense name")

            Spacer(modifier = Modifier.width(16.dp))
            Spacer(modifier = Modifier.height(25.dp))

            Text(text = "Money spent")
            Spacer(modifier = Modifier.width(16.dp))

            val doublePattern = remember { Regex("^\\d*\\.?\\d*\$") }
            var text by remember { mutableStateOf(createExpenseViewModel.amount) }

            TextInput(
                value = text,
                onChange = { value ->
                    if (value.isEmpty() || value.matches(doublePattern)) {
                        text = value
                        createExpenseViewModel.onAmountChange(value)
                    }
                },
                placeholder = "Enter amount of money"

            )

            Spacer(modifier = Modifier.width(16.dp))
            Spacer(modifier = Modifier.height(25.dp))

            Text(text = "Choose category")
            Spacer(modifier = Modifier.width(16.dp))
            // category dropdown goes here
            DropdownMenuExample(createExpenseViewModel)

            Spacer(modifier = Modifier.width(16.dp))
            Spacer(modifier = Modifier.height(25.dp))

            showDatePicker(context, createExpenseViewModel = createExpenseViewModel)

            Spacer(modifier = Modifier.width(16.dp))
            Spacer(modifier = Modifier.height(25.dp))

            Button(onClick = {
                val permissionCheckResult = ContextCompat.checkSelfPermission(expense,
                    Manifest.permission.CAMERA)

                if (permissionCheckResult == PackageManager.PERMISSION_GRANTED){
                    cameraLauncher.launch(uri)
                } else {
                    permissionLauncher.launch(Manifest.permission.CAMERA)
                }
            }) {
                Text(text = "Add image")

            }

            Spacer(modifier = Modifier.width(16.dp))

            if (capturedImageUri.path?.isNotEmpty() == true) {
                Image(
                    modifier = Modifier.padding(16.dp, 16.dp),
                    painter = rememberImagePainter(capturedImageUri),
                    contentDescription = "Image")
            } else {
                Image(
                    modifier = Modifier.padding(16.dp, 16.dp),
                    painter = painterResource(id = R.drawable.ic_image),
                    contentDescription = "Image")
            }













        }
    }
}


fun Context.createImageFile(): File {
    val timeStamp = SimpleDateFormat("yyyy_MM_dd_HH-mm-ss").format(Date())
    val imageFileName = "image" + timeStamp + "_"
    return File.createTempFile(
        imageFileName,
        ".jpg",
        externalCacheDir
    )
}