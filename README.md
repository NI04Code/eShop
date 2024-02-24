# Eshop

---
#### Nama: Naufal Ichsan
#### NPM: 2206082013  
#### Kelas: Adpro A  
#### Link App: https://eshop-ni04code.koyeb.app/

---
<details>
<summary>TUTORIAL 1</summary>

## Refleksi 1

Setelah mengerjakan exercise 1 saya menjadi lebih paham mengenai fitur fitur 
yang terdapat pada Spring, saya jadi mengerti bagaimana melakukan mapping dan 
bagaimana pemodelan MVC pada spring bekerja, selain itu saya juga memelajari berbagai 
macam annotations seperti @ModelAttribute dan @PathVariable yang dapat digunakan untuk 
melakukan komunikasi antara controller dengan template html saya. Kesulitan saya terdapat pada
fitur edit product yang mana memerlukan saya untuk mendapatkan id ketika melakukan getMapping 
dan menyimpannya untuk dipakai di postMapping ketika user melakukan "save changes". Saya cukup 
kesulitan ketika memikirkan proses komunikasi antara repository, service, dan controller karena belum 
terbiasa.

Selain memahami bagaimana spring framework bekerja, saya juga menerapkan beberapa konsep clean code agar
dapat dipahami oleh saya dan programmer lainnya, beberapa hal yang saya terapkan adalah,
1. Penamaan variable yang representatif
2. Function yang memiliki nama representatif dan dibuat sesimple dan seminim mungkin namun tetap menjalankan tugasnya
3. Pemberian komen yang minim dan hanya untuk code yang kurang representatif
4. Error handling dengan menggunakan throw IllegalArgumentException ataupun return false

sementara untuk konsep Secure coding saya baru melakukan mapping dengan method post ketika user melakukan input pada create 
dan edit product.

## Refleksi 2
- Unit Test  
1. Dengan melakukan unit test saya lebih yakin terhadap flow dan algoritma pada code saya, membuat unit test sendiri menurut saya tidak 
terlalu sulit karena seperti melakukan input output validasi menggunakan Assertion.
2. Unit Test yang diperlukan dalam suatu code bergantung pada berapa banyak fitur atau method yang digunakan pada code tersebut, sehingga semakin 
banyak fiturnya semakin banyak unit test yang diperlukan, selain itu dalam unit test alangkah baiknya untuk membuat scenario positif dan negatif untuk memastikan tidak adanya error 
di code kita.
3. Meskipun code coverage kita 100%, itu tidak memastikan bahwa code kita bebas dari bug ataupun error. Sebagai contoh jika kita lupa untuk memasukkan test untuk
edge case maka meskipun code coverage kita 100% masih bisa ditemukan bug ataupun error yg terjadi.

- Functional Test  
Saya memelajari banyak hal ketika melakukan functional test, terutama terkait selenium webdriver untuk mengakses template html yang diinginkan melalui urlmapping yang telah ditentukan 
dan untuk menjawab pertanyaan pada modul saya rasa jika kita membuat class java baru dengan setup dan instance variable yang sama akan membuat code kita kurang clean karena itu sama saja dengan menduplikat code 
yang sudah ada, menurut saya salah satu cara agar code kita tetap clean, yaitu dengan membuat subclass/child class yang inherit functional test class yang sudah kita buat.

</details>
---

<details>
<summary>TUTORIAL 2</summary>  

## Refleksi 1
ada dua code quality issue yang saya fix diantaranya
1. Menambahkan deskripsi menggunakan caption pada product list table di template html
2. Menghapus access modifier public pada setiap class dan method unit test (menggunakan default saja yang sama sama public)

## Refleksi 2
Menurut saya implementasi projek saat ini sudah menerapkan CI/CD. Kita bisa lihat untuk CInya sendiri dari .yml file 
yang ada pada workflows github ci.yml akan melakukan test ketika melakukan push dan pull req di branch manapun sonarcloud.yml akan 
melakukan scanning codenya. Untuk scorecard sendiri di set untuk melakukan scanning hanya ketika push dan pull req dilakukan di branch main
Sementara untuk cdnya sendiri saya menggunakan PaaS Koyeb.com, setiap kali branch main mendapatkan push atau pull req maka app akan langsung mendeploy ulang 
ke versi branch main yang baru.

</details>

<details>
<summary>TUTORIAL 3</summary>

## Refleksi 1
SOLID Principle yang diimplementasi pada modul
1. **Single Responsibility Principle (SRP)**: Saya memisahkan antara carController denga  productController dikarenakan keduanya memiliki tanggung jawab terhadap objek yang berbeda.
2. **Open Closed Principle (OCP)**: Saya menggunakan interface ketika memanggil service pada controller, tujuannya adalah apabila saya ingin menambahkan suatu fitur baru saya tidak perlu mengubah controller ataupun service saya lagi, tetapi hanya perlu membuat interface baru yang nantinya dapat diimplement
3. **Liskov Substitution Principle (LSP)**: Pada tutorial kali ini belum ada suatu class yang di extend dari class lain sehingga hal ini memenuhi LSP
4. **Interface Segregation Principle (ISP)**: Pada semua class yang mengimplement interface sudah menggunakan seluruh method dari interfacenya hal ini membuat setiap interface pada modul saya memiliki fungsinya masing masing secara spesifik dan tidak memaksa client untuk menggunakan method yang tidak perlu dipakai
5. **Dependencies Inversion Principle (DIP)**: Kembali pada penggunaan interface service pada controller disini menciptakan kondisi dimana modul saya bergantung pada abstraksi sehingga details yang ada pada class implementation dapat bergantung pada interface menggunakan autowired

Dengan menerapkan SOLID Principles saya merasa code saya lebih rapi, minim dependensi yang tidak diperlukan dan mudah untuk ditambahkan suatu fitur kedepannya, hal ini membuat code saya lebih mudah di maintain, scalable, adaptable, dan cleaner.

Tanpa menerapkan SOLID Principles saya rasa code saya jauh lebih dirty dan perlu untuk selalu mengubah code yang sudah dibuat, hal ini dapat menyebabkan kesulitan dalam melakukan maintenance terhadap code saya sehingga projek saya tidak bisa berkembang dengan cepat atau kesulitan dalam skalabilitas. 
</details>

