package medicalScheduler.main

import com.github.ajalt.mordant.terminal.Terminal
import mu.KotlinLogging
import com.github.ajalt.mordant.terminal.TextColors.*
import com.github.ajalt.mordant.terminal.TextStyles.*
import medicalScheduler.controllers.MenuController
import medicalScheduler.models.*
//import medicalScheduler.models.getId
import medicalScheduler.views.MenuView


val t = Terminal()
private val logger = KotlinLogging.logger {}

//Text styles for medicalScheduler.main.menu
val title = (bold+green+underline)
val option = (yellow)
val error = (brightYellow on red)

val menuController = MenuController()


fun main(args: Array<String>) {
    logger.info { "Launching Medical Scheduler App" }
    menuController.start()

}

