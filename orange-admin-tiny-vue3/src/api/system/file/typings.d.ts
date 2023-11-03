declare namespace SystemFileAPI {

  interface FileUploadRequest {
    file: File;
    path?: string;
  };

  interface FileVO {
    tenantId?: string;
    id?: string;
    configId?: string;
    name?: string;
    originalName?: string;
    path?: string;
    type?: string;
    size?: string
    url?: string;
  };

  interface FilePageQuery extends FileAllQuery {
    pageNo?: number;
    pageSize?: number;
  };

  interface FileAllQuery {
    nameLike?: string;
    createdStartTime?: string;
    createdEndTime?: string;
  }


  interface ConfigVO {
    tenantId?: string;
    id?: string;
    name?: string;
    storage?: string;
    domain?: string;
    basePath?: string;
    endPoint?: string;
    bucketName?: string;
    accessKey?: string;
    accessKeySecret?: string;
    master?: boolean;
    remark?: string;
  };

  interface ConfigPageQuery extends ConfigAllQuery {
    pageNo?: number;
    pageSize?: number;
  };

  interface ConfigAllQuery {
    titleLike?: string;
  }

  interface Storage {
    key?: string;
    name?: string;
  }
}
