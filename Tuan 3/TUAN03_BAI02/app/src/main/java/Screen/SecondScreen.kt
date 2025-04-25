package com.example.smarttasks

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.btvn02_t3_jetpackcompose_letrieuduy.ui.theme.BTVN02_T3_JETPACKCOMPOSE_LETRIEUDUYTheme

@Composable
fun GetStartedSecondScreen(onBack: () -> Unit, onNext: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Chỉ báo trang
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                horizontalArrangement = Arrangement.Start
            ) {
                Indicator(isSelected = false)
                Spacer(modifier = Modifier.width(8.dp))
                Indicator(isSelected = true)
                Spacer(modifier = Modifier.width(8.dp))
                Indicator(isSelected = false)
            }

            TextButton(onClick = onNext) {
                Text(
                    text = "skip",
                    color = Color(0xFF0066CC)
                )
            }
        }

        // Nội dung chính
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Hình minh họa
                Image(
                    painter = painterResource(id = R.drawable.hinh2),
                    contentDescription = "Work Effectiveness",
                    modifier = Modifier
                        .height(200.dp)
                        .fillMaxWidth(),
                    contentScale = ContentScale.Fit
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Tiêu đề
                Text(
                    text = "Increase Work Effectiveness",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Mô tả
                Text(
                    text = "Time management and the determination of more important tasks will give your job statistics better and always improve",
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }

        // Navigation buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Back button
            Button(
                onClick = onBack,
                modifier = Modifier
                    .size(38.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF32B2FC)
                ),
                shape = CircleShape,
                contentPadding = PaddingValues(12.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "Back",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }

            // Next button
            Button(
                onClick = onNext,
                modifier = Modifier
                    .height(35.dp)
                    .widthIn(min = 240.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF32B2FC)
                ),
                shape = RoundedCornerShape(25.dp)
            ) {
                Text(
                    text = "Next",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GetStartedSecondScreenPreview() {
    BTVN02_T3_JETPACKCOMPOSE_LETRIEUDUYTheme {
        GetStartedSecondScreen({}, {})
    }
}