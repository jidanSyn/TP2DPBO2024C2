# TP2DPBO2024C2

## Saya Jidan Abdurahman Aufan NIM [2205422] mengerjakan soal Tugas Praktikum-2 dalam mata kuliah DPBO untuk keberkahanNya maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin 

### Melanjutkan dari Latihan Praktikum 5, tetapi sekarang mengambil data dari mysql

Note: Saya membuat user mysql baru untuk koneksinya, dengan nama 'dpbo' dan passwordnya 'pass'
Langkah yang saya lakukan untuk membuat user baru:
Masuk ke root mysql, lalu eksekusi 
CREATE USER 'dpbo'@'localhost';
GRANT SELECT, INSERT, UPDATE, DELETE ON db_mahasiswa.* TO 'dpbo'@'localhost';
ALTER USER 'dpbo'@'localhost' IDENTIFIED BY 'pass';

- Modifikasi database mysql contoh dari modul untuk menambah kolom fakultas (file .sql)
- Membuat kelas Database
  ![image](https://github.com/jidanSyn/TP2DPBO2024C2/assets/114399924/dcdf5900-bb99-44ea-a3a9-42190b54ef89)

  Selain fungsi database yang ada di dalam modul, saya menambahkan fungsi untuk mengecek apakah record data sudah ada berdasarkan nim
  ![image](https://github.com/jidanSyn/TP2DPBO2024C2/assets/114399924/96fd7e49-5bd6-425e-ab91-52b7f29456dd)
  
- Pengecekan input user pada insert dan update apakah kosong, dan menampilkan popup bila kosong
  ![image](https://github.com/jidanSyn/TP2DPBO2024C2/assets/114399924/2d6a3c37-4c19-4b44-a09b-b708a8ba8357)

- Pengecekan input user pada insert dan update apakah nim sudah ada di dalam database
  ![image](https://github.com/jidanSyn/TP2DPBO2024C2/assets/114399924/7bb5311a-da49-4dc7-8331-bded15e51336)


  


	
