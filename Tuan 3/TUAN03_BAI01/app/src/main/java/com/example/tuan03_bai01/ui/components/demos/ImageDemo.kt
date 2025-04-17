package com.example.tuan03_bai01.ui.components.demos

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.tuan03_bai01.R // Đảm bảo bạn đã import R đúng cách

@Composable
fun ImageDemo() {
    // 🔥 Quan trọng: Đảm bảo bạn có ảnh trong res/drawable
    // Ví dụ: ic_launcher_foreground (có sẵn) hoặc thêm ảnh của bạn (vd: placeholder_image.png)
    val imageResId = R.drawable.ic_launcher_foreground // Thay bằng ảnh của bạn nếu muốn

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally // Căn giữa các ảnh trong Column
    ) {
        // --- Ví dụ 1: Image Cơ bản ---
        Text(
            text = "Basic Image",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 8.dp).align(Alignment.Start) // Căn lề trái cho tiêu đề
        )
        Image(
            painter = painterResource(id = imageResId),
            contentDescription = "Basic Android Image", // Mô tả cho Accessibility
            modifier = Modifier
                .size(100.dp) // Kích thước cố định
                .padding(bottom = 16.dp)
        )
        Divider(modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(16.dp))

        // --- Ví dụ 2: Image với ContentScale ---
        Text(
            text = "Image with ContentScale.Crop",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 8.dp).align(Alignment.Start)
        )
        Image(
            painter = painterResource(id = imageResId),
            contentDescription = "Cropped Image",
            modifier = Modifier
                .size(150.dp, 100.dp) // Kích thước khác nhau
                .padding(bottom = 16.dp),
            contentScale = ContentScale.Crop // Cắt ảnh để lấp đầy không gian, giữ tỷ lệ
        )
        Divider(modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Image with ContentScale.Fit",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 8.dp).align(Alignment.Start)
        )
        Image(
            painter = painterResource(id = imageResId),
            contentDescription = "Fit Image",
            modifier = Modifier
                .size(150.dp, 100.dp) // Kích thước khác nhau
                .border(1.dp, Color.Gray) // Thêm viền để thấy rõ kích thước
                .padding(bottom = 16.dp),
            contentScale = ContentScale.Fit // Thu nhỏ/Phóng to ảnh để vừa không gian, giữ tỷ lệ
        )
        Divider(modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(16.dp))

        // --- Ví dụ 3: Image với Shape (Circle) ---
        Text(
            text = "Circular Image",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 8.dp).align(Alignment.Start)
        )
        Image(
            painter = painterResource(id = imageResId),
            contentDescription = "Circular Image",
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape) // Cắt ảnh thành hình tròn
                .border(2.dp, Color.Cyan, CircleShape) // Thêm viền tròn
                .padding(bottom = 16.dp),
            contentScale = ContentScale.Crop // Thường dùng Crop với ảnh tròn
        )
        Divider(modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(16.dp))

        // --- Ví dụ 4: Image với Rounded Corners ---
        Text(
            text = "Image with Rounded Corners",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 8.dp).align(Alignment.Start)
        )
        Image(
            painter = painterResource(id = imageResId),
            contentDescription = "Rounded Corner Image",
            modifier = Modifier
                .size(150.dp, 100.dp)
                .clip(RoundedCornerShape(16.dp)) // Bo góc
                .padding(bottom = 16.dp),
            contentScale = ContentScale.Crop
        )
    }
}