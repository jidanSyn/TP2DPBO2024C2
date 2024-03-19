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
	
