package cn.openxm.bloguser.infrastructure.dao;

import cn.openxm.bloguser.domain.user.model.OpenXmUserDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author Xiao Ma
 * @date 2024/6/25
 * @slogan 少年应有鸿鹄志，当骑骏马踏平川。
 */
@Mapper
@Repository
public interface OpenXmUserDao extends BaseMapper<OpenXmUserDO> {
}
