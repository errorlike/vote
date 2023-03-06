package com.errorlike.vote.entities;

import java.time.LocalDateTime;

import javax.persistence.*;

import lombok.*;

@Builder
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "Participation", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "form_id"})
})
public class Participation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "form_id",nullable = false)
    private Form form;

    private LocalDateTime participationTime;

}