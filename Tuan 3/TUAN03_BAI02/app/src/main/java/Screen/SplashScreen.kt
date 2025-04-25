package com.example.smarttasks

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.btvn02_t3_jetpackcompose_letrieuduy.ui.theme.BTVN02_T3_JETPACKCOMPOSE_LETRIEUDUYTheme

@Composable
fun SplashScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Logo UTH
            Image(
                painter = painterResource(id = R.drawable.imageuth),
                contentDescription = "UTH Logo",
                modifier = Modifier
                    .width(150.dp)
                    .height(150.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Tên ứng dụng
            Text(
                text = "UTH SmartTasks",
                color = Color(0xFF0066CC),
                fontSize = 28.sp,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.ExtraBold
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    BTVN02_T3_JETPACKCOMPOSE_LETRIEUDUYTheme {
        SplashScreen()
    }
}