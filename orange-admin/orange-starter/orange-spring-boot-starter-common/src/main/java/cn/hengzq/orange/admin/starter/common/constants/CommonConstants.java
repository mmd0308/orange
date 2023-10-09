package cn.hengzq.orange.admin.starter.common.constants;

/**
 * @author 程序员橙子
 */
public interface CommonConstants {

    interface Common {
        /**
         * 默认父级ID
         */
        Long DEFAULT_PARENT_ID = -1L;


        String REST_API_URL_PREFIX = "/rest/orange";

        /**
         * 新用户默认密码
         */
        String DEFAULT_ADMIN_PASSWORD = "orange";
    }


    /**
     * 分页参数
     */
    interface Page {
        /**
         * 页码
         */
        Integer PAGE_NO = 1;

        /**
         * 每页数量
         */
        Integer PAGE_SIZE = 10;

        /**
         * 数量
         */
        Integer TOTAL = 0;
    }

}
