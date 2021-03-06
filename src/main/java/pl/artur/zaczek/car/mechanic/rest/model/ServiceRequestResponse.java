package pl.artur.zaczek.car.mechanic.rest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceRequestResponse {
    private long id;
    private String title;
    private String comment;
    private LocalDateTime startTime;
    private LocalDateTime finishTime;
    private LocalDateTime createdTime;
    private LocalDateTime modifiedTime;
    private String createdUser;
    private String modifiedUser;
    private boolean isDone;
    private BigDecimal price;
    private BigDecimal discount;
}
