package sg.ntu.edu.spring_stocks.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="forecast")
public class Forecast {

    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @Column(name="id")
    // private Long id;
    @Id
    @NotBlank(message="Stock name is mandatory")
    @Column(name="name")
    private String name;
    @Min(value=0, message="Value cannot be less than 0")
    @Column(name="target_high")
    private float targetHigh;
    @Min(value=0, message="Value cannot be less than 0")
    @Column(name="target_low")
    private float targetLow;
    @Min(value=0, message="Value cannot be less than 0")
    @Column(name="roe")
    private float roe;

    // strong buy, buy, hold, sell, strong sell
    @Column(name="forecast")
    private String forecast;


    
}
