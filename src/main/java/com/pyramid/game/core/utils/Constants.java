package com.pyramid.game.core.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;

import java.time.Instant;

/**
 * Created by Suvorov Vassilievitch
 * Date: 15/04/2024
 * Time: 13:04
 * Project Name: pyramid-game-api
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constants {

    public static final String VALUE_SHOULD_NOT_BE_NULL = "Value Should Not Be Null Or Empty";
    public static final String PARTNER_NOT_FOUND = "Partner not found.";
    public static final String GAME_NOT_FOUND = "Game not found.";
    public static final String GAME_NOT_ACTIVE = "Game not active.";
    public static final String GAME_UNIQUE_CODE = "Code should be unique.";
    public static final String ENROLLMENT_GAME_EXIST = "Game already enroll.";
    public static final String ENROLLMENT_NOT_FOUND = "Subscription do not exists.";
    public static final String ENROLLMENT_NOT_ACTIVE = "Subscription not active.";
    public static final String PARAMETER_EXCEPTION = "No such configuration";
    public static final String USER_LOGIN_EXIST = "Login already used.";
    public static final String USER_NOT_FOUND = "User not found.";
    public static final String CASHIER_NOT_FOUND = "Cashier not found.";
    public static final String USER_NOT_CASHIER = "User is not a cashier.";
    public static final String ROOM_NOT_FOUND = "Room not found.";
    public static final String ROOM_NOT_ACTIVE = "Room not active.";
    public static final String PARTNER_ALREADY_EXIST = "Partner already exists";
    public static final String EVENT_NOT_FOUND = "Event not found";
    public static final String EVENT_NOT_UNKNOWNDRAW = "Event not recognize";
    public static final String BET_UNKNOWN = "Bet not recognize";
    public static final String WITHDRAW_NOT_FOUND = "Ticket de paiement introuvable";
    public static final String SHIFT_NOT_FOUND = "Shift not open yet.";
    public static final String MOUVEMENT_NOT_FOUND = "Movement not open yet.";
    public static final String STAT_KENO = """
                SELECT COUNT(id) AS totalSlip,  COALESCE(sum(montant_mise),0) AS totalPayin 
                FROM PYRAM_BETKENO 
                WHERE 
                UPPER(cashier_login)=UPPER(?1) 
                AND UPPER(code_partner)=UPPER(?2) 
                AND UPPER(salle)=UPPER(?3) 
                AND created_at BETWEEN ?4 AND ?5
            """;

    public static String generateCode(){
        String sb = RandomStringUtils.randomAlphanumeric(10).toUpperCase() +
                Instant.now().toEpochMilli();
        return sb.toUpperCase();
    }

    public static Long generateBarcode() {
        return 10_000_000_000_000L - Instant.now().toEpochMilli();
    }
}
