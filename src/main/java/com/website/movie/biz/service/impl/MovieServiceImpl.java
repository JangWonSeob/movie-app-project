package com.website.movie.biz.service.impl;

import com.website.movie.biz.component.MovieComponent;
import com.website.movie.biz.dao.CodeDao;
import com.website.movie.biz.dao.MovieDao;
import com.website.movie.biz.dto.CodeDto;
import com.website.movie.biz.dto.MovieDto;
import com.website.movie.biz.model.movie.MovieData;
import com.website.movie.biz.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;


@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieDao movieDao;
    private final CodeDao codeDao;
    private final MovieComponent movieComponent;

    private void setGenre(String[] ids, MovieDto parameter) {
        if (ids.length > 1) {
            String idStr = "";
            String nameStr = "";
            for (int i = 0; i < ids.length; i++) {
                if(!StringUtils.isEmpty(ids[i])) {
                    CodeDto code = codeDao.selectOne(CodeDto.builder().id(ids[i]).build());
                    if(code != null) {
                        idStr += ids[i];
                        nameStr += code.getName();
                        if (i < ids.length - 1) {
                            idStr += ",";
                            nameStr += ",";
                        }
                    }
                }
            }
            parameter.setGenreIds(idStr);
            parameter.setGenreNames(nameStr);
        }
    }

    @Override
    public List<CodeDto> main() {


        List<CodeDto> result = codeDao.selectList(CodeDto.builder().type(MovieDto.CODE_TYPE).build());

        System.out.println(result);

        for (CodeDto x : result) {
            x.setMovieList(movieDao.main(MovieDto.builder().searchGenre(x.getId()).build()));
        }


        return result;
    }


    @Override
    public void getTmdbMovieData() {

        for (int i = 1; i < 11; i++) {
            List<MovieData> list = movieComponent.getMovieList(i);
            for (MovieData movieData : list) {
                MovieDto parameter = MovieDto.toDto(movieData);
                movieDao.delete(parameter);
                setGenre(movieData.getGenreIds(), parameter);
                movieDao.insert(parameter);
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

        return movieDao.selectOne(parameter);
    }


}
