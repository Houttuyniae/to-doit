package io.todoit.common.bean;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import org.springframework.util.StringUtils;

import java.util.HashMap;

/**
 * @author:zhangd
 * @date:2019/4/4 11:17
 */
@Data
public class PageQuery{

    /**
     * 当前页
     */
    private Long currPage;

    /**
     * 每页展示多少条
     */
    private Long limit;

    /**
     * 正序排序字段
     */
    private String ascs;
    /**
     * 反序排序字段
     */
    private String descs;


    public Long getCurrPage() {
        return currPage > 0L ? currPage : 1L;
    }

    public void setCurrPage(Long currPage) {
        this.currPage = currPage;
    }

    public Long getLimit() {
        return limit >= 10L ? limit : 10L;
    }

    public void setLimit(Long limit) {
        this.limit = limit;
    }


    /**
     * 设置分页参数格式
     * @param clazz
     * @return
     */
    public IPage<?> getPage(Class<? extends PageQuery> clazz){

        Page<?> page = new Page<>(this.getCurrPage(),this.getCurrPage());
        if(StringUtils.isEmpty(ascs)){
            page.setDesc(getDescs());
        }else {
            page.setDesc(getAscs());
        }
        return page;
    }

}
