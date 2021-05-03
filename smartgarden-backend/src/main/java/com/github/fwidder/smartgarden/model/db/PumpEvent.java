package com.github.fwidder.smartgarden.model.db;

import com.github.fwidder.smartgarden.config.GPIOPumpOutputPin;
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
public class PumpEvent {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private boolean state;

    @Column
    private LocalDateTime time;

    @Enumerated(EnumType.STRING)
    private GPIOPumpOutputPin pump;
}
