package com.volunteer.vo;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class ServeTime {
    private String activityName;

    private BigDecimal serveTime;
}
