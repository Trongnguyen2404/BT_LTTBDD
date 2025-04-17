package com.example.tuan03_bai01.ui.components.demos

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview // Thêm Preview để dễ xem
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ColumnDemo() {
    Column(modifier = Modifier.padding(16.dp)) {

        // --- Ví dụ 1: Column Cơ bản ---
        Text("Basic Column", style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(bottom = 8.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray.copy(alpha = 0.3f)) // Nền để thấy rõ phạm vi
                .padding(8.dp)
        ) {
            Text("Item 1")
            Text("Item 2")
            Text("Item 3 is a bit longer")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Divider()
        Spacer(modifier = Modifier.height(16.dp))

        // --- Ví dụ 2: Column với Vertical Arrangement ---
        Text("Column with Vertical Arrangement", style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(bottom = 8.dp))
        Text("Arrangement.SpaceEvenly:", fontSize = 12.sp, fontWeight = FontWeight.Bold)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp) // Cần chiều cao cố định để thấy rõ arrangement
                .background(Color.Cyan.copy(alpha = 0.3f))
                .padding(8.dp),
            verticalArrangement = Arrangement.SpaceEvenly // Cách đều các item, cả khoảng trống đầu/cuối
        ) {
            Text("Top")
            Text("Middle")
            Text("Bottom")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text("Arrangement.SpaceBetween:", fontSize = 12.sp, fontWeight = FontWeight.Bold)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .background(Color.Magenta.copy(alpha = 0.3f))
                .padding(8.dp),
            verticalArrangement = Arrangement.SpaceBetween // Item đầu ở trên, cuối ở dưới, còn lại cách đều
        ) {
            Text("First")
            Text("Second")
            Text("Third")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text("Arrangement.spacedBy(10.dp):", fontSize = 12.sp, fontWeight = FontWeight.Bold)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                // Không cần height cố định cho spacedBy
                .background(Color.Yellow.copy(alpha = 0.3f))
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp) // Khoảng cách cố định giữa các item
        ) {
            Text("One")
            Text("Two")
            Text("Three")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Divider()
        Spacer(modifier = Modifier.height(16.dp))

        // --- Ví dụ 3: Column với Horizontal Alignment ---
        Text("Column with Horizontal Alignment", style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(bottom = 8.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Green.copy(alpha = 0.2f))
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally // Căn giữa các item theo chiều ngang
        ) {
            Text("Centered Item 1")
            Text("Item 2 is longer and also centered")
            Text("Item 3")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Blue.copy(alpha = 0.2f))
                .padding(8.dp),
            horizontalAlignment = Alignment.End // Căn phải các item theo chiều ngang
        ) {
            Text("End Aligned 1")
            Text("Item 2")
            Text("Longer End Aligned Item 3")
        }
    }
}

// Thêm Preview để xem nhanh giao diện của Demo này trong Android Studio
@Preview(showBackground = true)
@Composable
fun ColumnDemoPreview() {
    MaterialTheme { // Cần MaterialTheme cho các style chữ
        ColumnDemo()
    }
}