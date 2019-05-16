package cn.yumetsuki.vertx_ktx_sql.vertx_ext

import cn.yumetsuki.vertx_ktx_sql.json.fromJson
import cn.yumetsuki.vertx_ktx_sql.sql.SQL
import io.vertx.core.json.JsonArray
import io.vertx.core.json.JsonObject
import io.vertx.ext.asyncsql.AsyncSQLClient
import io.vertx.ext.sql.SQLConnection
import io.vertx.ext.sql.UpdateResult
import io.vertx.kotlin.ext.sql.*
import kotlinx.coroutines.*

/**
 * Connection DSL
 * You should make sure the handler finished before connection.close()
 * @param handler A extension function of SQLConnection
 * @author yumetsuki
 * */
fun AsyncSQLClient.connection(handler: suspend SQLConnection.() -> Unit): Job {
    return GlobalScope.launch {
        val connection = getConnectionAwait()
        handler(connection)
        connection.close()
    }
}

/**
 * SQLConnection.query DSL
 * @param handler A handler that return a SQL instance
 * @return A list of result<T>
 * @author yumetsuki
 * */
suspend inline fun <reified T> SQLConnection.query(
    handler: () -> SQL
): List<T> {
    return queryAwait(handler().sql()).rows.map { it.fromJson<T>() }
}

/**
 * SQLConnection.queryWithParams DSL
 * @param handler A handler that return a SQL instance
 * @return A list of result<T>
 * @author yumetsuki
 * */
suspend inline fun <reified T> SQLConnection.queryWithParams(
    array: JsonArray,
    handler: () -> SQL
): List<T> {
    return queryWithParamsAwait(handler().sql(), array).rows.map { it.fromJson<T>() }
}

/**
 * SQLConnection.query DSL
 * @param handler A handler that return a SQL instance
 * @return A list of result<JsonObject>
 * @author yumetsuki
 * */
suspend fun SQLConnection.queryJson(
    handler: suspend () -> SQL
): List<JsonObject>
{
    return queryAwait(handler().sql()).rows
}

/**
 * SQLConnection.queryWithParams DSL
 * @param handler A handler that return a SQL instance
 * @param array The params used in sql
 * @return A list of result<Json>
 * @author yumetsuki
 * */
suspend fun SQLConnection.queryJsonWithParams(
    array: JsonArray,
    handler: suspend () -> SQL
): List<JsonObject> {
    return queryWithParamsAwait(handler().sql(), array).rows
}

/**
 * SQLConnection.update DSL
 * @param handler A handler that return a SQL instance
 * @return UpdateResultSet
 * @author yumetsuki
 * */
suspend fun SQLConnection.update(
    handler: suspend () -> SQL
): UpdateResult
{
    return updateAwait(handler().sql())
}

/**
 * SQLConnection.updateWithParams DSL
 * @param handler A handler that return a SQL instance
 * @return UpdateResultSet
 * @author yumetsuki
 * */
suspend fun SQLConnection.updateWithParams(
    array: JsonArray,
    handler: suspend () -> SQL
): UpdateResult
{
    return updateWithParamsAwait(handler().sql(), array)
}

/**
 * SQLConnection.execute DSL
 * @param handler A handler that return a SQL instance
 * @author yumetsuki
 * */
suspend fun SQLConnection.execute(
    handler: suspend () -> SQL
) {
    return executeAwait(handler().sql())
}

/**
 * SQLConnection.call DSL
 * @param handler A handler that return a SQL instance
 * @return A list of result<T>
 * @author yumetsuki
 * */
suspend inline fun <reified T> SQLConnection.call(
    handler: () -> SQL
): List<T> {
    return callAwait(handler().sql()).rows.map { it.fromJson<T>() }
}

/**
 * SQLConnection.callWithParams DSL
 * @param params The params used in sql
 * @param outputs The outputs of the call function result
 * @param handler A handler that return a SQL instance
 * @return A list of result<T>
 * @author yumetsuki
 * */
suspend inline fun <reified T> SQLConnection.callWithParams(
    params: JsonArray,
    outputs: JsonArray,
    handler: () -> SQL
): List<T>{
    return callWithParamsAwait(handler().sql(), params, outputs).rows.map { it.fromJson<T>() }
}

/**
 * SQLConnection.call DSL
 * @param handler A handler that return a SQL instance
 * @return A list of result<JsonObject>
 * @author yumetsuki
 * */
suspend fun SQLConnection.callJson(
    handler: suspend () -> SQL
): List<JsonObject> {
    return callAwait(handler().sql()).rows
}

/**
 * SQLConnection.callWithParams DSL
 * @param params The params used in sql
 * @param outputs The outputs of the call function result
 * @param handler A handler that return a SQL instance
 * @return A list of result<JsonObject>
 * @author yumetsuki
 * */
suspend fun SQLConnection.callJsonWithParams(
    params: JsonArray,
    outputs: JsonArray,
    handler: suspend () -> SQL
): List<JsonObject>{
    return callWithParamsAwait(handler().sql(), params, outputs).rows
}