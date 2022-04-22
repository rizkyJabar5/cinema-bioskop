package com.rizky.challenge4;

import com.rizky.challenge4.backend.model.dto.FilmDto;
import com.rizky.challenge4.backend.model.dto.UserDto;
import com.rizky.challenge4.backend.repository.SchedulesRepository;
import com.rizky.challenge4.backend.repository.SeatsRepository;
import com.rizky.challenge4.backend.service.FilmService;
import com.rizky.challenge4.backend.service.UserService;
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
    CommandLineRunner run(UserService userService, FilmService service, SchedulesRepository schedules, SeatsRepository seatsRepository) {
        return args -> {
            Logger logger = LoggerFactory.getLogger(getClass());
            logger.info("Generating existing data");

            userService.addUser(new UserDto("sukronganteng", "sukron@email.com", "Surabaya", "aaah Ngantuk"));
            userService.addUser(new UserDto("rizky", "rizky@email.com", "Malang", "apasihhh"));
            userService.addUser(new UserDto("malaikat", "malaikat@email.com", "Situbondo", "ngantuk"));
            userService.addUser(new UserDto("kusionojumari", "kusiono@email.com", "Magelang", "aaah"));
            userService.addUser(new UserDto("andrejipun", "andre@email.com", "Purwokerto", "gak Ngantuk"));
            userService.addUser(new UserDto("izamkipli", "izam@email.com", "Jakarta", "janganGitu"));
            userService.addUser(new UserDto("mamatskonat", "mamat@email.com", "Bandung", "sayaHalu"));


            service.addFilm(new FilmDto("MPN" ,"Malaikat pencabut nyali", "Malaikat yang paling ditakuti", false));
            service.addFilm(new FilmDto("TTGNT" ,"Tetangga Genit", "Film yang kontroversial(Katanya)", true));
            service.addFilm(new FilmDto("KOK" ,"Kapal Oleng Kapten", "Film yang dirilis enggak tau kapan", false));
            service.addFilm(new FilmDto("KF" ,"Katanya Film", "Salah satu film yang tidak tau bahwa dia adalah film", true));
            service.addFilm(new FilmDto("CHKTTD" ,"Chat Ku Tenggelam oleh banyaknya Pesan dari Teman Dosenku", "Skripsi dipersulit. Bimbingan pun jadi sulit. Lulus pun jadi sulit", true));
            service.addFilm(new FilmDto("KMLSKD" ,"Katanya Mau Lulus? Skripsi Kok Dianggurin", "Salah Satu pernyataan paling nyelekit", false));
            service.addFilm(new FilmDto("PCAB" ,"Pocong Absurd", "Ini film horor ya?", true));
            service.addFilm(new FilmDto("KTLK" ,"Kuntilanak", "film horor ya?", true));

//            service.addSchedule(new ScheduleDto(, , , 45000), "MPN");

        };
    }
}
