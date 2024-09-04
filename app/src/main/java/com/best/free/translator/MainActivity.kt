package com.best.free.translator

import android.R
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.best.free.translator.databinding.ActivityMainBinding
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.TranslatorOptions

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val REQUEST_CODE = 101
    private val languagesCode = 0
    private lateinit var sourceLanguagesCode: String
    private lateinit var targetLanguageCode: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        //set spinner
        val adapter = ArrayAdapter(this, R.layout.simple_spinner_item, Constants.languages)
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        binding.spinnerSourceLanguage.adapter = adapter
        binding.spinnerTargetLanguage.adapter = adapter

        //set default language
        val position1 = adapter.getPosition(Constants.languages[9])
        val position2 = adapter.getPosition(Constants.languages[3])
        binding.spinnerSourceLanguage.setSelection(position1)
        binding.spinnerTargetLanguage.setSelection(position2)

        binding.btnTranslate.setOnClickListener {
            sourceLanguagesCode =
                getLanguageCode(binding.spinnerSourceLanguage.selectedItem.toString())
            targetLanguageCode =
                getLanguageCode(binding.spinnerTargetLanguage.selectedItem.toString())

            if (binding.tvSourceLanguage.text.toString().isNotEmpty()) {
                translateText(sourceLanguagesCode, targetLanguageCode)
            }
        }

    }

    private fun translateText(sourceLanguages: String, targetLanguage: String) {
        binding.tvTargetLanguage.text = "Downloading... language model"

        val options = TranslatorOptions.Builder()
            .setSourceLanguage(sourceLanguages)
            .setTargetLanguage(targetLanguage)
            .build()
        val translator = Translation.getClient(options)
        translator.downloadModelIfNeeded()
            .addOnSuccessListener {
                translator.translate(binding.tvSourceLanguage.text.toString())
                    .addOnSuccessListener {
                        binding.tvTargetLanguage.text = it
                    }
            }
    }

    private fun getLanguageCode(languages: String): String {
        var languageCode = ""
        when (languages) {
            "Afrikaans" -> languageCode = TranslateLanguage.AFRIKAANS
            "Albanian" -> languageCode = TranslateLanguage.ALBANIAN
            "Arabic" -> languageCode = TranslateLanguage.ARABIC
            "Bengali" -> languageCode = TranslateLanguage.BENGALI
            "Bulgarian" -> languageCode = TranslateLanguage.BULGARIAN
            "Czech" -> languageCode = TranslateLanguage.CZECH
            "Chinese" -> languageCode = TranslateLanguage.CHINESE
            "Danish" -> languageCode = TranslateLanguage.DANISH
            "Dutch" -> languageCode = TranslateLanguage.DUTCH
            "English" -> languageCode = TranslateLanguage.ENGLISH
            "Esperanto" -> languageCode = TranslateLanguage.ESPERANTO
            "Estonian" -> languageCode = TranslateLanguage.ESTONIAN
            "Finnish" -> languageCode = TranslateLanguage.FINNISH
            "French" -> languageCode = TranslateLanguage.FRENCH
            "German" -> languageCode = TranslateLanguage.GERMAN
            "Greek" -> languageCode = TranslateLanguage.GREEK
            "Gujarati" -> languageCode = TranslateLanguage.GUJARATI
            "Hebrew" -> languageCode = TranslateLanguage.HEBREW
            "Hindi" -> languageCode = TranslateLanguage.HINDI
            "Hungarian" -> languageCode = TranslateLanguage.HUNGARIAN
            "Italian" -> languageCode = TranslateLanguage.ITALIAN
            "Irish" -> languageCode = TranslateLanguage.IRISH
            "Indonesian" -> languageCode = TranslateLanguage.INDONESIAN
            "Japanese" -> languageCode = TranslateLanguage.JAPANESE
            "Korean" -> languageCode = TranslateLanguage.KOREAN
            "Lithuanian" -> languageCode = TranslateLanguage.LITHUANIAN
            "Latvian" -> languageCode = TranslateLanguage.LATVIAN
            "Malay" -> languageCode = TranslateLanguage.MALAY
            "Marathi" -> languageCode = TranslateLanguage.MARATHI
            "Norwegian" -> languageCode = TranslateLanguage.NORWEGIAN
            "Polish" -> languageCode = TranslateLanguage.POLISH
            "Portuguese" -> languageCode = TranslateLanguage.PORTUGUESE
            "Persian" -> languageCode = TranslateLanguage.PERSIAN
            "Romanian" -> languageCode = TranslateLanguage.ROMANIAN
            "Russian" -> languageCode = TranslateLanguage.RUSSIAN
            "Spanish" -> languageCode = TranslateLanguage.SPANISH
            "Swedish" -> languageCode = TranslateLanguage.SWEDISH
            "Tamil" -> languageCode = TranslateLanguage.TAMIL
            "Telugu" -> languageCode = TranslateLanguage.TELUGU
            "Thai" -> languageCode = TranslateLanguage.THAI
            "Turkish" -> languageCode = TranslateLanguage.TURKISH
            "Ukrainian" -> languageCode = TranslateLanguage.UKRAINIAN
            "Urdu" -> languageCode = TranslateLanguage.URDU
            "Vietnamese" -> languageCode = TranslateLanguage.VIETNAMESE
        }
        return languageCode
    }
}