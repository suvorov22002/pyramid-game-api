package com.pyramid.game.domain.mouvement.dto;

import com.pyramid.game.domain.users.model.AppUser;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Created by Suvorov Vassilievitch
 * Date: 14/06/2024
 * Time: 20:06
 * Project Name: pyramid-game-api
 */
@Getter
@Setter
@AllArgsConstructor
public class MouvementDto {

    private Long id;
    private Double credit;
    private Double debit;
    private String operation;
    private Double balance;
    private String login;
    private String partner;
    private LocalDateTime createdAt;
}
