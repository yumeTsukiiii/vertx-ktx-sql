# vertx-ktx-sql
### This is a DSL of Vertx-Database-Access with Kotlin.
### It's very simple and lightweight, but it might be useful to you.

#### For example:
```
suspend fun main() {

    val vertx = Vertx.vertx()

    //vertx api,create a AsyncClient
    val client = MySQLClient.createShared(vertx, json {
        obj(
            "username" to "root",
            "password" to "root",
            "database" to "study"
        )
    })

    //extension create a connection
    client.connection {
    
        queryJsonWithParams(json {
            array(1)
        }) {
            //build a sql
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
```
------
> *If you just want to build a sql string with dsl, you can just download the SQL.kt and SQLBuilderExt.kt*

### Support following function to append a sql keyword, but not all.
#### You can see more at SQL.kt
* select
* from 
* where
* eql
* insert_into
* value
* values
* inner_join
* left_join
* right_join
* update

