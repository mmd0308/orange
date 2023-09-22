declare namespace SystemDictAPI {
  interface DictTypePageQuery extends DictTypeAllQuery {
    /** 查询页码 */
    pageNo?: number;
    /** 每页数量 */
    pageSize?: number;
  };

  interface DictTypeAllQuery {
    /** 字典名称 */
    name?: string;
    nameLike?: string;
    /** 字典类型 */
    dictType?: string;
  }

  interface DictTypeVO {
    /** 租户id */
    tenantId?: string;
    /** 主键 */
    id?: string;
    /** 字典名称 */
    name?: string;
    /** 字典类型 */
    dictType?: string;
    /** 状态 */
    status?: 'NORMAL' | 'DISABLE';
    /** 预设标记 */
    presetFlag?: 'PRESET' | 'CUSTOM';
    /** 备注 */
    remark?: string;
  };

  interface DictDataPageQuery extends DictDataAllQuery {
    /** 查询页码 */
    pageNo?: number;
    /** 每页数量 */
    pageSize?: number;
  };

  interface DictDataAllQuery {
    dictType?: string;
    presetFlag?: number;
    dictLabel?: string;
  }

  interface DictDataVO {
    /** 租户id */
    tenantId?: number;
    /** 主键 */
    id?: string;
    /** 排序 */
    sort?: number;
    /** 字典数据标签 */
    dictLabel?: string;
    /** 字典数据键值 */
    dictValue?: string;
    /** 字典类型 */
    dictType?: string;
    /** 状态 */
    status?: 'SUCCESS' | 'FAIL';
    /** 预设标记 */
    presetFlag?: 'PRESET' | 'CUSTOM';
    /** 回显样式 */
    showStyle?: string;
    /** 备注 */
    remark?: string;
  };
}