package com.xiaoya.yidiantong.model;

/**
 * Author: meyu
 * Date:   16/5/27
 * Email:  627655140@qq.com
 */
public class VideoDetail {

    private String passDemand;
    private String criterion;
    private String passStrategy;

    public String getCriterion() {
        return criterion;
    }

    public void setCriterion(String criterion) {
        this.criterion = criterion;
    }

    public String getPassDemand() {
        return passDemand;
    }

    public void setPassDemand(String passDemand) {
        this.passDemand = passDemand;
    }

    public String getPassStrategy() {
        return passStrategy;
    }

    public void setPassStrategy(String passStrategy) {
        this.passStrategy = passStrategy;
    }

    @Override
    public String toString() {
        return "VideoDetail{" +
                "criterion='" + criterion + '\'' +
                ", passDemand='" + passDemand + '\'' +
                ", passStrategy='" + passStrategy + '\'' +
                '}';
    }

    public static final String VIDEO_TEST="<info type=\"videos_detail\" error=\"0\" detail=\"success\">\n" +
            "<video passDemand=\"中间不能停顿，彻底否定了电子桩考试中，原地打死方向，对杆辨认标识的做法。\" criterion=\"● 不按规定路线、顺序行驶的，不合格；<br/>● 车身出线的，不合格；<br/>● 倒库不入的，不合格；<br/>● 中途停车的，不合格；<br/>● 超过4分钟，不合格。\" passStrategy=\"● 将车停在车位线旁1.5米处；<br/>● 当车尾处于差不多两个车位宽度的边线时，方向盘打满,往后倒车；<br/>● 感觉车头正时，方向盘马上回正倒车。<br/>● 充分利用车头某一参照物，当参照物位于道中间或一侧时，应能正确感觉到车辆所在位置，以确定打方向或回方向的时机及幅度大小；<br/>● 要充分利用两眼余光作用，克服眼睛看远不顾近而造成修正方向较晚的现象；<br/>● 养成车头不偏斜就不动方向盘的良好习惯。在修正方向时要做到“及时”二字，且幅度适中，一般在回正方向时应稍早，且幅度也要小；<br/>● 结合场地训练，训练转向的时机和速度，如8字形、S形和直角转弯；<br/>● 车辆在右侧行驶时，为防止车向右头偏斜，应将方向盘左转至无游动间隙，以便左手能感觉车辆此时所在位置。<br/> 注：在考试前多练习找找手感，这样会更容易上手哦。\"></video>\n" +
            "</info>";
}
