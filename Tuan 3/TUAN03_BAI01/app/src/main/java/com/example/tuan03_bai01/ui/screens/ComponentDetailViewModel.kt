package com.example.tuan03_bai01.ui.screens

import androidx.lifecycle.SavedStateHandle // Để lấy tham số từ navigation
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tuan03_bai01.data.repository.UIComponentRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * Data class đại diện cho trạng thái giao diện người dùng của màn hình ComponentDetailScreen.
 * @param componentName Tên của component đang được hiển thị.
 * @param componentDescription Mô tả chi tiết của component.
 * @param isLoading Trạng thái cho biết dữ liệu đang được tải hay không.
 * @param error Thông báo lỗi nếu có.
 */
data class ComponentDetailUiState(
    val componentName: String = "Unknown",
    val componentDescription: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)

/**
 * ViewModel cho màn hình ComponentDetailScreen.
 * Chịu trách nhiệm lấy chi tiết (tên, mô tả) của một component cụ thể
 * dựa vào tham số `componentName` nhận được từ Navigation.
 * Cung cấp dữ liệu cho View thông qua ComponentDetailUiState.
 */
class ComponentDetailViewModel(
    // Inject SavedStateHandle để truy cập các arguments được truyền qua Navigation.
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow(ComponentDetailUiState())
    val uiState: StateFlow<ComponentDetailUiState> = _uiState.asStateFlow()

    // Lấy giá trị của argument "componentName" từ SavedStateHandle.
    // Tên "componentName" phải khớp với tên đã định nghĩa trong route của NavGraph.
    private val componentName: String = savedStateHandle.get<String>("componentName") ?: "Unknown"

    init {
        loadComponentDetails()
    }

    /**
     * Tải chi tiết của component (hiện tại chỉ là mô tả) từ Repository.
     * Cập nhật UiState tương ứng.
     */
    private fun loadComponentDetails() {
        // 1. Cập nhật trạng thái: Đang tải và đặt tên component ban đầu
        _uiState.update { it.copy(isLoading = true, componentName = this.componentName) }

        viewModelScope.launch {
            try {
                // 2. Lấy mô tả từ repository
                // Giả lập độ trễ: kotlinx.coroutines.delay(500)
                val description = UIComponentRepository.findDescriptionByName(componentName)

                // 3. Cập nhật trạng thái: Tải thành công
                _uiState.update { currentState ->
                    currentState.copy(
                        isLoading = false,
                        componentDescription = description,
                        error = null
                    )
                }
            } catch (e: Exception) {
                // 4. Cập nhật trạng thái: Có lỗi xảy ra
                _uiState.update { currentState ->
                    currentState.copy(
                        isLoading = false,
                        error = "Failed to load details for '$componentName': ${e.message}"
                    )
                }
            }
        }
    }
}