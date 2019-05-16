import cn.yumetsuki.vertx_ktx_sql.sql.*
import cn.yumetsuki.vertx_ktx_sql.sql.SQL.Companion.all
import cn.yumetsuki.vertx_ktx_sql.sql.SQL.Companion.param
import cn.yumetsuki.vertx_ktx_sql.vertx_ext.*
import io.vertx.core.Vertx
import io.vertx.ext.asyncsql.MySQLClient
import io.vertx.kotlin.core.json.array
import io.vertx.kotlin.core.json.json
import io.vertx.kotlin.core.json.obj

suspend fun main() {

    val vertx = Vertx.vertx()

    val client = MySQLClient.createShared(vertx, json {
        obj(
            "username" to "root",
            "password" to "root",
            "database" to "study"
        )
    })

    client.connection {

        queryJsonWithParams(json {
            array(1)
        }) {
            sql {
                this select all from "user" where "id" eql param
            }
        }.forEach {
            println(it)
        }

    }.join()

    client.close()

    vertx.close()
}