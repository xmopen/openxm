package cn.openxm.bloguser.infrastructure.dao;

import cn.openxm.bloguser.domain.user.model.OpenXmUserDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * author Xiao Ma
 * date 2024/6/25
 */
@Mapper
@Repository
public interface OpenXmUserDao extends BaseMapper<OpenXmUserDO> {
}
