package com.example.tuan03_bai01.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tuan03_bai01.data.model.UIComponent
import com.example.tuan03_bai01.data.repository.UIComponentRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * Data class đại diện cho trạng thái giao diện người dùng của màn hình ComponentsListScreen.
 * @param groupedComponents Danh sách các component đã được nhóm theo category.
 * @param isLoading Trạng thái cho biết dữ liệu đang được tải hay không.
 * @param error Thông báo lỗi nếu có.
 */
data class ComponentsListUiState(
    val groupedComponents: List<Pair<String, List<UIComponent>>> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

/**
 * ViewModel cho màn hình ComponentsListScreen.
 * Chịu trách nhiệm lấy danh sách các UI component từ Repository
 * và cung cấp chúng cho View thông qua ComponentsListUiState.
 */
class ComponentsListViewModel : ViewModel() {

    // StateFlow riêng tư, chỉ có thể thay đổi bên trong ViewModel.
    private val _uiState = MutableStateFlow(ComponentsListUiState())
    // StateFlow công khai, chỉ đọc, để View có thể lắng nghe.
    val uiState: StateFlow<ComponentsListUiState> = _uiState.asStateFlow()

    // Khối init được gọi tự động khi ViewModel được tạo lần đầu.
    init {
        loadComponents()
    }

    /**
     * Tải danh sách các component từ Repository.
     * Cập nhật UiState tương ứng (loading, success, error).
     */
    private fun loadComponents() {
        // 1. Cập nhật trạng thái: Đang tải
        _uiState.update { it.copy(isLoading = true) }

        // 2. Sử dụng viewModelScope để chạy coroutine.
        // Coroutine này sẽ tự động hủy khi ViewModel bị hủy, tránh memory leak.
        viewModelScope.launch {
            try {
                // 3. Lấy dữ liệu từ Repository (hiện tại là đồng bộ, nhưng cấu trúc này hoạt động tốt với bất đồng bộ)
                // Giả lập độ trễ nếu cần để thấy trạng thái loading rõ hơn: kotlinx.coroutines.delay(1000)
                val components = UIComponentRepository.groupedComponents

                // 4. Cập nhật trạng thái: Tải thành công
                _uiState.update { currentState ->
                    currentState.copy(
                        isLoading = false,
                        groupedComponents = components,
                        error = null // Xóa lỗi cũ nếu có
                    )
                }
            } catch (e: Exception) {
                // 5. Cập nhật trạng thái: Có lỗi xảy ra
                _uiState.update { currentState ->
                    currentState.copy(
                        isLoading = false,
                        error = "Failed to load components: ${e.message}"
                    )
                }
            }
        }
    }

    // Có thể thêm các hàm khác ở đây để xử lý sự kiện người dùng nếu cần
    // ví dụ: fun onComponentClicked(componentName: String) { ... }
    // nhưng trong trường hợp này, việc điều hướng đơn giản có thể xử lý ở View.
}