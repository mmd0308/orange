package cn.hengzq.starter.storage.util;

import cn.hengzq.orange.admin.starter.context.GlobalContextHelper;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.file.FileNameUtil;
import cn.hutool.core.util.StrUtil;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * @author 程序员橙子
 */
public class StorageUtil {

    private static final List<String> IMAGE_EXTENSION = List.of("jpeg", "jpg", "bmp", "png");


    /**
     * 生成系统相对路径
     *
     * @return 返回生成的路径
     */
    public static String getSystemRelativePath() {
        return GlobalContextHelper.getTenantId() + File.separator + DateUtil.thisYear() + File.separator
                + DateUtil.thisMonth() + File.separator + DateUtil.thisDayOfMonth();
    }

    /**
     * 生成相对路径
     *
     * @return 返回生成的路径
     */
    public static String getRelativePath() {
        return GlobalContextHelper.getTenantId() + StrUtil.SLASH + DateUtil.thisYear() + StrUtil.SLASH
                + DateUtil.thisMonth() + StrUtil.SLASH + DateUtil.thisDayOfMonth();
    }

    /**
     * 获取新的文件名称
     *
     * @param fileName
     * @return
     */
    public static String getNewFileName(String fileName) {
        // 主文件名，不包含扩展名
        String prefix = FileNameUtil.getPrefix(fileName);
        // 文件扩展名
        String suffix = FileNameUtil.getSuffix(fileName);
        // 把当天HH:mm:ss，转换成秒
        long time = DateUtil.timeToSecond(DateUtil.formatTime(new Date()));
        // 新文件名
        return prefix + "_" + time + "." + suffix;
    }

    /**
     * 判断是否为图片
     *
     * @param fileName 文件名称
     * @return
     */
    public static Boolean isImage(String fileName) {
        if (StrUtil.isBlank(fileName)) {
            return Boolean.FALSE;
        }
        // 文件扩展名
        String suffix = FileNameUtil.getSuffix(fileName);
        return suffix == null || !IMAGE_EXTENSION.contains(suffix.toLowerCase(Locale.ROOT)) ? Boolean.FALSE :
                Boolean.TRUE;
    }
}
