plugins {
    alias(libs.plugins.android.application) // Plugin để xây dựng ứng dụng Android
    alias(libs.plugins.kotlin.android) // Plugin hỗ trợ Kotlin trên Android
    alias(libs.plugins.kotlin.compose) // Plugin hỗ trợ Jetpack Compose
}

android {
    namespace = "com.example.myapplication_kt" // Không gian tên (namespace) của ứng dụng
    compileSdk = 35 // Phiên bản SDK cao nhất mà ứng dụng có thể sử dụng

    defaultConfig {
        applicationId = "com.example.myapplication_kt" // ID duy nhất của ứng dụng trên thiết bị
        minSdk = 35 // Phiên bản Android tối thiểu mà ứng dụng hỗ trợ
        targetSdk = 35 // Phiên bản Android mà ứng dụng được tối ưu hóa
        versionCode = 1 // Mã phiên bản ứng dụng (dùng để cập nhật trên CH Play)
        versionName = "1.0" // Tên phiên bản ứng dụng (hiển thị trên CH Play)

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner" // Runner cho kiểm thử UI
    }

    buildTypes {
        release {
            isMinifyEnabled = false // Không bật tính năng tối ưu mã (ProGuard) để giảm kích thước ứng dụng
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), // File cấu hình ProGuard mặc định
                "proguard-rules.pro" // File cấu hình ProGuard tùy chỉnh
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11 // Hỗ trợ Java 11
        targetCompatibility = JavaVersion.VERSION_11 // Nhắm đến Java 11
    }

    kotlinOptions {
        jvmTarget = "11" // Hỗ trợ Kotlin trên JVM 11
    }

    buildFeatures {
        compose = true // Kích hoạt Jetpack Compose
    }
}

dependencies {
    implementation(libs.androidx.core.ktx) // Thư viện lõi của Android Jetpack (viết bằng Kotlin)
    implementation(libs.androidx.lifecycle.runtime.ktx) // Hỗ trợ vòng đời (lifecycle) trong Kotlin
    implementation(libs.androidx.activity.compose) // Hỗ trợ Activity với Jetpack Compose
    implementation(platform(libs.androidx.compose.bom)) // Import Compose BOM (Bill of Materials)
    implementation(libs.androidx.ui) // Thư viện UI của Jetpack Compose
    implementation(libs.androidx.ui.graphics) // Thư viện đồ họa của Jetpack Compose
    implementation(libs.androidx.ui.tooling.preview) // Công cụ hỗ trợ xem trước UI Compose
    implementation(libs.androidx.material3) // Sử dụng Material Design 3
    testImplementation(libs.junit) // Thư viện kiểm thử JUnit
    androidTestImplementation(libs.androidx.junit) // Hỗ trợ kiểm thử trên Android với JUnit
    androidTestImplementation(libs.androidx.espresso.core) // Thư viện kiểm thử UI Espresso
    androidTestImplementation(platform(libs.androidx.compose.bom)) // BOM cho kiểm thử Jetpack Compose
    androidTestImplementation(libs.androidx.ui.test.junit4) // Kiểm thử giao diện với JUnit 4
    debugImplementation(libs.androidx.ui.tooling) // Công cụ hỗ trợ phát triển Compose (hiển thị UI trong Android Studio)
    debugImplementation(libs.androidx.ui.test.manifest) // Hỗ trợ kiểm thử giao diện
    implementation("androidx.compose.ui:ui:1.4.3") // Jetpack Compose UI phiên bản 1.4.3
    implementation("androidx.compose.material:material:1.4.3") // Material Design 1.4.3
    implementation("androidx.compose.ui:ui-tooling-preview:1.4.3") // Công cụ xem trước UI
    implementation("androidx.activity:activity-compose:1.7.0") // Hỗ trợ Activity với Compose
}
