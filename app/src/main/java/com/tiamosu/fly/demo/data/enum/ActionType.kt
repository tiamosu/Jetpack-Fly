package com.tiamosu.fly.demo.data.enum

/**
 * @author ti
 * @date 2022/7/13.
 */
enum class ActionType(val type: Int) {
    NAVIGATION(0),          //Navigation 使用示例
    ACTIVITY_RESULT(1),     //ActivityResult 使用示例
    MAVERICKS(2),           //MAVERICKS 使用示例
    VIEW_BINDING(3),        //ViewBinding 使用示例
    DIALOG_FRAGMENT(4),     //DialogFragment 使用示例
    SHARED(5),              //SharedViewModel 使用示例
}