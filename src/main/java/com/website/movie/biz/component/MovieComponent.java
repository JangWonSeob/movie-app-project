package com.website.movie.biz.component;

import com.website.movie.biz.dao.MovieDao;
import com.website.movie.biz.model.movie.MovieData;
import com.website.movie.biz.model.movie.MovieResult;
import com.website.movie.common.properties.TmdbProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MovieComponent {

    private final TmdbProperties tmdbProperties;
    private final RestTemplate restTemplate;

    public List<MovieData> getMovieList(int pageNo) {

//      https://api.themoviedb.org/3/movie/popular?api_key={{api_key}}&language=ko-Kr&page=1

        String url = tmdbProperties.getBaseUrl();
        url += "api_key=" + tmdbProperties.getApiKey();
        url += "&language=ko-Kr";
        url += "&page=" + pageNo;

        MovieResult result = restTemplate.getForObject(url, MovieResult.class);

        System.out.println(result);

        return result.getResults();
    }

}
