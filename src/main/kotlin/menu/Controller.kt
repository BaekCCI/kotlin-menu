package menu

import menu.data.Coach
import menu.data.Menu
import menu.model.RecommendationMachine
import menu.view.InputView
import menu.view.OutputView

class Controller {
    val recommendationMachine = RecommendationMachine()
    val validator = Validator()
    val inputView = InputView()
    val outputView = OutputView()
    fun start() {
        outputView.displayServiceStart()
        setCoaches()
        setUneatableMenu()
        recommendationMachine.startRecommend()
        displayResult()
    }

    fun setCoaches() {
        val coach = getCoaches()
        recommendationMachine.setCoach(coach)
    }
    fun getCoaches():List<String>{
        while (true) {
            try {
                val input = inputView.getCoaches()
                validator.validName(input)
                return input.split(",").map { it.replace(" ", "") }

            } catch (e: IllegalArgumentException) {
                println(e.message)
            }
        }
    }

    fun setUneatableMenu() {
        val coaches = recommendationMachine.getCoach()
        coaches.forEach {
            val input = getUneatableMenu(it)
            addUneatableMenuToCoach(it, input)
        }
    }

    fun getUneatableMenu(coach: Coach): String {
        while (true) {
            try {
                val input = inputView.getUneatableMenus(coach.name)
                validator.validUneatableMenu(input)
                return input
            } catch (e: IllegalArgumentException) {
                println(e.message)
            }
        }
    }

    fun addUneatableMenuToCoach(coach: Coach, input: String) {
        val menus = input.split(",").map { it.trim() }
        menus.forEach {
            coach.addUneatableMenu(it)
        }
    }

    fun displayResult() {
        outputView.displayResultTitle()
        outputView.displayRecommendedCategory(recommendationMachine.getRecommendCategory())
        val recommendedResult = recommendationMachine.getRecommendResult()
        recommendedResult.forEach {
            outputView.displayRecommendedMenu(it.coach, it.menus)
        }
        outputView.displayEnd()
    }

}