package com.example.tuan03_bai01.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tuan03_bai01.R
import com.example.tuan03_bai01.navigation.Screen
import com.example.tuan03_bai01.ui.theme.poppinsFont



@Composable
fun WelcomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp), // Tạo khoảng cách với viền màn hình
        verticalArrangement = Arrangement.SpaceBetween, // Căn cách đều trên - giữa - dưới
        horizontalAlignment = Alignment.CenterHorizontally // Căn giữa nội dung theo chiều ngang
    ) {
        Spacer(modifier = Modifier.height(100.dp)) // Khoảng cách trên cùng

        // 🖼️ Ảnh logo
        Image(
            painter = painterResource(id = R.drawable.ic_jetpack_compose),
            contentDescription = "Jetpack Compose Logo",
            modifier = Modifier.size(250.dp) // Điều chỉnh kích thước ảnh
        )

        Spacer(modifier = Modifier.height(60.dp))

        // 📝 Tiêu đề
        Text(
            text = "Jetpack Compose",
            fontFamily = poppinsFont,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(20.dp))

        // 📜 Mô tả
        Text(
            text = "Jetpack Compose is a modern UI toolkit for building native Android applications using a declarative programming approach.",
            fontFamily = poppinsFont,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp, // 🔥 Chỉnh về 14px (~ 14.sp)
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.weight(1f)) // Đẩy nút xuống dưới

        // 🔵 Nút "I'm ready"
        Button(
            onClick = { navController.navigate(Screen.ComponentsList.route) },
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(bottom = 32.dp), // Cách mép dưới 32.dp để đẹp hơn
            shape = RoundedCornerShape(50), // Bo góc
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3)) // Màu xanh
        ) {
            Text(text = "I'm ready",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }
    }
}
