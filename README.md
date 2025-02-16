cara untuk running local :
1. clone dulu file https://github.com/agung866/credit_simulator.git
2. setelah itu buka di Code Editor (disini saya pakai intellij)
3. lalu lakukan perintah mvn clean package
4. kemudian setelah itu execute dengan script di bawah without docker
NOTES : anda bisa ubah filenya dengan urutan vehicleType|vehicleCondition|yearOfVehicle|loanAmount|tenor|downPayment

cara untuk running di Docker :
1. clone dulu file https://github.com/agung866/credit_simulator.git
2. setelah itu buka di Code Editor (disini saya pakai intellij)
3. lalu lakukan perintah mvn clean package
4. execute script ini di terminal docker build -t credit_simulator:1.0 .
5. kemudian setelah itu execute dengan script di bawah run docker
   NOTES : anda bisa ubah filenya dengan urutan vehicleType|vehicleCondition|yearOfVehicle|loanAmount|tenor|downPayment

pembuatan mock api : https://designer.mocky.io/design
NOTES : service ini belum bisa menerima data lebih dari 1 baik lewat call api atau lewat file input

contoh isi json yang digunakan : 
{
"vehicleType":"Mobil",
"vehicleCondition":"Bekas",
"yearOfVehicle":2023,
"loanAmount":130000000,
"tenor":6,
"downPayment":35
}

script run docker: 
1. docker run -it --rm credit_simulator:1.0
2. docker run -it --rm -v "$(pwd)/file_input.txt:/app/file_input.txt" credit_simulator:1.0 file_input.txt

script run without docker :
1. ./bin/credit_simulator
2. ./bin/credit_simulator file_input.txt
3. java -jar target/credit_simulator-1.0-SNAPSHOT.jar
4. java -jar target/credit_simulator-1.0-SNAPSHOT.jar file_input.txt 
