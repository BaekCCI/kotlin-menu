package menu

import net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.Listener.ErrorEscalating

const val MIN_NUMBER_OF_COACH = 2
const val MAX_NUMBER_OF_COACH = 5
const val MIN_NAME_LENGTH = 2
const val MAX_NAME_LENGTH = 4
const val MAX_UNEATABLE_MENU = 2


enum class ErrorMessage(val message: String) {
    COACH_COUNT_OUT_OF_RANGE("코치는 2~5명까지 입력 가능합니다."),
    NAME_LENGTH_OUT_OF_RANGE("이름은 2~4글자여야 합니다."),
    DUPLICATE_NAME("중복된 이름이 입력되었습니다."),
    MENU_COUNT_OUT_OF_RANGE("메뉴는 최대 2개까지 입력가능합니다."),
    DUPLICATE_MENU("중복된 메뉴가 입력되었습니다.");

    fun format(): String {
        return message
    }
}

class Validator {
    fun validName(input: String) {
        val coaches = input.split(",").map { it.replace(" ", "") }
        require(coaches.size in MIN_NUMBER_OF_COACH..MAX_NUMBER_OF_COACH) { ErrorMessage.COACH_COUNT_OUT_OF_RANGE.format() }
        coaches.forEach {
            require(it.length in MIN_NAME_LENGTH..MAX_NAME_LENGTH) { ErrorMessage.NAME_LENGTH_OUT_OF_RANGE.format() }
        }
        require(coaches.size == coaches.distinct().size) { ErrorMessage.DUPLICATE_NAME.format() }
    }

    fun validUneatableMenu(input: String) {
        val menus = input.split(",").map { it.trim() }
        require(menus.size <= MAX_UNEATABLE_MENU) { ErrorMessage.MENU_COUNT_OUT_OF_RANGE.format() }
        require(menus.size == menus.distinct().size) { ErrorMessage.DUPLICATE_MENU.format() }
    }
}