package com.workintech.twitterapi.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "retweets", schema = "fsweb", uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "tweet_id"}))
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Retweet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tweet_id", nullable = false)
    private Tweet tweet;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
