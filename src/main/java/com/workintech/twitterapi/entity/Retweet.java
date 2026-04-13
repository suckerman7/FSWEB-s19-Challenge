package com.workintech.twitterapi.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "retweets", uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "tweet_id"}))
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Retweet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Tweet tweet;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
