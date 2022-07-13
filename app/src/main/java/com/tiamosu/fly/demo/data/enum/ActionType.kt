package com.tiamosu.fly.demo.data.enum

/**
 * @author ti
 * @date 2022/7/13.
 */
enum class ActionType(val type: Int) {
    NAVIGATION(0),      //Navigation 使用示例
    ACTIVITY_RESULT(1), //ActivityResult 使用示例
    VIEW_MODEL(2),      //ViewModel 使用示例
    VIEW_BINDING(3),    //ViewBinding 使用示例
}