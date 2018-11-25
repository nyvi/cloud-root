package com.github.cloud.daemon.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.cloud.daemon.biz.domain.request.SearchRequest;
import com.github.cloud.daemon.biz.domain.vo.QuartzVO;
import org.apache.ibatis.annotations.Param;

/**
 * @author : czk
 * @date 2018-11-24
 */
public interface DaemonMapper extends BaseMapper<QuartzVO> {

    /**
     * 分页查询
     *
     * @param page    分页参数
     * @param request 请求参数
     * @return 用户列表
     */
    IPage<QuartzVO> listPage(Page page, @Param("pm") SearchRequest request);
}
