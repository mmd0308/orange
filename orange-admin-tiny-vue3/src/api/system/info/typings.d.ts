declare namespace SystemInfoAPI {

  interface InfoTypeVO {
    tenantId?: string;
    id?: string;
    parentId?: string;
    name?: string;
    code?: string;
    sort?: number;
    presetFlag?: 'PRESET' | 'CUSTOM';
    remark?: string;
  };

  interface InfoTypeTreeVO {
    tenantId?: string;
    id?: string;
    parentId?: string;
    name?: string;
    code?: string;
    sort?: number;
    presetFlag?: 'PRESET' | 'CUSTOM';
    remark?: string;
    children?: DepartmentTreeVO[];
  };

  interface InfoTypeAllQuery {
    parentId?: number;
    name?: string;
    nameLike?: string
  };

  interface InfoVO {
    tenantId?: string;
    id?: string;
    typeId?: string;
    typeName?: string;
    title?: string;
    content?: string;
    sort?: number;
  };

  interface InfoPageQuery extends InfoAllQuery {
    pageNo?: number;
    pageSize?: number;
  };

  interface InfoAllQuery {
    titleLike?: string;
  }
}
