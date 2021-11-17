package com.github.kancyframework.dingtalk.enums;

/**
 * MsgTypeEnum
 *
 * @author huangchengkang
 * @date 2021/11/16 23:14
 */
public enum MsgTypeEnum {
    TEXT("text", "普通文本"),
    MARKDOWN("markdown", "markdown富文本"),
    LINK("link", "链接消息"),
    ACTION_CARD("actionCard", "actionCard"),
    FEED_CARD("feedCard", "feedCard");

    MsgTypeEnum(String type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    /**
     * 类型
     */
    private String type;

    /**
     * 描述
     */
    private String desc;

    public String getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }
}
