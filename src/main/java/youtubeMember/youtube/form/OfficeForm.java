package youtubeMember.youtube.form;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Getter@Setter
public class OfficeForm {

    @NotNull(message = "도장을 선택해주세요")
    @Range(min = 1, max = 1000)
    private Long id;
}
