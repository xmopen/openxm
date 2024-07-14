package cn.openxm.bloguser.domain.user.model;

import lombok.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * Entity 可以理解为 具有唯一标识符的实体，其初始化可以理解为从DO中转换而来，但是其状态会随着业务流程的处理而改变，
 * 最终交给Repository进行持久化。
 * <p>
 * 其最终通过Builder建造者模式对外提供实例，从而提供服务，其提供的服务都是单例。
 *  TODO: 是否自己可以实现Builder注解？然后通过内存池来进行实现？
 *
 * @author Xiao Ma
 * @date 2024/6/26
 * @slogan 少年应有鸿鹄志，当骑骏马踏平川。
 */
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity implements Serializable {

    /**
     * 用户唯一标识符。
     * */
    private Integer id;

    private String mail;

    private int mailCode;

    private String nickName;

    private String password;

    private String icon;

    /**
     * 校验当前领域UserEntity在注册模块信息是否足够。
     * @return true 表示参数校验OK。
     */
    public boolean checkRegisterInfoIsValid() {
        return !Objects.equals(this.mail, "") && this.mailCode != 0 && !Objects.equals(this.nickName, "")
                && !Objects.equals(this.password, "");
    }

    /**
     * mailCodeIsMatch 邮箱验证码是否匹配。
     * */
    public boolean mailCodeIsNotMatch(int targetMailCode){
        return this.mailCode != targetMailCode;
    }

}
