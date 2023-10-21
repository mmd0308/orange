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


  interface InfoTypeAllQuery {
    parentId?: number;
    name?: string;
    nameLike?: string
  };
}