# Gunakan base image Java 17
FROM openjdk:17-jdk-slim

# Set working directory di dalam container
WORKDIR /app

# Copy file JAR ke dalam container
COPY target/credit_simulator-1.0-SNAPSHOT.jar /app/credit_simulator.jar

# Copy file input (opsional)
COPY file_input.txt /app/file_input.txt

# Buat direktori bin dan copy script
RUN mkdir -p /app/bin
COPY /bin/credit_simulator /app/bin/credit_simulator
COPY /bin/credit_simulator.bat /app/bin/credit_simulator.bat
RUN chmod +x /app/bin/credit_simulator

# Set environment variable untuk PATH
ENV PATH="/app/bin:${PATH}"

# Command default untuk menjalankan aplikasi
ENTRYPOINT ["java", "-jar", "credit_simulator.jar"]