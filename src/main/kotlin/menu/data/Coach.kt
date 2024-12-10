package menu.data

data class Coach(val name : String){
    val uneatableMenu : MutableList<String> = mutableListOf()

    fun addUneatableMenu(menu:String){
        uneatableMenu.add(menu)
    }
}
