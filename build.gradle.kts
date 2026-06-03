plugins {
	java
	id("org.springframework.boot") version "3.4.6"
	id("io.spring.dependency-management") version "1.1.7"
	id("com.diffplug.spotless") version "6.25.0"
}

group = "com.inhatc"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.14")
	implementation("org.jsoup:jsoup:1.17.2")
	runtimeOnly("org.mariadb.jdbc:mariadb-java-client")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("com.squareup.okhttp3:mockwebserver:4.12.0")
	testImplementation("com.squareup.okhttp3:okhttp:4.12.0")
	testRuntimeOnly("com.h2database:h2")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

spotless {
	java {
		target("src/*/java/**/*.java")
		googleJavaFormat()
		removeUnusedImports()
		trimTrailingWhitespace()
		endWithNewline()
	}
	format("misc") {
		target("*.gradle.kts", ".gitignore", "*.md", ".env.example")
		trimTrailingWhitespace()
		endWithNewline()
	}
}
