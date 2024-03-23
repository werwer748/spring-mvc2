package hello.itemservice.domain.item;

// bean validation에서 제공되서 어떤 구현체에서도 작동

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
public class ItemRegacy {

    @NotNull(groups = UpdateCheck.class) //수정 요구사항 추가
    private Long id;

    @NotBlank(message = "공백X", groups = {SaveCheck.class, UpdateCheck.class}) // 빈값, 공백만 있는 경우 에러 - 프로퍼티설정이 우선 적용
    private String itemName;

    @NotNull(groups = {SaveCheck.class, UpdateCheck.class}) // null인 경우 에러
//    @Range(min = 1000, max = 1000000) // 범위안의 값이 아닌 경우 에러
//    @Min(1000)
//    @Max(1000000)
    @Range(min = 1000, max = 1000000)
    private Integer price;

    @NotNull(groups = {SaveCheck.class, UpdateCheck.class})
    @Max(value = 9999, groups = {SaveCheck.class}) // 최대 범위를 넘길 경우 에러
    private Integer quantity;

    public ItemRegacy() {
    }

    public ItemRegacy(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
