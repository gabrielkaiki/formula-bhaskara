package com.gabrielkaiki.formuladebhaskara

import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gabrielkaiki.formuladebhaskara.databinding.ActivityMainBinding
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.interstitial.InterstitialAd
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.math.pow
import kotlin.math.roundToInt
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {
    private var mAdView: AdView? = null
    private lateinit var binding: ActivityMainBinding
    private lateinit var valorA: EditText
    private lateinit var valorB: EditText
    private lateinit var valorC: EditText
    private lateinit var botaoCalcular: Button
    private lateinit var botaoLimpar: Button
    private lateinit var bhaskara1: TextView
    private lateinit var linearCalculos: LinearLayout
    private lateinit var formula2Denominador1: TextView
    private lateinit var formula2Numerador1: TextView
    private lateinit var formula2Denominador2: TextView
    private lateinit var formula2Numerador2: TextView
    private lateinit var formula2Denominador3: TextView
    private lateinit var formula2Numerador3: TextView
    private lateinit var formula2Denominador4: TextView
    private lateinit var formula2Numerador4: TextView
    private lateinit var formula2Denominador5: TextView
    private lateinit var formula2Numerador5: TextView
    private lateinit var formula2Denominador6: TextView
    private lateinit var formula2Numerador6: TextView
    private lateinit var formula2Denominador7: TextView
    private lateinit var formula2Numerador7: TextView
    private lateinit var formula2Denominador8: TextView
    private lateinit var formula2Numerador8: TextView
    private lateinit var formula2Denominador9: TextView
    private lateinit var formula2Numerador9: TextView
    private lateinit var formula2x1: TextView
    private lateinit var formula2x2: TextView
    private lateinit var formula2x3: TextView
    private lateinit var formula2x4: TextView
    private lateinit var formula2x5: TextView
    private lateinit var formula2x6: TextView
    private lateinit var formula2x7: TextView
    private lateinit var formula2x8: TextView
    private lateinit var formula2x9: TextView
    private lateinit var textoVazio1: TextView
    private lateinit var textoVazio2: TextView
    private lateinit var textoVazio3: TextView
    private lateinit var textoVazio4: TextView
    private lateinit var textoVazio5: TextView
    private lateinit var textoVazio6: TextView
    private lateinit var textoVazio7: TextView
    private lateinit var textoVazio8: TextView
    private lateinit var textoVazio9: TextView
    private lateinit var decimalFormat: DecimalFormat
    private lateinit var adView: AdView
    private var mInterstitialAd: InterstitialAd? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        MobileAds.initialize(this)
        carregarAnuncio()

        //Inicializar componentes
        inicializarComponentes()

        //Eventos de clique
        adicionarEventosDeClique()
    }

    private fun carregarAnuncio() {
        val adRequest = AdRequest.Builder().build()
        mAdView = binding.adView
        mAdView?.loadAd(adRequest)
    }

    private fun adicionarEventosDeClique() {
        botaoCalcular.setOnClickListener {
            validarCampos()
        }

        botaoLimpar.setOnClickListener {
            limparCampos()
        }
    }

    private fun limparCampos() {
        valorA.setText("")
        valorB.setText("")
        valorC.setText("")
        bhaskara1.text = ""

        val linearLayoutPai = linearCalculos.getChildAt(1) as LinearLayout
        for (i in 0 until linearLayoutPai.childCount) {
            val linearLayoutFilho = linearLayoutPai.getChildAt(i) as LinearLayout
            val texto1 = linearLayoutFilho.getChildAt(0) as TextView
            val texto2 = linearLayoutFilho.getChildAt(1) as TextView
            texto1.text = ""
            texto2.text = ""
        }

        carregarAnuncio()
    }

    private fun validarCampos() {
        val a = valorA.text
        val b = valorB.text
        val c = valorC.text

        if (!a.isNullOrEmpty()) {
            if (!b.isNullOrEmpty()) {
                if (!c.isNullOrEmpty()) {
                    calcular()
                } else {
                    Toast.makeText(this, "Please, enter the value of C!", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please, enter the value of B!", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Please, enter the value of A!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun calcular() {
        var a = valorA.text.toString()
        var b = valorB.text.toString()
        var c = valorC.text.toString()

        a = verificarSimboloDivisao(a)
        b = verificarSimboloDivisao(b)
        c = verificarSimboloDivisao(c)

        val resultadoMultiplicacao =
            multiplicacao(4, a.toDouble(), c.toDouble())
        val resultadoExponenciacao = exponenciacao(b)
        val delta = subtracao(resultadoExponenciacao, resultadoMultiplicacao)

        bhaskara1.text = "Δ = b² - 4.a.c \n" +
                "Δ = (${b})² - 4 . ${isNegative(a.toDouble())} . ${isNegative(c.toDouble())}\n" +
                "Δ = ${formatarResultado(resultadoExponenciacao)} - ${
                    isNegative(
                        resultadoMultiplicacao
                    )
                }\n" +
                "Δ = ${formatarResultado(delta)}"

        if (delta > 0) calcularSegundaFormulaDeBhaskara(delta, a, b)
    }

    private fun calcularSegundaFormulaDeBhaskara(delta: Double, a: String, b: String) {

        formula2x1.text = "x = "
        textoVazio1.text = "x = "
        formula2Denominador1.text = "-b + - √Δ"
        adicinarLinhaAbaixoDeTexto(formula2Denominador1)
        formula2Numerador1.text = "    2 . a"

        formula2x2.text = "x = "
        textoVazio2.text = "x = "
        formula2Denominador2.text = "-${isNegative(b.toDouble())} + - √${formatarResultado(delta)}"
        adicinarLinhaAbaixoDeTexto(formula2Denominador2)
        formula2Numerador2.text = "2 . ${isNegative(a.toDouble())}"

        val raizDelta = raizQuadrada(delta)

        formula2x3.text = "x = "
        textoVazio3.text = "x = "
        formula2Denominador3.text =
            "-${isNegative(b.toDouble())} + - (${formatarResultado(raizDelta)})"
        adicinarLinhaAbaixoDeTexto(formula2Denominador3)
        val multiplicacaoNumerador = 2 * a.toDouble()

        formula2Numerador3.text = "${formatarResultado(multiplicacaoNumerador)}"

        calcularRaizX1(b, raizDelta, multiplicacaoNumerador)
    }

    private fun formatarResultado(x: Double): String {
        return if (!isDecimal(x)) {
            x.roundToInt().toString()
        } else {
            x.toString()
        }
    }

    private fun adicinarLinhaAbaixoDeTexto(textview: TextView) {
        textview.paintFlags =
            textview.paintFlags or Paint.UNDERLINE_TEXT_FLAG
    }

    private fun calcularRaizX1(
        b: String,
        raizDelta: Double,
        auxiliarMultiplicacaoNumerador: Double
    ) {
        formula2x4.text = "x' = "
        textoVazio4.text = "x' = "
        formula2Denominador4.text =
            "-${isNegative(b.toDouble())} + ${formatarResultado(raizDelta)}"
        adicinarLinhaAbaixoDeTexto(formula2Denominador4)
        formula2Numerador4.text = formatarResultado(auxiliarMultiplicacaoNumerador)


        val soma = adicao((-1 * b.toDouble()), raizDelta)

        formula2x5.text = "x' = "
        textoVazio5.text = "x' = "
        formula2Denominador5.text = formatarResultado(soma)
        adicinarLinhaAbaixoDeTexto(formula2Denominador5)

        formula2Numerador5.text = "${formatarResultado(auxiliarMultiplicacaoNumerador)}"

        var divisao = divisao(soma, auxiliarMultiplicacaoNumerador)

        formula2x6.text = "x' = "
        textoVazio6.text = "x' = "
        formula2x6.setTextColor(Color.GREEN)
        formula2Denominador6.text = formatarResultado(divisao)
        formula2Denominador6.setTextColor(Color.GREEN)

        calcularRaizX2(b, raizDelta, auxiliarMultiplicacaoNumerador)
    }

    private fun calcularRaizX2(
        b: String,
        raizDelta: Double,
        auxiliarMultiplicacaoNumerador: Double
    ) {
        formula2x7.text = "x\" = "
        textoVazio7.text = "x\" = "
        formula2Denominador7.text =
            "-${isNegative(b.toDouble())} - ${formatarResultado(raizDelta)}"
        adicinarLinhaAbaixoDeTexto(formula2Denominador7)
        formula2Numerador7.text = formatarResultado(auxiliarMultiplicacaoNumerador)


        val subtracao = subtracao((-1 * b.toDouble()), raizDelta)

        formula2x8.text = "x\" = "
        textoVazio8.text = "x\" = "
        formula2Denominador8.text = formatarResultado(subtracao)
        adicinarLinhaAbaixoDeTexto(formula2Denominador8)

        formula2Numerador8.text = "${formatarResultado(auxiliarMultiplicacaoNumerador)}"

        val divisao = divisao(subtracao, auxiliarMultiplicacaoNumerador)

        formula2x9.text = "x\" = "
        textoVazio9.text = "x\" = "
        formula2x9.setTextColor(Color.GREEN)
        formula2Denominador9.text = formatarResultado(divisao)
        formula2Denominador9.setTextColor(Color.GREEN)
    }

    private fun isDecimal(valor: Double): Boolean {
        return (valor % 1) != 0.0 || (valor % 1) != -0.0
    }

    private fun verificarSimboloDivisao(valor: String): String {
        if (valor.contains("[\\\\/]".toRegex())) {
            return conversaoFracaoParaDecimal(valor).toString()
        } else if (valor.contains(",")) {
            return valor.replace(",", ".")
        } else {
            return valor
        }
    }

    private fun conversaoFracaoParaDecimal(a: String): Double {
        val numeros = a.split("[\\\\/]".toRegex())
        val denominador = numeros[0].toDouble()
        val numerador = numeros[1].toDouble()
        val resultado = denominador / numerador
        return formatarDouble(resultado)
    }

    private fun isNegative(valor: Double): String {
        return if (valor < 0.0) {
            if (!isDecimal(valor)) {
                "(${valor.roundToInt()})"
            } else {
                "(${formatarDouble(valor)})"
            }
        } else {
            if (!isDecimal(valor)) {
                valor.roundToInt().toString()
            } else {
                formatarDouble(valor).toString()
            }
        }
    }

    private fun convertDecimalToFraction(x: Double): String {
        if (x < 0) {
            return "-" + convertDecimalToFraction(-x)
        }
        val tolerance = 1.0E-6
        var h1 = 1.0
        var h2 = 0.0
        var k1 = 0.0
        var k2 = 1.0
        var b = x
        do {
            val a = Math.floor(b)
            var aux = h1
            h1 = a * h1 + h2
            h2 = aux
            aux = k1
            k1 = a * k1 + k2
            k2 = aux
            b = 1 / (b - a)
        } while (Math.abs(x - h1 / k1) > x * tolerance)
        val denominador = Math.round(h1)
        val numerador = Math.round(k1)
        return "$denominador/$numerador"
    }

    private fun inicializarComponentes() {
        valorA = binding.editTextA
        valorB = binding.editTextB
        valorC = binding.editTextC
        linearCalculos = binding.linearCalculos
        botaoCalcular = binding.buttonCalculate
        botaoLimpar = binding.buttonClearFields
        bhaskara1 = binding.textViewBhaskara1
        formula2Denominador1 = binding.formula2Denominador1
        formula2Numerador1 = binding.formula2Numerador1
        formula2Denominador2 = binding.formula2Denominador2
        formula2Numerador2 = binding.formula2Numerador2
        formula2Denominador3 = binding.formula2Denominador3
        formula2Numerador3 = binding.formula2Numerador3
        formula2Denominador4 = binding.formula2Denominador4
        formula2Numerador4 = binding.formula2Numerador4
        formula2Denominador5 = binding.formula2Denominador5
        formula2Numerador5 = binding.formula2Numerador5
        formula2Denominador6 = binding.formula2Denominador6
        formula2Numerador6 = binding.formula2Numerador6
        formula2Denominador7 = binding.formula2Denominador7
        formula2Numerador7 = binding.formula2Numerador7
        formula2Denominador8 = binding.formula2Denominador8
        formula2Numerador8 = binding.formula2Numerador8
        formula2Denominador9 = binding.formula2Denominador9
        formula2Numerador9 = binding.formula2Numerador9
        formula2x1 = binding.formula2x1
        formula2x2 = binding.formula2x2
        formula2x3 = binding.formula2x3
        formula2x4 = binding.formula2x4
        formula2x5 = binding.formula2x5
        formula2x6 = binding.formula2x6
        formula2x7 = binding.formula2x7
        formula2x8 = binding.formula2x8
        formula2x9 = binding.formula2x9
        textoVazio1 = binding.textVazio1
        textoVazio2 = binding.textVazio2
        textoVazio3 = binding.textVazio3
        textoVazio4 = binding.textVazio4
        textoVazio5 = binding.textVazio5
        textoVazio6 = binding.textVazio6
        textoVazio7 = binding.textVazio7
        textoVazio8 = binding.textVazio8
        textoVazio9 = binding.textVazio9
        decimalFormat = DecimalFormat("#.##")
        decimalFormat.roundingMode = RoundingMode.UP
    }

    private fun exponenciacao(b: String): Double {
        val bConvertido = b.toDouble()
        val resultado = bConvertido.pow(2)
        return formatarDouble(resultado)
    }

    private fun subtracao(a: Double, b: Double): Double {
        val resultado = a - b
        return formatarDouble(resultado)
    }

    private fun multiplicacao(i: Int, a: Double, c: Double): Double {
        val resultado = i * a * c
        return formatarDouble(resultado)
    }

    private fun adicao(a: Double, b: Double): Double {
        val resultado = a + b
        return formatarDouble(resultado)
    }

    private fun divisao(a: Double, b: Double): Double {
        val resultado = a / b
        return formatarDouble(resultado)
    }

    private fun raizQuadrada(valor: Double): Double {
        val resultado = sqrt(valor)
        return formatarDouble(resultado)
    }

    private fun formatarDouble(valor: Double): Double {
        return decimalFormat.format(valor).toDouble()
    }
}