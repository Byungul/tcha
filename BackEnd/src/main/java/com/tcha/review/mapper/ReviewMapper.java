package com.tcha.review.mapper;

import com.tcha.review.dto.ReviewDto;
import com.tcha.review.dto.ReviewDto.Response;
import com.tcha.review.entity.Review;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    Review postToReview(ReviewDto.Post postRequest);

    default ReviewDto.Response reviewToResponse(Review review) {
        return ReviewDto.Response.builder()
                .id(review.getId())
                .content(review.getContent())
                .star(review.getStar())
                .profileImg(review.getUserProfile().getProfileImage())
                .profileName(review.getUserProfile().getName())
                .trainerProfileImg(review.getTrainer().getUserProfile().getProfileImage())
                .trainerProfileName(review.getTrainer().getUserProfile().getName())
                .build();
    }

    List<Response> reviewsToResponses(List<Review> reviews);
}
