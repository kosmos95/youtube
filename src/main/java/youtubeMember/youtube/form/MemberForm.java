package youtubeMember.youtube.form;

import lombok.Getter;
import lombok.Setter;
import youtubeMember.youtube.doamin.Office;

import javax.validation.constraints.NotEmpty;


@Getter@Setter
public class MemberForm {


    @NotEmpty(message = "이름은 필수 입력입니다")
    private String name;

    @NotEmpty(message = "채널 아이디를 넣어주세요")
    private String channelId;

    private Boolean leader;

}
