package com.website.movie.web.schedule;


import com.website.movie.biz.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Scheduler {

    private final MovieService movieService;

//    @Scheduled(cron = "")
//    public void tmdb() {
//        movieService.getTmdbMovieData();
//    }

}
















