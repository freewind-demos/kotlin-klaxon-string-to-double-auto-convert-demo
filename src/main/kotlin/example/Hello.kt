package example

import com.beust.klaxon.Converter
import com.beust.klaxon.JsonObject
import com.beust.klaxon.JsonValue
import com.beust.klaxon.Klaxon

data class Data(val foo: Double, val bar: Double)

fun main(args: Array<String>) {
    val json = """{"foo":"123.456", "bar":"234.567"}"""
    failed(json)
    succeedWithConverter(json)
}

private fun failed(json: String) {
    try {
        val klaxon = Klaxon()
        val data = klaxon.parse<Data>(json)
        println(data)
    } catch (e: Exception) {
        println(e.toString())
    }
}

private fun succeedWithConverter(json: String) {
    val converter = object : Converter {
        override fun canConvert(cls: Class<*>): Boolean {
            return cls == Data::class.java
        }

        override fun fromJson(jv: JsonValue): Any {
            val obj = jv.inside as JsonObject
            val foo = obj.string("foo")!!
            val bar = obj.string("bar")!!
            return Data(foo.toDouble(), bar.toDouble())
        }

        override fun toJson(value: Any): String {
            val obj = value as Data
            return """{"foo":"${obj.foo}", "bar":"${obj.bar}"}"""
        }
    }

    val data = Klaxon().converter(converter).parse<Data>(json)!!
    println(data)

    val str = Klaxon().converter(converter).toJsonString(data)
    println(str)
}



