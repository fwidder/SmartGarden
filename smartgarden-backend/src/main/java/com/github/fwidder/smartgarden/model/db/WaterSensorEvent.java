package com.github.fwidder.smartgarden.model.db;

import com.github.fwidder.smartgarden.config.ArduinoWaterSensorInputPin;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
@ToString
public class WaterSensorEvent {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private long value;

    @Column
    private LocalDateTime time;

    @Enumerated(EnumType.STRING)
    private ArduinoWaterSensorInputPin sensor;
}
