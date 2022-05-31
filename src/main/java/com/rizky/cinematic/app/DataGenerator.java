package com.rizky.cinematic.app;
//
//import com.rizky.challenge4.backend.model.dto.FilmDto;
//import com.rizky.challenge4.backend.model.dto.SeatsDto;
//import com.rizky.challenge4.backend.model.entity.SeatsNumberID;
//import com.rizky.challenge4.backend.model.entity.Users;
//import com.rizky.challenge4.backend.service.FilmService;
//import com.rizky.challenge4.backend.service.SeatService;
//import com.rizky.challenge4.backend.service.UserService;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Component;
//
//import java.util.HashSet;
//
//import static com.rizky.challenge4.backend.model.enums.SeatsRowEnum.A;
//import static com.rizky.challenge4.backend.model.enums.SeatsRowEnum.B;
//
//@Component
//public class DataGenerator implements HasLogger {
//
//    @Bean
//    CommandLineRunner loadData(
//            UserService userService,
//            FilmService service,
//            SeatService seatService) {
//        return args -> {
//
//            getLogger().info("Generating existing data");
//
//            userService.registerNewUser(new Users(
//                    "rizky",
//                    "rizky@email.com",
//                    "Malang",
//                    "apasihhh",
//                    new HashSet<>()
//            ));
//            userService.addUsers(new Users(
//                    "malaikat",
//                    "malaikat@email.com",
//                    "Situbondo",
//                    "ngantuk",
//                    new HashSet<>()
//            ));
//
//            service.addFilm(new FilmDto(
//                    "MPN",
//                    "Malaikat pencabut nyali",
//                    "Malaikat yang paling ditakuti",
//                    false));
//            service.addFilm(new FilmDto(
//                    "TTGNT",
//                    "Tetangga Genit",
//                    "Film yang kontroversial(Katanya)",
//                    true));
//            service.addFilm(new FilmDto(
//                    "KOK",
//                    "Kapal Oleng Kapten",
//                    "Film yang dirilis enggak tau kapan",
//                    false));
//            service.addFilm(new FilmDto(
//                    "KF",
//                    "Katanya Film",
//                    "Salah satu film yang tidak tau bahwa dia adalah film",
//                    true));
//            service.addFilm(new FilmDto(
//                    "CHKTTD",
//                    "Chat Ku Tenggelam oleh banyaknya Pesan dari Teman Dosenku",
//                    "Skripsi dipersulit. Bimbingan pun jadi sulit. Lulus pun jadi sulit", true));
//            service.addFilm(new FilmDto(
//                    "KMLSKD", "Katanya Mau Lulus? Skripsi Kok Dianggurin",
//                    "Salah Satu pernyataan paling nyelekit",
//                    false));
//            service.addFilm(new FilmDto(
//                    "PCAB",
//                    "Pocong Absurd",
//                    "Ini film horor ya?",
//                    true));
//            service.addFilm(new FilmDto(
//                    "KTLK",
//                    "Kuntilanak",
//                    "film horor ya?",
//                    true));
//
//            seatService.addNewSeats(new SeatsDto(new SeatsNumberID(1L, A)));
//            seatService.addNewSeats(new SeatsDto(new SeatsNumberID(2L, A)));
//            seatService.addNewSeats(new SeatsDto(new SeatsNumberID(3L, A)));
//            seatService.addNewSeats(new SeatsDto(new SeatsNumberID(1L, B)));
//            seatService.addNewSeats(new SeatsDto(new SeatsNumberID(2L, B)));
//            seatService.addNewSeats(new SeatsDto(new SeatsNumberID(3L, B)));
//
//        };
//    }
//}
