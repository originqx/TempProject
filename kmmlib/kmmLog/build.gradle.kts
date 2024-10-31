plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinCocoapods)
    alias(libs.plugins.androidLibrary)
    id("maven-publish")
}

group = "com.zyb"
version = "5.0.2"

publishing {
    repositories {
//        google()
//        mavenCentral()
        mavenLocal()
    }
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
        publishLibraryVariants("release", "debug")
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "12.0"
        name = "MyCocoaPodLog"
        framework {
            baseName = "MyFrameworkLog"
            isStatic = true
//            export(project(":shared"))
        }
    }
    
    sourceSets {
        androidMain.get().dependencies{
            // Retrofit 依赖
            implementation(libs.retrofit)

            // 可选: 如果需要 Gson 转换器
            implementation(libs.converter.gson)

            // 可选: 如果使用 OkHttp 作为底层 HTTP 客户端
            implementation(libs.okhttp)
            implementation(libs.logging.interceptor)
        }

        commonMain.dependencies {
            //put your multiplatform dependencies here
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "com.zyb.kmmlib"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}