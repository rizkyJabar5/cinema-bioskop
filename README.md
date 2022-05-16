# Cinematic
Aplikasi cinematic adalah aplikasi backend yang menggunakan teknologi spring boot, 
sebagai transaksi penayangan jadwal film sesuai jadwal.
# Technologies
1. Spring boot
2. Postgresql
3. JWT Token
## Deployment to Heroku
url: `https://cinematic21.herokuapp.com`
## Test case menggunakan API

| Jadwal & Film                             | User                         |
|-------------------------------------------|------------------------------|
| Menambah Film baru                        | Menambah User                |
| Menambah Film baru lebih dari 1           | Menambah User lebih dari 1   |
| Update Film                               | Update User                  |
| Hapus film                                | Menampilkan User             |
| Menampilkan film By Id.                   | Hapus User                   |
| Menampilkan list film yang sedang tayang. | Menampilkan user By Email    |
| Menampilkan jadwal pada film tertentu.    | Menampilkan user By username |

## Entity Relationship Diagram
Rancangan ERD 
![ERD](./src/main/resources/ERD/ERD%20Challenge%204%20Binar.png)

## Relational Diagram
Rancangan sementara
![ERD](./src/main/resources/ERD/Bioskop Binar Challenge.png)