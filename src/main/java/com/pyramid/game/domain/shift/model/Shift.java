package com.pyramid.game.domain.shift.model;

import com.pyramid.game.core.utils.BaseEntity;
import com.pyramid.game.core.validation.MandatoryField;
import com.pyramid.game.domain.shift.model.enums.ShiftStatus;
import jakarta.persistence.*;
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
//@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
@Entity(name = "PYRAM_SHIFT")
public class Shift extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int totalSlip = 0;
    private int paidSlip = 0;
    private int revoqSlip = 0;
    private Double totalPayin = 0d;
    private Double totalPayout = 0d;
    private Double totalBalance = 0d;
    private Double totalRevoq = 0d;
    private LocalDateTime startDate;
    @MandatoryField
    private Double startBalance;
    private Double endBalance;
    private LocalDateTime endDate;
    @MandatoryField
    private String login;
    @MandatoryField
    private String partner;
    @MandatoryField
    private String salle;
    @MandatoryField
    @Enumerated(EnumType.STRING)
    private ShiftStatus shiftStatus;

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
        Shift shift = new Shift();
        shift.setTotalSlip(totSlip);
        shift.setPaidSlip(paySlip);
        shift.setRevoqSlip(revqSlip);
        shift.setTotalPayin(totPayin);
        shift.setTotalPayout(totPayout);
        shift.setTotalBalance(totBalance);
        shift.setTotalRevoq(totRevoq);

        return shift;

    }

}
