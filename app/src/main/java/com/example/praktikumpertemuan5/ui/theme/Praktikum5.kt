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
                        Text(
                            text = stringResource(R.string.form_title),
                            color = colorResource(R.color.title_purple),
                            fontSize = dimensionResource(R.dimen.text_title).value.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                        Spacer(Modifier.height(dimensionResource(R.dimen.spacing_small)))
                        Text(
                            text = stringResource(R.string.form_subtitle),
                            color = colorResource(R.color.subtitle_gray),
                            fontSize = dimensionResource(R.dimen.text_subtitle).value.sp,
                            textAlign = TextAlign.Center
                        )
                        Spacer(Modifier.height(dimensionResource(R.dimen.spacing_large)))

                        // Nama depan & belakang
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.spacing_medium))
                        ) {
                            FormField(
                                value = firstName,
                                onValueChange = { firstName = it },
                                hint = stringResource(R.string.hint_first_name),
                                modifier = Modifier.weight(1f)
                            )
                            FormField(
                                value = lastName,
                                onValueChange = { lastName = it },
                                hint = stringResource(R.string.hint_last_name),
                                modifier = Modifier.weight(1f)
                            )
                        }
                        Spacer(Modifier.height(dimensionResource(R.dimen.spacing_medium)))

                        // Email
                        FormField(
                            value = email,
                            onValueChange = { email = it },
                            hint = stringResource(R.string.hint_email),
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(Modifier.height(dimensionResource(R.dimen.spacing_medium)))

                        // Tanggal lahir & experience
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.spacing_medium))
                        ) {
                            BirthDateField(
                                value = birthDate,
                                onDateSelected = { birthDate = it },
                                modifier = Modifier.weight(1f)
                            )
                            ExperienceDropdown(
                                value = experience,
                                onSelected = { experience = it },
                                modifier = Modifier.weight(1f)
                            )
                        }
                        Spacer(Modifier.height(dimensionResource(R.dimen.spacing_large)))

                        // Skill
                        Text(
                            text = stringResource(R.string.skill_title),
                            color = colorResource(R.color.label_text),
                            fontSize = dimensionResource(R.dimen.text_label).value.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            SkillCheckbox(
                                label = stringResource(R.string.skill_android),
                                checked = skillAndroid,
                                onCheckedChange = { skillAndroid = it }
                            )
                            SkillCheckbox(
                                label = stringResource(R.string.skill_uiux),
                                checked = skillUiUx,
                                onCheckedChange = { skillUiUx = it }
                            )
                            SkillCheckbox(
                                label = stringResource(R.string.skill_web),
                                checked = skillWeb,
                                onCheckedChange = { skillWeb = it }
                            )
                        }
                        Spacer(Modifier.height(dimensionResource(R.dimen.spacing_large)))

                        // Tombol Proceed
                        Button(
                            onClick = { showResult = true },
                            enabled = isFormValid,
                            shape = RoundedCornerShape(dimensionResource(R.dimen.button_corner)),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = colorResource(R.color.button_enabled),
                                contentColor = colorResource(R.color.button_text),
                                disabledContainerColor = colorResource(R.color.button_disabled),
                                disabledContentColor = colorResource(R.color.button_text)
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(dimensionResource(R.dimen.button_height))
                        ) {
                            Text(
                                text = stringResource(R.string.button_proceed),
                                fontSize = dimensionResource(R.dimen.text_button).value.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

            }
        }

        // Popup hitam: Box di luar Scaffold, mengapung di atasnya
        if (showResult) {
            val skillText = buildList {
                if (skillAndroid) add(stringResource(R.string.skill_android))
                if (skillUiUx) add(stringResource(R.string.skill_uiux))
                if (skillWeb) add(stringResource(R.string.skill_web))
            }.joinToString(stringResource(R.string.skill_separator))
            val popupShape = RoundedCornerShape(dimensionResource(R.dimen.popup_corner))
            val popupTextSize = dimensionResource(R.dimen.text_popup).value.sp
            val popupTextColor = colorResource(R.color.popup_text)


