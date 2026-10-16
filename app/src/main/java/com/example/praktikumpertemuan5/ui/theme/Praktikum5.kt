package com.example.praktikumpertemuan5.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import kotlinx.coroutines.delay
import com.example.praktikumpertemuan5.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BiodataScreen() {
    var firstName by rememberSaveable { mutableStateOf("") }
    var lastName by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var birthDate by rememberSaveable { mutableStateOf("") }
    var experience by rememberSaveable { mutableStateOf("") }
    var skillAndroid by rememberSaveable { mutableStateOf(false) }
    var skillUiUx by rememberSaveable { mutableStateOf(false) }
    var skillWeb by rememberSaveable { mutableStateOf(false) }
    var showResult by rememberSaveable { mutableStateOf(false) }
    val popupDuration = integerResource(R.integer.popup_duration_ms).toLong()

    // Popup otomatis hilang setelah durasi di resources (5 detik)
    LaunchedEffect(showResult) {
        if (showResult) {
            delay(popupDuration)
            showResult = false
        }
    }

    val isFormValid = firstName.isNotBlank() &&
            lastName.isNotBlank() &&
            email.isNotBlank() &&
            birthDate.isNotBlank() &&
            experience.isNotBlank() &&
            (skillAndroid || skillUiUx || skillWeb)

    Box(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            containerColor = colorResource(R.color.screen_background)
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                // Gambar header
                Image(
                    painter = painterResource(R.drawable.background_header),
                    contentDescription = stringResource(R.string.cd_background),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(dimensionResource(R.dimen.header_height))
                )

                // Box yang mengapung di atas Scaffold
                Surface(
                    shape = RoundedCornerShape(
                        topStart = dimensionResource(R.dimen.card_corner),
                        topEnd = dimensionResource(R.dimen.card_corner)
                    ),
                    color = colorResource(R.color.card_background),
                    modifier = Modifier
                        .padding(
                            top = dimensionResource(R.dimen.card_top_offset),
                            bottom = dimensionResource(R.dimen.card_bottom_margin)
                        )
                        .fillMaxWidth()
                        .shadow(
                            elevation = dimensionResource(R.dimen.card_elevation),
                            shape = RoundedCornerShape(
                                topStart = dimensionResource(R.dimen.card_corner),
                                topEnd = dimensionResource(R.dimen.card_corner)
                            )
                        )
                ) {
                    Column(
                        modifier = Modifier
                            .verticalScroll(rememberScrollState())
                            .padding(dimensionResource(R.dimen.card_padding)),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

}
