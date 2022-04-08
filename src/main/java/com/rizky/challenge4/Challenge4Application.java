package com.rizky.challenge4;

import com.rizky.challenge4.backend.data.entity.Films;
import com.rizky.challenge4.backend.data.entity.Users;
import com.rizky.challenge4.backend.repository.SchedulesRepository;
import com.rizky.challenge4.backend.repository.UsersRepository;
import com.rizky.challenge4.backend.service.FilmService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Challenge4Application {

    public static void main(String[] args) {
        SpringApplication.run(Challenge4Application.class, args);
    }

    @Bean
    CommandLineRunner run (UsersRepository userRepository, FilmService service, SchedulesRepository schedules){
        return args -> {
            Logger logger = LoggerFactory.getLogger(getClass());
            if(userRepository.count() != 0L){
                logger.info("Using existing database");
                return;
            }

            logger.info("Generating existing data");

            Films filmOnShow = new Films();

            userRepository.save( new Users("sukronganteng", "sukron@email.com", "Surabaya", "aaah Ngantuk"));
            userRepository.save( new Users("rizky", "rizky@email.com", "Malang", "apasihhh"));
            userRepository.save( new Users("malaikat", "malaikat@email.com", "Situbondo", "ngantuk"));
            userRepository.save( new Users("kusionojumari", "kusiono@email.com", "Magelang", "aaah"));
            userRepository.save( new Users("andrejipun", "andre@email.com", "Purwokerto", "gak Ngantuk"));
            userRepository.save( new Users("izamkipli", "izam@email.com", "Jakarta", "janganGitu"));
            userRepository.save( new Users("mamatskonat", "mamat@email.com", "Bandung", "sayaHalu"));


            service.addFilm( new Films("Malaikat pencabut nyali", "Malaikat yang paling ditakuti", false));
            service.addFilm( new Films("Tetangga Genit", "Film yang kontroversial(Katanya)", true));
            service.addFilm( new Films("Kapal Oleng Kapten", "Film yang dirilis enggak tau kapan", false));
            service.addFilm( new Films("Katanya Film", "Salah satu film yang tidak tau bahwa dia adalah film", true));
            service.addFilm( new Films("Chat Ku Tenggelam oleh banyaknya Pesan dari Teman Dosenku", "Skripsi dipersulit. Bimbingan pun jadi sulit. Lulus pun jadi sulit", true));
            service.addFilm( new Films("Katanya Mau Lulus? Skripsi Kok Dianggurin", "Salah Satu pernyataan paling nyelekit", false));
            service.addFilm( new Films("Pocong Absurd", "Ini film horor ya?", true));

        };
    }
}
