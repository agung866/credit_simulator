@echo off

REM Jika ada argumen file input, jalankan dengan file input
if "%1"=="" (
    java -jar target\credit_simulator-1.0-SNAPSHOT.jar
) else (
    java -jar target\credit_simulator-1.0-SNAPSHOT.jar %1
)