package cn.hengzq.orange.admin.starter.common.vo;

import cn.hengzq.orange.admin.starter.common.constant.PageConstant;
import cn.hengzq.orange.admin.starter.common.vo.query.PageQuery;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * @author 程序员橙子
 */
public class PageVO<T> implements Serializable {

    @Schema(description = "列表数据", accessMode = Schema.AccessMode.READ_ONLY)
    private List<T> records;

    @Schema(description = "列表总数", accessMode = Schema.AccessMode.READ_ONLY)
    private Integer total;

    @Schema(description = "每页数量", accessMode = Schema.AccessMode.READ_ONLY)
    private Integer pageSize;

    @Schema(description = "页码", accessMode = Schema.AccessMode.READ_ONLY)
    private Integer pageNo;

    public PageVO() {
    }

    public PageVO(Integer pageNo, Integer pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public PageVO(PageQuery queryVo) {
        this.pageNo = queryVo.getPageNo();
        this.pageSize = queryVo.getPageSize();
        this.total = 0;
    }

    public List<T> getRecords() {
        return records == null ? Collections.emptyList() : records;
    }

    public Integer getTotal() {
        return total == null ? PageConstant.TOTAL : total;
    }

    public Integer getPageSize() {
        return pageSize == null ? PageConstant.PAGE_SIZE : pageSize;
    }

    public Integer getPageNo() {
        return pageNo == null ? PageConstant.PAGE_NO : pageNo;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }
}
