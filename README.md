Kotlin Klaxon Demo
==================

Test if Klaxon will convert String value to Double automatically.

The answer is NO, with such exception:

```
Exception in thread "main" com.beust.klaxon.KlaxonException: Couldn't find a suitable constructor for class Data to initialize with {number=123.456}: java.lang.IllegalArgumentException argument type mismatch
	at com.beust.klaxon.JsonObjectConverter.fromJson(JsonObjectConverter.kt:48)
```

So we need to write a converter(which is not quite convenient though)