package menu.model

import camp.nextstep.edu.missionutils.Randoms
import menu.ErrorMessage
import menu.MAX_NUMBER_OF_COACH
import menu.MIN_NUMBER_OF_COACH
import menu.data.Coach
import menu.data.Menu
import menu.data.RecommendedResult

const val MENU_PATH = "src/main/kotlin/menu/source/Menu.md"

class RecommendationMachine() {
    val menus: MutableList<Menu> = mutableListOf()
    val coaches: MutableList<Coach> = mutableListOf()
    val recommendedCategory: MutableList<String> = mutableListOf()
    val recommendedResults: MutableList<RecommendedResult> = mutableListOf()

    init {
        loadMenu()
    }

    fun loadMenu() {
        val loadedData = java.io.File(MENU_PATH).readLines()
        loadedData.forEach { data ->
            val parseData = data.split(":").map { it.trim() }
            setMenu(parseData[0], parseData[1])
        }
    }

    fun setMenu(category: String, items: String) {
        val parseItem = items.split(",").map { it.trim() }
        menus.add(
            Menu(
                category = category,
                dishes = parseItem
            )
        )
    }

    fun setCoach(names: List<String>) {
        names.forEach {
            coaches.add(
                Coach(it)
            )
        }
    }

    fun getCoach(): List<Coach> {
        return coaches
    }

    fun startRecommend() {
        setRecommendedCategory()
        setRecommendedResult(recommendedCategory)

    }

    fun setRecommendedCategory(): List<String> {
        while (recommendedCategory.size < 5) {
            var randomCategory: String
            do {
                randomCategory = getRandomCategory()
                val duplicateCategory = recommendedCategory.filter { it == randomCategory }
            } while (duplicateCategory.size >= 2)
            recommendedCategory.add(randomCategory)
        }
        return recommendedCategory
    }

    fun getRandomCategory(): String {
        return when (Randoms.pickNumberInRange(1, 5)) {
            1 -> "일식"
            2 -> "한식"
            3 -> "중식"
            4 -> "아시안"
            else -> {
                "양식"
            }
        }
    }


    fun setRecommendedResult(recommendedCategories: List<String>): List<RecommendedResult> {
        coaches.forEach { coach ->
            recommendedResults.add(
                RecommendedResult(
                    coach = coach.name,
                    menus = getMenu(coach, recommendedCategories)
                )
            )
        }
        return recommendedResults
    }

    fun getMenu(coach: Coach, recommendedCategories: List<String>): List<String> {
        val recommendedMenus: MutableList<String> = mutableListOf()
        var randomMenu: String
        recommendedCategories.forEach { category ->
            do {
                randomMenu = getRandomMenu(category)
            } while (recommendedMenus.contains(randomMenu) || checkIsUneatableMenu(coach, randomMenu))
            recommendedMenus.add(randomMenu)
        }
        return recommendedMenus
    }

    fun checkIsUneatableMenu(coach: Coach, randomMenu: String): Boolean {
        return coach.uneatableMenu.contains(randomMenu)
    }

    fun getRandomMenu(category: String): String {
        val dish = menus.find { it.category == category }!!.dishes
        val menu = Randoms.shuffle(dish)
        return menu[0]
    }

    fun getRecommendCategory(): List<String> {
        return recommendedCategory
    }

    fun getRecommendResult(): List<RecommendedResult> {
        return recommendedResults
    }


}