    package com.projpba.projpba.DTO;

    import com.fasterxml.jackson.annotation.JsonProperty;
    import lombok.Getter;
    import lombok.Setter;

    import java.time.LocalDate;
    import java.util.Date;

    @Getter
    @Setter
    public class ReservationDto {
        private Long hotelId;
        private Long roomId;
        private LocalDate startDate;
        private LocalDate endDate;
        private String userEmail;
    }

