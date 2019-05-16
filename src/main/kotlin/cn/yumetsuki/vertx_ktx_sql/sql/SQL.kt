package cn.yumetsuki.vertx_ktx_sql.sql

/**
 * This class is a wrapper of a StringBuilder
 * In order to avoid calling function StringBuilder.clear()
 * @author yumetsuki
 * */
class SQL private constructor(private val builder: StringBuilder) {

    fun sql() = builder.toString()

    /**
     * This class can generate a SQL by streaming calls.
     * You can also use infix function in SQLBuilderExt
     * @author yumetsuki
     * */
    @Suppress("FunctionName")
    class SQLBuilder {

        private val builder = StringBuilder()

        /**
         * Concat "select" and a string
         * @param s a string after select
         * */
        fun select(s: String): StringBuilder = builder.append("select $s ")

        /**
         * Concat "from" and a string
         * @param s a string after from
         * */
        fun from(s: String): StringBuilder = builder.append("from $s ")

        /**
         * Concat "where" and a string
         * @param s a string after where
         * */
        fun where(s: String): StringBuilder = builder.append("where $s ")

        /**
         * Concat "insert_into" and a string
         * @param s a string after insert_into
         * */
        fun insert_into(s: String): StringBuilder = builder.append("insert into $s ")

        /**
         * Concat "value" and a string
         * @param list a string list after value and in ()
         * */
        fun value(list: List<String>): StringBuilder = builder.append("value(${list.joinToString(",")}) ")

        /**
         * Concat "values" and a string
         * @param list a string list after values and in ()
         * */
        fun values(list: List<String>): StringBuilder = builder.append("values(${list.joinToString(",")}) ")

        /**
         * Put a list into ()
         * @param list a string list
         * */
        fun parentheses(list: List<String>): StringBuilder = builder.append("(${list.joinToString(",")}) ")

        /**
         * Concat "value" and a string
         * @param sequence a string sequence after value and in ()
         * */
        fun value(sequence: Sequence<String>): StringBuilder = builder.append("value(${sequence.joinToString(",")}) ")

        /**
         * Concat "values" and a string
         * @param sequence a string sequence a string list after values and in ()
         * */
        fun values(sequence: Sequence<String>): StringBuilder = builder.append("values(${sequence.joinToString(",")}) ")

        /**
         * Put a list into ()
         * @param sequence a string sequence
         * */
        fun parentheses(sequence: Sequence<String>): StringBuilder = builder.append("(${sequence.joinToString(",")}) ")

        /**
         * Concat "update" and a string
         * @param s a string after update
         * */
        fun update(s: String): StringBuilder = builder.append("update $s ")

        /**
         * Concat "set" and a string
         * @param s a string after set
         * */
        fun set(s: String): StringBuilder = builder.append("set $s ")

        /**
         * Concat "equals" and a string
         * @param s a string after equals
         * */
        fun eql(s: String): StringBuilder = builder.append("=$s ")

        /**
         * Concat "inner join" and a string
         * @param s a string after inner join
         * */
        fun inner_join(s: String): StringBuilder = builder.append("inner join $s ")

        /**
         * Concat "left join" and a string
         * @param s a string after left join
         * */
        fun left_join(s: String): StringBuilder = builder.append("left join $s ")

        /**
         * Concat "right join" and a string
         * @param s a string after right join
         * */
        fun right_join(s: String): StringBuilder = builder.append("right join $s ")

        /**
         * Build SQL with StringBuilder
         * */
        fun build(): SQL = SQL(builder)

        /**
         * Concat a string
         * @param s a string
         * */
        fun concat(s: String): StringBuilder = builder.append("$s ")

        /**
         * Concat this builder and another
         * @param builder a string after select
         * */
        operator fun plus(builder: SQLBuilder): SQLBuilder {
            this.builder.append(builder.builder.toString())
            return this
        }

    }

    companion object {
        const val all: String = "*"
        const val param: String = "?"
    }

}