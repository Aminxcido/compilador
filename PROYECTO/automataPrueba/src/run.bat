@echo off
setlocal enabledelayedexpansion

REM Parámetro de entrada: archivo .mt
if "%~1"=="" (
    echo Uso: run.bat archivo.mt
    exit /b 1
)

set archivo=%~1

REM 1️⃣ Compilar mateamina
javac mateamina.java
if errorlevel 1 (
    echo Error al compilar mateamina.java
    exit /b 1
)

REM 2️⃣ Ejecutar mateamina con el archivo dado
java mateamina %archivo% > resultado.txt

REM 3️⃣ Verificar que fue aceptada
findstr "Cadena aceptada." resultado.txt >nul
if errorlevel 1 (
    echo La expresion NO fue aceptada. No se generara reporte.
    exit /b 1
)

REM 4️⃣ Verificar errores semánticos de forma correcta
findstr "No se encontraron errores semanticos" resultado.txt >nul
if errorlevel 1 (
    echo Se encontraron errores semanticos. No se generara reporte.
    exit /b 1
)

REM 5️⃣ Compilar y ejecutar GeneradorReporte.java
javac GeneradorReporte.java
if errorlevel 1 (
    echo Error al compilar GeneradorReporte.java
    exit /b 1
)

REM Le pasamos resultado.txt al generador
java GeneradorReporte resultado.txt %archivo%
REM 6️⃣ Abrir el html en el navegador
start resultado.html

echo  Proceso finalizado correctamente.
pause
