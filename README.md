# TP2DPBO2024C2

## Saya Jidan Abdurahman Aufan NIM [2205422] mengerjakan soal Tugas Praktikum-2 dalam mata kuliah DPBO untuk keberkahanNya maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin 

### Melanjutkan dari Latihan Praktikum 5, tetapi sekarang mengambil data dari mysql

Note: Saya membuat user mysql baru untuk koneksinya, dengan nama 'dpbo' dan passwordnya 'pass'\
Langkah yang saya lakukan untuk membuat user baru:\
Masuk ke root mysql, lalu eksekusi \
CREATE USER 'dpbo'@'localhost';\
GRANT SELECT, INSERT, UPDATE, DELETE ON db_mahasiswa.* TO 'dpbo'@'localhost';\
ALTER USER 'dpbo'@'localhost' IDENTIFIED BY 'pass';

- Modifikasi database mysql contoh dari modul untuk menambah kolom fakultas (file .sql)
- Membuat kelas Database.java
  ![image](https://github.com/jidanSyn/TP2DPBO2024C2/assets/114399924/dcdf5900-bb99-44ea-a3a9-42190b54ef89)
  
  	- Mengganti fungsi insertUpdateDeleteQuery dengan prepareAndExecute agar lebih mudah menyusun string query dan anti SQL injection
  	  ![image](https://github.com/jidanSyn/TP2DPBO2024C2/assets/114399924/d59de4e0-80b0-4378-9283-90ec82807c0d)
  	  	- contoh penggunaan pada insert data di Menu.java
  	  	  ![image](https://github.com/jidanSyn/TP2DPBO2024C2/assets/114399924/649902b5-56c0-4af4-8a82-fa2bdc672b83)


     	- Menambahkan fungsi untuk mengecek apakah record data sudah ada berdasarkan nim
  	  ![image](https://github.com/jidanSyn/TP2DPBO2024C2/assets/114399924/96fd7e49-5bd6-425e-ab91-52b7f29456dd)
  	  
- Implementasi Manipulasi data di Menu.java  
	  
	- Pengecekan input user pada insert dan update apakah kosong, dan menampilkan popup bila kosong
	  ![image](https://github.com/jidanSyn/TP2DPBO2024C2/assets/114399924/2d6a3c37-4c19-4b44-a09b-b708a8ba8357)

	- Pengecekan input user pada insert dan update apakah nim sudah ada di dalam database
	  ![image](https://github.com/jidanSyn/TP2DPBO2024C2/assets/114399924/7bb5311a-da49-4dc7-8331-bded15e51336)
   	  Untuk update, dicek terlebih dahulu apakah nim diubah, hanya jika nim diubah, baru mengecek apaka nim baru sudah ada di database
   	  ![image](https://github.com/jidanSyn/TP2DPBO2024C2/assets/114399924/9cd9438a-dc03-4c86-aadd-3d13ee4141b4)
   



  


	
