package com.xiaoya.yidiantong.utils;

/**
 * Author: meyu
 * Date:   16/3/2
 * Email:  627655140@qq.com
 */
public interface Constant {

    String MOB_APPKEY = "10f1429cf21e2";
    String MOB_APP_SERCRET = "cf496953f90fabdf28669a8158ffacad";


    String EXTRA_NOTIFY_DELETE = "extra_notify_delete";
    String EXTRA_NOTIFY_CONFIRM = "extra_notify_confirm";
    String EXTRA_NOTIFY_NOTIFICATION = "extra_notify_notification";
    String EXTRA_NOTIFY_ALARM_TIME = "extra_notify_alarm_time";

    String KEY_USER_HAS_COMMENT = "key_user_has_comment";
    String KEY_USER_COMMENT_TIME = "key_user_comment_time";
    String KEY_EXERCISE_TIME = "key_exercise_time";

    String EXTRA_NOTIFY_ADD = "extra_notify_add";
    String EXTRA_NOTIFY_MODIFY = "extra_notify_modify";

    String EXTRA_FROM_LOGIN_ACTIVITY = "extra_from_login_activity";

    String EXTRA_TAG_FRAGMENT = "extra_tag_fragment";
    String EXTRA_TAG_FRAGMENT_VIDEO_START_OR_STOP = "extra_tag_fragment_video_start_or_stop";
    String EXTRA_TAG_FRAGMENT_ANMI_START_OR_STOP = "extra_tag_fragment_anmi_start_or_stop";
    int ANIM_HIDE = 0;
    int ANIM_SHOW = 1;
    int EXTRA_TAG_FRAGMENT_VIDEO = 0x100;
    int EXTRA_TAG_FRAGMENT_MAIN = 0x101;

    String USER_PLAY_TIME = "user_play_time";
    String VIDEO_PALY_PROCESS_TAG = "video_paly_process_tag";


    String EXTRA_TAG_ACTION_INTENT = "extra_tag_action_intent";
    int EXTRA_TAG_ACTION_0 = 0x00;
    int EXTRA_TAG_ACTION_1 = 0x01;
    int EXTRA_TAG_ACTION_2 = 0x02;
    int EXTRA_TAG_ACTION_3 = 0x03;
    int EXTRA_TAG_ACTION_4 = 0x04;
    int EXTRA_TAG_ACTION_5 = 0x05;
    int EXTRA_TAG_ACTION_6 = 0x06;
    int EXTRA_TAG_ACTION_7 = 0x07;
    int EXTRA_TAG_ACTION_8 = 0x08;

    //定义视频每个动作的时间点（秒）
    int time_video_action_1 = 25000;
    int time_video_action_2 = 106000;
    int time_video_action_3 = 207000;
    int time_video_action_4 = 287000;
    int time_video_action_5 = 364000;
    int time_video_action_6 = 459000;
    int time_video_action_7 = 590000;
    int time_video_action_8 = 660000;

    int time_video_action_t = 732000;

    int [] TAG_POSITIONS = {
            time_video_action_1 * 100 / time_video_action_t,
            time_video_action_2 * 100 / time_video_action_t,
            time_video_action_3 * 100 / time_video_action_t,
            time_video_action_4 * 100 / time_video_action_t,
            time_video_action_5 * 100 / time_video_action_t,
            time_video_action_6 * 100 / time_video_action_t,
            time_video_action_7 * 100 / time_video_action_t,
            time_video_action_8 * 100 / time_video_action_t};

}
