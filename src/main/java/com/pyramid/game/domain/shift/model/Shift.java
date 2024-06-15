package com.pyramid.game.domain.shift.model;

import com.pyramid.game.core.utils.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

/**
 * Created by Suvorov Vassilievitch
 * Date: 11/06/2024
 * Time: 00:19
 * Project Name: pyramid-game-api
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "PYRAM_SHIFT")
public class Shift extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int totalSlip;
    private int paidSlip;
    private int revoqSlip;
    private double totalPayin;
    private double totalPayout;
    private double totalBalance;
    private double totalRevoq;
    private LocalDateTime startDate;
    private double startBalance;
    private double endBalance;
    private LocalDateTime endDate;
    private String user;
    private String partner;

    public static Shift statistics(List<Shift> shifts) {

        int totSlip = 0, paySlip = 0, revqSlip = 0;
        double totPayin = 0d, totPayout = 0d, totBalance = 0d, totRevoq = 0,
                startBalance = 0, endBalance = 0;

        for(Shift s : shifts) {

            totSlip = totSlip + s.totalSlip;
            paySlip = paySlip + s.paidSlip;
            revqSlip = revqSlip + s.revoqSlip;
            totPayin = totPayin + s.totalPayin;
            totPayout = totPayout + s.totalPayout;
            totRevoq = totRevoq + s.totalRevoq;

        }

        totBalance = totPayin - totPayout;

        return Shift.builder()
                .totalSlip(totSlip)
                .paidSlip(paySlip)
                .revoqSlip(revqSlip)
                .totalPayin(totPayin)
                .totalPayout(totPayout)
                .totalBalance(totBalance)
                .totalRevoq(totRevoq)
                .build();

    }

}
