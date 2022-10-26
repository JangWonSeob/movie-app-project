package com.website.movie.biz.component;

import com.website.movie.biz.dao.CodeDao;
import com.website.movie.biz.dto.CodeDto;
import com.website.movie.biz.model.movie.*;
import com.website.movie.biz.model.movie.detail.MovieResultDetail;
import com.website.movie.biz.model.tv.TvData;
import com.website.movie.biz.model.tv.TvResult;
import com.website.movie.biz.model.tv.detail.TvResultDetail;
import com.website.movie.common.properties.TmdbProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TmdbComponent {

    private final TmdbProperties tmdbProperties;
    private final RestTemplate restTemplate;

    private final CodeDao codeDao;

    public List<MovieData> getMovieList(int pageNo) {

        MovieResult result = new MovieResult();

        try{
            //      https://api.themoviedb.org/3/movie/popular?api_key={{api_key}}&language=ko-Kr&page=1

            String url = tmdbProperties.getMovieUrl() + "popular?";
            url += "api_key=" + tmdbProperties.getApiKey();
            url += "&language=ko-Kr";
            url += "&page=" + pageNo;

            result = restTemplate.getForObject(url, MovieResult.class);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return result.getResults();
    }

    public MovieResultDetail getMovieDetail(String id) {

        MovieResultDetail result = new MovieResultDetail();

        try {
            //      https://api.themoviedb.org/3/movie/{{id}}?api_key={{api_key}}&language=ko-Kr&page=1&append_to_response=videos,images,watch/providers,credits

            String url = tmdbProperties.getMovieUrl() + id + "?";
            url += "api_key=" + tmdbProperties.getApiKey();
            url += "&language=ko-KR";
            url += "&append_to_response=videos,images,watch/providers,credits";

            result = restTemplate.getForObject(url, MovieResultDetail.class);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return result;
    }

    public List<TvData> getTvList(int pageNo) {

        TvResult result = new TvResult();

        try {
            //      https://api.themoviedb.org/3/tv/popular?api_key={{api_key}}&language=ko-Kr&page=1

            String url = tmdbProperties.getTvUrl();
            url += "api_key=" + tmdbProperties.getApiKey();
            url += "&language=ko-KR";
            url += "&page=" + pageNo;

            result = restTemplate.getForObject(url, TvResult.class);

            System.out.println(result);
        } catch (Exception e) {

        }

        return result.getResults();
    }

    public TvResultDetail getTvDetail(String id) {

        TvResultDetail result = new TvResultDetail();

        try {
            //      https://api.themoviedb.org/3/tv/{{id}}?api_key={{api_key}}&language=ko-Kr&append_to_response=videos,images,watch/providers,credits

            String url = tmdbProperties.getTvUrl() + id + "?";
            url += "api_key=" + tmdbProperties.getApiKey();
            url += "&language=ko-KR";
            url += "&append_to_response=videos,images,watch/providers,credits";

            result = restTemplate.getForObject(url, TvResultDetail.class);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return result;
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
