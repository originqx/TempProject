plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinCocoapods)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinSerialization)
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

    listOf(iosX64(), iosArm64(), iosSimulatorArm64()).forEach { target ->
        target.compilations.getByName("main") {
            val myCinterop by cinterops.creating {
                // 指定 .def 文件的路径
                defFile("$projectDir/src/nativeInterop/cinterop/myCinterop.def")
                // 指定头文件所在的目录
                includeDirs("$projectDir/src/nativeInterop/cinterop/header")
            }
        }
    }

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "12.0"
        name = "MyCocoaPod"
        framework {
            baseName = "MyFramework"
            isStatic = true
            export(project(":kmmLog"))
        }
        pod("AFNetworking", "4.0.1")
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
            implementation(libs.ktor.client.okhttp)
        }


        val commonMain by getting {
            dependencies {
                //put your multiplatform dependencies here
                // Ktor 的核心库和客户端引擎
                implementation(libs.ktor.client.core)
                implementation(libs.ktor.client.json)
                implementation(libs.ktor.client.logging)
                implementation(libs.kotlinx.serialization.json)

                implementation(libs.ktor.client.content.negotiation)
                implementation(libs.ktor.serialization.kotlinx.json)

                api(project(":kmmLog"))

            }
        }

        targets.all {
            compilations.all {
                kotlinOptions {
                    freeCompilerArgs += "-Xexpect-actual-classes"
                }
            }
        }


        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
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