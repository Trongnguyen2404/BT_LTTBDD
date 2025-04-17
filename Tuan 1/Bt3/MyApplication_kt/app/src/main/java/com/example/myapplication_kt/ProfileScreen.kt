package com.example.myapplication_kt // Khai báo package của ứng dụng

// Import các thư viện cần thiết từ Jetpack Compose
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication_kt.R // Import tài nguyên trong ứng dụng

/**
 * Composable tạo nút Icon có viền bo góc.
 */
@Composable
fun RoundedCornerIconButton(
    modifier: Modifier = Modifier, // Modifier giúp tùy chỉnh vị trí, kích thước của nút
    onClick: () -> Unit, // Lambda function xử lý sự kiện khi nút được nhấn
    imageVector: ImageVector, // Biểu tượng icon hiển thị trên nút
    contentDescription: String // Mô tả icon giúp hỗ trợ truy cập (accessibility)
) {
    Surface(
        modifier = modifier.size(44.dp), // Thiết lập kích thước của nút là 44x44 dp
        shape = RoundedCornerShape(12.dp), // Định dạng viền bo góc với bán kính 12dp
        color = Color.White, // Màu nền của nút là trắng
        border = BorderStroke(1.dp, Color.LightGray) // Viền mỏng 1dp màu xám nhạt
    ) {
        IconButton(onClick = onClick) { // Nút có thể nhấn được
            Icon(
                imageVector = imageVector, // Icon truyền vào từ tham số
                contentDescription = contentDescription, // Mô tả nội dung của icon
                tint = Color.Black // Màu của icon là đen
            )
        }
    }
}

/**
 * Composable tạo màn hình hồ sơ cá nhân.
 */
@Composable
fun ProfileScreen() {
    Box( // Container chính chứa toàn bộ màn hình
        modifier = Modifier
            .fillMaxSize() // Chiếm toàn bộ kích thước màn hình
            .background(Color(0xFFF2F2F2)) // Thiết lập màu nền xám nhẹ
    ) {
        // Nút Quay Lại (góc trên bên trái)
        RoundedCornerIconButton(
            modifier = Modifier
                .align(Alignment.TopStart) // Căn nút về phía trên cùng bên trái
                .padding(16.dp), // Khoảng cách từ viền màn hình là 16dp
            onClick = { /* TODO: Xử lý sự kiện quay lại */ },
            imageVector = Icons.AutoMirrored.Filled.ArrowBack, // Sử dụng icon quay lại
            contentDescription = "Back" // Mô tả icon quay lại
        )

        // Nút Chỉnh Sửa (góc trên bên phải)
        RoundedCornerIconButton(
            modifier = Modifier
                .align(Alignment.TopEnd) // Căn nút về phía trên cùng bên phải
                .padding(16.dp), // Khoảng cách từ viền màn hình là 16dp
            onClick = { /* TODO: Xử lý sự kiện chỉnh sửa */ },
            imageVector = Icons.Default.Edit, // Sử dụng icon chỉnh sửa
            contentDescription = "Edit" // Mô tả icon chỉnh sửa
        )

        // Cột chứa nội dung chính (Ảnh đại diện + Thông tin)
        Column(
            modifier = Modifier
                .fillMaxSize() // Chiếm toàn bộ kích thước màn hình
                .padding(horizontal = 32.dp), // Cách lề trái và phải 32dp
            horizontalAlignment = Alignment.CenterHorizontally, // Căn giữa theo chiều ngang
            verticalArrangement = Arrangement.Center // Căn giữa theo chiều dọc
        ) {
            // Ảnh đại diện (vuông bo tròn 16dp)
            Image(
                painter = painterResource(id = R.drawable.profile_image), // Ảnh đại diện từ tài nguyên
                contentDescription = "Profile Picture", // Mô tả ảnh giúp hỗ trợ truy cập
                modifier = Modifier
                    .size(120.dp) // Kích thước ảnh là 120x120 dp
                    .clip(CircleShape) // Cắt ảnh thành hình tròn
            )

            // Tên người dùng
            Text(
                text = "Johan Smith", // Hiển thị tên người dùng
                fontSize = 22.sp, // Cỡ chữ 22sp
                fontWeight = FontWeight.Bold, // Định dạng chữ đậm
                color = Color.Black, // Màu chữ đen
                modifier = Modifier.padding(top = 8.dp) // Cách ảnh đại diện 8dp
            )

            // Địa điểm người dùng
            Text(
                text = "California, USA", // Hiển thị địa điểm của người dùng
                fontSize = 18.sp, // Cỡ chữ 18sp
                color = Color.Gray // Màu chữ xám nhạt
            )
        }
    }
}
