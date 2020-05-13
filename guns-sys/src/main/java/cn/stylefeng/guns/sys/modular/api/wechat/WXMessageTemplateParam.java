package cn.stylefeng.guns.sys.modular.api.wechat;

public class WXMessageTemplateParam {
    private String color;
    private String value;

    public WXMessageTemplateParam(String value,String color){
        this.color =color;
        this.value=value;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
