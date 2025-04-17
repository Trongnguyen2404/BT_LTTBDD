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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RowDemo() {
    Column(modifier = Modifier.padding(16.dp).fillMaxWidth()) { // Column bao ngoài để xếp chồng các ví dụ Row

        // --- Ví dụ 1: Row Cơ bản ---
        Text("Basic Row", style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(bottom = 8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray.copy(alpha = 0.3f)) // Nền để thấy rõ phạm vi
                .padding(8.dp)
        ) {
            Text("Left")
            Spacer(modifier = Modifier.width(8.dp)) // Thêm khoảng cách nhỏ
            Text("Center")
            Spacer(modifier = Modifier.width(8.dp))
            Text("Right")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Divider()
        Spacer(modifier = Modifier.height(16.dp))

        // --- Ví dụ 2: Row với Horizontal Arrangement ---
        Text("Row with Horizontal Arrangement", style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(bottom = 8.dp))
        Text("Arrangement.SpaceEvenly:", fontSize = 12.sp, fontWeight = FontWeight.Bold)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp) // Chiều cao để thấy rõ alignment dọc nếu có
                .background(Color.Cyan.copy(alpha = 0.3f))
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly // Cách đều các item, cả khoảng trống đầu/cuối
        ) {
            Text("A")
            Text("B")
            Text("C")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text("Arrangement.SpaceBetween:", fontSize = 12.sp, fontWeight = FontWeight.Bold)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(Color.Magenta.copy(alpha = 0.3f))
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween // Item đầu ở trái, cuối ở phải, còn lại cách đều
        ) {
            Text("Start")
            Text("Mid")
            Text("End")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text("Arrangement.spacedBy(20.dp):", fontSize = 12.sp, fontWeight = FontWeight.Bold)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(Color.Yellow.copy(alpha = 0.3f))
                .padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp) // Khoảng cách cố định giữa các item
        ) {
            Text("One")
            Text("Two")
            Text("Three")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Divider()
        Spacer(modifier = Modifier.height(16.dp))

        // --- Ví dụ 3: Row với Vertical Alignment ---
        Text("Row with Vertical Alignment", style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(bottom = 8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp) // Cần chiều cao để thấy rõ alignment dọc
                .background(Color.Green.copy(alpha = 0.2f))
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically // Căn giữa các item theo chiều dọc
        ) {
            Text("Tall Text\nLine 2", fontSize = 20.sp) // Item cao hơn
            Spacer(modifier = Modifier.width(8.dp))
            Text("Short") // Item thấp hơn
            Spacer(modifier = Modifier.width(8.dp))
            Box(modifier = Modifier.size(30.dp).background(Color.Red)) // Box để thấy rõ hơn
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.Blue.copy(alpha = 0.2f))
                .padding(8.dp),
            verticalAlignment = Alignment.Bottom // Căn đáy các item theo chiều dọc
        ) {
            Text("Tall Text\nLine 2", fontSize = 20.sp)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Short (Bottom)")
            Spacer(modifier = Modifier.width(8.dp))
            Box(modifier = Modifier.size(30.dp).background(Color.Red))
        }
    }
}

@Preview(showBackground = true, widthDp = 360) // Preview với chiều rộng cụ thể
@Composable
fun RowDemoPreview() {
    MaterialTheme {
        RowDemo()
    }
}