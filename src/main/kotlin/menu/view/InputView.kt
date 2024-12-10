package menu.view

import camp.nextstep.edu.missionutils.Console

enum class InputMessage(private val message: String) {
    COACH_NAME("코치의 이름을 입력해 주세요. (, 로 구분)"),
    UNEATABLE_MENU("%s(이)가 못 먹는 메뉴를 입력해 주세요.");

    fun format(vararg args: Any): String {
        return message.format(*args)
    }
}

class InputView {
    fun getCoaches(): String {
        println(InputMessage.COACH_NAME.format())
        return Console.readLine()
    }

    fun getUneatableMenus(name: String): String {
        println(InputMessage.UNEATABLE_MENU.format(name))
        return Console.readLine()
    }
}