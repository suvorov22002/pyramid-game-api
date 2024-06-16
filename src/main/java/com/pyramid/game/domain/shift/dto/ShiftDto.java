package com.pyramid.game.domain.shift.dto;

import com.pyramid.game.core.validation.MandatoryField;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Created by Suvorov Vassilievitch
 * Date: 12/06/2024
 * Time: 06:28
 * Project Name: pyramid-game-api
 */
@Getter
@Setter
@AllArgsConstructor
public class ShiftDto {

    private Long id;
    private int totalSlip;
    private int paidSlip;
    private int revoqSlip;
    private double totalPayin;
    private double totalPayout;
    private double totalBalance;
    private double totalRevoq;
    private LocalDateTime startDate;
    @MandatoryField
    private double startBalance;
    private double endBalance;
    private LocalDateTime endDate;
    @MandatoryField
    private String login;
    @MandatoryField
    private String partner;
    @MandatoryField
    private String salle;
    private String shiftStatus;

}
