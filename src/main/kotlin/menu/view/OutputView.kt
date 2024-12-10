package menu.view

enum class OutputMessage(val message: String) {
    SERVICE_START("점심 메뉴 추천을 시작합니다."),
    RESULT_TITLE("메뉴 추천 결과입니다.\n[ 구분 | 월요일 | 화요일 | 수요일 | 목요일 | 금요일 ]"),
    RECOMMENDED_CATEGORY("[ 카테코리 | %s | %s | %s | %s | %s ]"),
    RECOMMENDED_MENU_FOR_COACH("[ %s | %s | %s | %s | %s | %s ]"),
    END("추천을 완료했습니다.");

    fun format(vararg args: Any): String {
        return message.format(*args)
    }
}

class OutputView {
    fun displayServiceStart() {
        println(OutputMessage.SERVICE_START.format())
    }

    fun displayResultTitle() {
        println(OutputMessage.RESULT_TITLE.format())
    }

    fun displayRecommendedCategory(categories: List<String>) {
        println(
            OutputMessage.RECOMMENDED_CATEGORY.format(
                categories[0],
                categories[1],
                categories[2],
                categories[3],
                categories[4]
            )
        )
    }

    fun displayRecommendedMenu(coach: String, menu: List<String>) {
        println(
            OutputMessage.RECOMMENDED_MENU_FOR_COACH.format(
                coach, menu[0], menu[1], menu[2], menu[3], menu[4]
            )
        )
    }
    fun displayEnd(){
        println(OutputMessage.END.format())
    }
}