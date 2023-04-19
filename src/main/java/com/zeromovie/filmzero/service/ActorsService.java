package com.zeromovie.filmzero.service;

import com.zeromovie.filmzero.dto.ActorDto;
import com.zeromovie.filmzero.dto.MovieDto;
import com.zeromovie.filmzero.entity.Actors;
import com.zeromovie.filmzero.entity.MovieInfo;
import com.zeromovie.filmzero.entity.QMovieInfo;
import com.zeromovie.filmzero.repository.ActorsRepository;
import com.zeromovie.filmzero.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ActorsService {
    private final ActorsRepository actorsRepository;



    @Transactional
    public List<Actors> findById(Long id) {
        List<Actors> actorsList = actorsRepository.findByMovieInfoId(id);
        return actorsList;
    }
}
