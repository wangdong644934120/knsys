package cn.stylefeng.guns.sys.modular.api.wechat;

import java.util.Map;

public class WXMessageTemplate {
    private String touser;
    private String template_id;
    private String appid;
    private Map<String,WXMessageTemplateParam> data;

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public Map<String, WXMessageTemplateParam> getData() {
        return data;
    }

    public void setData(Map<String, WXMessageTemplateParam> data) {
        this.data = data;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String toJSON() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("{");
        buffer.append(String.format("\"touser\":\"%s\"", this.touser)).append(",");
        buffer.append(String.format("\"template_id\":\"%s\"", this.template_id)).append(",");
        buffer.append(String.format("\"appid\":\"%s\"", this.appid)).append(",");
        buffer.append("\"data\":{");
        WXMessageTemplateParam param = null;
        for (int i = 0; i < this.data.size(); i++) {
            param = data.get(i);
            // 判断是否追加逗号
            if (i < this.data.size() - 1) {
                buffer.append(String.format("\"%s\": {\"value\":\"%s\"},", param.getColor(), param.getValue()));
            } else {
                buffer.append(String.format("\"%s\": {\"value\":\"%s\"}", param.getColor(), param.getValue()));
            }
        }
        buffer.append("}");
        buffer.append("}");
        return buffer.toString();
    }

}
