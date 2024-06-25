package cn.openxm.bloguser.domain.user.model;

import lombok.Builder;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * Entity 可以理解为 具有唯一标识符的实体，其初始化可以理解为从DO中转换而来，但是其状态会随着业务流程的处理而改变，
 * 最终交给Repository进行持久化。
 *
 *  其最终通过Builder建造者模式对外提供实例，从而提供服务，其提供的服务都是单例。
 *  TODO: 是否自己可以实现Builder注解？然后通过内存池来进行实现？
 *
 * author Xiao Ma
 * date 2024/6/26
 */
@Builder
@ToString
public class OpenXmUserEntity implements Serializable {

    private Long id;

    private String userId;



}
