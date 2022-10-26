package com.website.movie.biz.service.impl;

import com.website.movie.biz.component.TmdbComponent;
import com.website.movie.biz.dao.*;
import com.website.movie.biz.dto.*;
import com.website.movie.biz.model.movie.MovieData;
import com.website.movie.biz.model.movie.detail.MovieGenres;
import com.website.movie.biz.model.movie.detail.MovieKrBuyRent;
import com.website.movie.biz.model.movie.detail.MovieResultDetail;
import com.website.movie.biz.model.movie.detail.MovieVideos;
import com.website.movie.biz.model.tv.TvData;
import com.website.movie.biz.model.tv.detail.TvResultDetail;
import com.website.movie.biz.service.MovieService;
import com.website.movie.biz.service.TvService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class TvServiceImpl implements TvService {

    private final MovieDao movieDao;
    private final MovieWatchProviderDao movieWatchProviderDao;
    private final MovieYoutubeDao movieYoutubeDao;
    private final CodeDao codeDao;
    private final BookMarkDao bookMarkDao;


    private final TmdbComponent tmdbComponent;


    private void setGenre(List<MovieGenres> genreList, MovieDto parameter) {

        if (genreList != null) {
            String idStr = "";
            String nameStr = "";
            for (int i = 0; i < genreList.size(); i++) {
                idStr += genreList.get(i).getId();
                nameStr += genreList.get(i).getName();
                if (i < genreList.size() - 1) {
                    idStr += ",";
                    nameStr += ",";
                }
            }
            parameter.setGenreIds(idStr);
            parameter.setGenreNames(nameStr);
        }

    }

    @Override
    public List<MovieDto> index() {

        return movieDao.index();
    }

    @Override
    public List<MovieDto> index2() {

        return movieDao.index2();
    }

    @Override
    public List<CodeDto> main() {
        List<CodeDto> result = codeDao.selectList(CodeDto.builder().type(MovieDto.CODE_TYPE_MOVIE_GENRE).build());

        for (CodeDto x : result) {
            x.setMovieList(movieDao.main(MovieDto.builder().searchGenre(x.getSubId()).build()));
        }
        return result;
    }

    @Override
    public void getTmdbTvData() {

        for (int i = 0; i < 10; i++) {
            List<TvData> list = tmdbComponent.getTvList(i + 1);
            for (TvData x : list) {
                TvResultDetail movieDetail = tmdbComponent.getTvDetail(x.getId());



            }
        }
    }

    @Override
    public List<MovieDto> gets(MovieDto parameter) {

        return movieDao.selectList(parameter);
    }

    @Override
    public int totalCount(MovieDto parameter) {

        return movieDao.selectListCount(parameter);
    }

    @Override
    public MovieDto get(MovieDto parameter) {

        MovieDto result = movieDao.selectOne(parameter);

        MovieWatchProvidersDto watchProvidersDto = MovieWatchProvidersDto.builder()
                .movieId(result.getId())
                .searchProviderType(MovieWatchProvidersDto.TYPE_BUY)
                .build();

        // buy
        result.setWatchProvidersBuyList(movieWatchProviderDao.selectList(watchProvidersDto));

        // rent
        watchProvidersDto.setSearchProviderType(MovieWatchProvidersDto.TYPE_RENT);
        result.setWatchProvidersRentList(movieWatchProviderDao.selectList(watchProvidersDto));

        // YOUTUBE
        result.setWatchYoutubeList(movieYoutubeDao.selectList(MovieYoutubeDto.builder().movieId(result.getId()).build()));

        if (parameter.getLoginUserId() > 0) {
            result.setBookMarkYn(bookMarkDao.selectMyBookMark(BookMarkDto.builder().tableId(result.getId()).tableName(BookMarkDto.TABLE_NAME_MOVIE).loginUserId(parameter.getLoginUserId()).build()));
        }

        System.out.println(result);

        return result;
    }


}
