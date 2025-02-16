# Credit Simulator

Simulasi kredit kendaraan berdasarkan input seperti jenis kendaraan, kondisi kendaraan, tahun, jumlah pinjaman, tenor, dan uang muka.

---

## 🚀 Cara Menjalankan Secara Lokal

1. **Clone Repository**  
   Jalankan perintah berikut:
   ```bash
   git clone https://github.com/agung866/credit_simulator.git
   ```

2. **Buka di Code Editor**  
   Buka project di editor favorit Anda (contoh: IntelliJ IDEA).

3. **Build Aplikasi**  
   Jalankan perintah Maven berikut:
   ```bash
   mvn clean package
   ```

4. **Jalankan Aplikasi Tanpa Docker**
   - **Tanpa file input**:
     ```bash
     ./bin/credit_simulator
     ```

   - **Dengan file input**:
     ```bash
     ./bin/credit_simulator file_input.txt
     ```

   - **Menggunakan Java**:
     ```bash
     java -jar target/credit_simulator-1.0-SNAPSHOT.jar
     ```

   - **Dengan file input menggunakan Java**:
     ```bash
     java -jar target/credit_simulator-1.0-SNAPSHOT.jar file_input.txt
     ```

   **📌 Catatan:**  
   Anda bisa mengubah isi file input dengan format:  
   `vehicleType|vehicleCondition|yearOfVehicle|loanAmount|tenor|downPayment`

---

## 🐋 Cara Menjalankan Menggunakan Docker

1. **Clone Repository**  
   Jalankan perintah berikut:
   ```bash
   git clone https://github.com/agung866/credit_simulator.git
   ```

2. **Buka di Code Editor**  
   Buka project di editor favorit Anda (contoh: IntelliJ IDEA).

3. **Build Aplikasi**  
   Jalankan perintah berikut:
   ```bash
   mvn clean package
   ```

4. **Build Docker Image**  
   Jalankan perintah:
   ```bash
   docker build -t credit_simulator:1.0 .
   ```

5. **Jalankan Aplikasi dengan Docker**
   - **Tanpa file input**:
     ```bash
     docker run -it --rm credit_simulator:1.0
     ```

   - **Dengan file input**:
     ```bash
     docker run -it --rm -v "$(pwd)/file_input.txt:/app/file_input.txt" credit_simulator:1.0 file_input.txt
     ```

   **📌 Catatan:**  
   Format file input:  
   `vehicleType|vehicleCondition|yearOfVehicle|loanAmount|tenor|downPayment`

---

## 🛠️ Pembuatan Mock API

Gunakan [Mocky API Designer](https://designer.mocky.io/design) untuk membuat mock API.  
**📌 Catatan:** Service ini belum bisa menerima data lebih dari satu, baik melalui API call maupun file input.

---

## 📋 Contoh Data JSON yang Digunakan

```json
{
  "vehicleType": "Mobil",
  "vehicleCondition": "Bekas",
  "yearOfVehicle": 2023,
  "loanAmount": 130000000,
  "tenor": 6,
  "downPayment": 35
}
```

---

## 📦 Script Eksekusi Docker

- **Tanpa file input**:
  ```bash
  docker run -it --rm credit_simulator:1.0
  ```

- **Dengan file input**:
  ```bash
  docker run -it --rm -v "$(pwd)/file_input.txt:/app/file_input.txt" credit_simulator:1.0 file_input.txt
  ```

---

## 📄 Script Eksekusi Tanpa Docker

1. **Tanpa file input**:
   ```bash
   ./bin/credit_simulator
   ```

2. **Dengan file input**:
   ```bash
   ./bin/credit_simulator file_input.txt
   ```

3. **Menggunakan Java tanpa file input**:
   ```bash
   java -jar target/credit_simulator-1.0-SNAPSHOT.jar
   ```

4. **Menggunakan Java dengan file input**:
   ```bash
   java -jar target/credit_simulator-1.0-SNAPSHOT.jar file_input.txt
   ```

---

🎉 **Terima kasih telah menggunakan Credit Simulator!** Jika ada pertanyaan lebih lanjut, silakan hubungi pengembang atau lihat dokumentasi tambahan.
