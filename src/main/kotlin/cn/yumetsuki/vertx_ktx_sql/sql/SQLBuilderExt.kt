package cn.yumetsuki.vertx_ktx_sql.sql

suspend fun sql(handler: suspend SQL.SQLBuilder.() -> SQL.SQLBuilder): SQL {
    return handler(SQL.SQLBuilder()).build()
}

infix fun SQL.SQLBuilder.select(s: String): SQL.SQLBuilder = select(s)

infix fun SQL.SQLBuilder.from(s: String): SQL.SQLBuilder = from(s)

infix fun SQL.SQLBuilder.where(s: String): SQL.SQLBuilder = where(s)

infix fun SQL.SQLBuilder.insert_into(s: String): SQL.SQLBuilder = insert_into(s)

infix fun SQL.SQLBuilder.value(list: List<String>): SQL.SQLBuilder = value(list)

infix fun SQL.SQLBuilder.values(list: List<String>): SQL.SQLBuilder = values(list)

infix fun SQL.SQLBuilder.value(sequence: Sequence<String>): SQL.SQLBuilder = value(sequence)

infix fun SQL.SQLBuilder.values(sequence: Sequence<String>): SQL.SQLBuilder = values(sequence)

infix fun SQL.SQLBuilder.parentheses(list: List<String>): SQL.SQLBuilder = parentheses(list)

infix fun SQL.SQLBuilder.parentheses(sequence: Sequence<String>): SQL.SQLBuilder = parentheses(sequence)

infix fun SQL.SQLBuilder.update(s: String): SQL.SQLBuilder = update(s)

infix fun SQL.SQLBuilder.set(s: String): SQL.SQLBuilder = set(s)

infix fun SQL.SQLBuilder.eql(s: String): SQL.SQLBuilder = eql(s)

infix fun SQL.SQLBuilder.inner_join(s: String): SQL.SQLBuilder = inner_join(s)

infix fun SQL.SQLBuilder.left_join(s: String): SQL.SQLBuilder = left_join(s)

infix fun SQL.SQLBuilder.right_join(s: String): SQL.SQLBuilder = right_join(s)

infix fun SQL.SQLBuilder.concat(s: String): SQL.SQLBuilder = concat(s)





