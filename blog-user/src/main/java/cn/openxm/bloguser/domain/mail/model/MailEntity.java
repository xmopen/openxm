package cn.openxm.bloguser.domain.mail.model;

import cn.openxm.bloguser.infrastructure.util.ThreadSafeRandom;
import lombok.*;

/**
 * author Xiao Ma
 * date 2024/6/26
 */
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MailEntity {

    private String from;

    private String to;

    private String title;

    private String subject;

    private String content;

    private int code;

    private MailType type;

    public static enum MailType {

        MAIL_TYPE_TEXT(0),
        MAIL_TYPE_HTML(1),;

        private final int mailType;

        MailType(int mailType){
            this.mailType = mailType;
        }
    }

    public int generateMailCode(){
        this.code = ThreadSafeRandom.randomInt(1000,9999);
        return this.code;
    }

}
