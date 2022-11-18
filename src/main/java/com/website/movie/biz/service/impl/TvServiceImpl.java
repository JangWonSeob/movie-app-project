package com.website.movie.biz.service.impl;

import com.website.movie.biz.component.TmdbComponent;
import com.website.movie.biz.dao.*;
import com.website.movie.biz.dto.*;
import com.website.movie.biz.model.tv.TvData;
import com.website.movie.biz.model.tv.detail.TvGenres;
import com.website.movie.biz.model.tv.detail.TvKr;
import com.website.movie.biz.model.tv.detail.TvResultDetail;
import com.website.movie.biz.model.tv.detail.TvVideos;
import com.website.movie.biz.service.TvService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class TvServiceImpl implements TvService {

    private final TvDao tvDao;
    private final TvWatchProviderDao tvWatchProviderDao;
    private final TvYoutubeDao tvYoutubeDao;
    private final CodeDao codeDao;
    private final BookMarkDao bookMarkDao;


    private final TmdbComponent tmdbComponent;


    private void setGenre(List<TvGenres> genreList, TvDto parameter) {

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
    public List<TvDto> index() {

        return tvDao.index();
    }


    @Override
    public List<CodeDto> main() {
        List<CodeDto> result = codeDao.selectList(CodeDto.builder().type(TvDto.CODE_TYPE_TV_GENRE).build());

        for (CodeDto x : result) {
            x.setTvList(tvDao.main(TvDto.builder().searchGenre(x.getSubId()).build()));
        }
        return result;
    }

    @Override
    public void getTmdbTvData() {

        for (int i = 0; i < 10; i++) {
            List<TvData> list = tmdbComponent.getTvList(i + 1);
            if (list != null) {
                for (TvData x : list) {
                    TvResultDetail tvDetail = tmdbComponent.getTvDetail(x.getId());
                    TvDto parameter = TvDto.toDto(tvDetail);

                    // 삭제
                    tvDao.delete(parameter);
                    // 공급자 삭제
                    tvWatchProviderDao.deleteByMovieId(TvWatchProvidersDto.builder().tvId(parameter.getId()).build());
                    // YOUTUBE 삭제
                    tvYoutubeDao.deleteByTvId(TvYoutubeDto.builder().tvId(parameter.getId()).build());

                    // 장르 세팅
                    setGenre(tvDetail.getGenres(), parameter);
                    // TV 추가
                    tvDao.insert(parameter);
                    if (tvDetail.getWatchProviders() != null && tvDetail.getWatchProviders().getResults() != null
                            && tvDetail.getWatchProviders().getResults().getKR() != null) {
                        if (tvDetail.getWatchProviders().getResults().getKR().getFlatrate() != null && tvDetail.getWatchProviders().getResults().getKR().getFlatrate().size() > 0) {
                            for (TvKr z : tvDetail.getWatchProviders().getResults().getKR().getFlatrate()) {
                                tvWatchProviderDao.insert(TvWatchProvidersDto.toDto(tvDetail.getId(), z));
                            }
                        }
                    }

                    int j = 1;
                    if (tvDetail.getVideos() != null && tvDetail.getVideos().getResults() != null
                            && !tvDetail.getVideos().getResults().isEmpty()) {
                        // YOUTUBE 추가
                        for (TvVideos y : tvDetail.getVideos().getResults()) {
                            if ("YouTube".equals(y.getSite())) {
                                tvYoutubeDao.insert(TvYoutubeDto.toDto(y, tvDetail.getId(), j++));
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public List<TvDto> gets(TvDto parameter) {

        return tvDao.selectList(parameter);
    }

    @Override
    public int totalCount(TvDto parameter) {

        return tvDao.selectListCount(parameter);
    }

    @Override
    public TvDto get(TvDto parameter) {

        TvDto result = tvDao.selectOne(parameter);

        TvWatchProvidersDto tvWatchProvidersDto = TvWatchProvidersDto.builder()
                .tvId(result.getId())
                .build();

        result.setWatchProvidersList(tvWatchProviderDao.selectList(tvWatchProvidersDto));

        // YOUTUBE
        result.setWatchYoutubeList(tvYoutubeDao.selectList(TvYoutubeDto.builder().tvId(result.getId()).build()));

        if (parameter.getLoginUserId() > 0) {
            result.setBookMarkYn(bookMarkDao.selectMyBookMark(BookMarkDto.builder().tableId(result.getId()).tableName(BookMarkDto.TABLE_NAME_MOVIE).loginUserId(parameter.getLoginUserId()).build()));
        }

        System.out.println(result);

        return result;
    }


}
