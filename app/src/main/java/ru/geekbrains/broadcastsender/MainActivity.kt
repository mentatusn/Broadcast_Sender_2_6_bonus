package ru.geekbrains.broadcastsender

import android.R
import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import ru.geekbrains.broadcastsender.databinding.ActivityMainBinding

const val ACTION_SEND_TITLE = "SEND_TITLE"
const val SEND_TITLE = "TITLE"

class MainActivity : AppCompatActivity() {

    companion object {
        // Имя ACTION для Broadcast, по нему Receiver и будет определяться
        const val ACTION_SEND_MSG: String = "broadcastsender.message"

        // Имя передаваемого параметра
        const val NAME_MSG: String = "MSG"

        // Эта константа спрятана в Intent классе,
        // Но, именно посредством её можно поднять приложение
        const val FLAG_RECEIVER_INCLUDE_BACKGROUND: Int = 0x01000000
    }

    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_item)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initButtonSend()
    }



    @SuppressLint("WrongConstant")
    private fun initButtonSend() {
        val message = "message"
        binding.btnSent.setOnClickListener {
            // Формируем интент
            val msg: String = message.toString()
            val intent = Intent()
            // Укажем ACTION, по которому будем ловить сообщение
            intent.action = ACTION_SEND_MSG
            // Добавим параметр.
            intent.putExtra(NAME_MSG, msg)
            // Указываем флаг поднятия приложения
            // (без него будут получать уведомления только
            // загруженные приложения)
            intent.addFlags(FLAG_RECEIVER_INCLUDE_BACKGROUND)
            // Отправка сообщения
            sendBroadcast(intent)
        }

    }
}