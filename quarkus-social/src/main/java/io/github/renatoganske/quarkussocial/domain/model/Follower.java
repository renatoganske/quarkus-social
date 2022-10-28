package io.github.renatoganske.quarkussocial.domain.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "followers")
@Data
public class Follower {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    @JoinColumn(name = "user_id")
    private User follower;
}
