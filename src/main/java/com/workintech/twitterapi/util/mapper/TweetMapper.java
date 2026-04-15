package com.workintech.twitterapi.util.mapper;

import com.workintech.twitterapi.dto.CreateTweetDTO;
import com.workintech.twitterapi.dto.TweetResponseDTO;
import com.workintech.twitterapi.dto.UpdateTweetDTO;
import com.workintech.twitterapi.entity.Tweet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TweetMapper {

    @Mapping(source = "user.username", target = "username")
    TweetResponseDTO toDto(Tweet tweet);

    Tweet toEntity(CreateTweetDTO dto);

    List<TweetResponseDTO> toDtoList(List<Tweet> tweets);

    void updateTweetFromDto(UpdateTweetDTO dto, @MappingTarget Tweet tweet);
}
