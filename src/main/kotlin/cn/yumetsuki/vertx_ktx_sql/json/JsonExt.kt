package cn.yumetsuki.vertx_ktx_sql.json

import com.fasterxml.jackson.databind.ObjectMapper
import io.vertx.core.json.JsonObject

val mapper = ObjectMapper()

inline fun <reified T> String.fromJson(): T = mapper.readValue(this, T::class.java)

inline fun <reified T> JsonObject.fromJson(): T = mapper.readValue(this.encode(), T::class.java)

inline fun <reified T> T.toJson(): String = mapper.writeValueAsString(this)