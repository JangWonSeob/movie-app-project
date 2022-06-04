package com.website.movie.biz.component;

import com.website.movie.biz.dao.CodeDao;
import com.website.movie.biz.dao.MovieDao;
import com.website.movie.biz.dto.CodeDto;
import com.website.movie.biz.model.movie.MovieData;
import com.website.movie.biz.model.movie.MovieProviderDetail;
import com.website.movie.biz.model.movie.MovieProviders;
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

    private final CodeDao codeDao;

    public List<MovieData> getMovieList(int pageNo) {

//      https://api.themoviedb.org/3/movie/popular?api_key={{api_key}}&language=ko-Kr&page=1

        String url = tmdbProperties.getMovieUrl();
        url += "api_key=" + tmdbProperties.getApiKey();
        url += "&language=ko-Kr";
        url += "&page=" + pageNo;

        MovieResult result = restTemplate.getForObject(url, MovieResult.class);

        System.out.println(result);

        return result.getResults();
    }

    public List<MovieData> getTvList(int pageNo) {

//      https://api.themoviedb.org/3/movie/popular?api_key={{api_key}}&language=ko-Kr&page=1

        String url = tmdbProperties.getTvUrl();
        url += "api_key=" + tmdbProperties.getApiKey();
        url += "&language=ko-Kr";
        url += "&page=" + pageNo;

        MovieResult result = restTemplate.getForObject(url, MovieResult.class);

        System.out.println(result);

        return result.getResults();
    }

    public void getProviders() {

        String url = "https://api.themoviedb.org/3/watch/providers/movie?api_key=a0684e801c0369a97ce6b44f3c6b453b&language=en-US";

        MovieProviders result = restTemplate.getForObject(url, MovieProviders.class);

        System.out.println(result);

        int i = 1;

        for (MovieProviderDetail x : result.getResults()) {
            CodeDto codeDto = CodeDto.builder()
                    .subId(x.getProviderId())
                    .name(x.getProviderName())
                    .type("MOVIE_PROVIDERS")
                    .sortValue(i)
                    .imgPath(x.getLogoPath())
                    .build();

            codeDao.insert(codeDto);
            i += 1;
        }
    }

}
