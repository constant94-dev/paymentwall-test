plugins {
    java
    id("org.springframework.boot") version "2.7.7"
    id("io.spring.dependency-management") version "1.0.15.RELEASE"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.junit.jupiter:junit-jupiter:5.9.0")
    implementation("org.junit.jupiter:junit-jupiter-engine:5.9.0")
    implementation("org.junit.jupiter:junit-jupiter-api:5.9.0")
    // https://mvnrepository.com/artifact/com.paymentwall/paymentwall-java
    implementation("com.paymentwall:paymentwall-java:2.0.4")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

// Package as JAR
tasks.jar {
    manifest {
        attributes["Main-Class"] = "com.example.paymentwalltwo.PaymentwallTwoApplication"
    }
    configurations["compileClasspath"].forEach { file: File ->
        from(zipTree(file.absoluteFile))
    }
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE // 중복되는 파일을 제외해서 Jar 파일 패키징
    enabled = false // Jar task 를 통해 생성되는 plain archive SKIP 설정 값
}