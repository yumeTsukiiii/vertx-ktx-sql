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
        fun select(s: String): SQLBuilder = builder.append("select $s ").let { this }

        /**
         * Concat "from" and a string
         * @param s a string after from
         * */
        fun from(s: String): SQLBuilder = builder.append("from $s ").let { this }

        /**
         * Concat "where" and a string
         * @param s a string after where
         * */
        fun where(s: String): SQLBuilder = builder.append("where $s ").let { this }

        /**
         * Concat "insert_into" and a string
         * @param s a string after insert_into
         * */
        fun insert_into(s: String): SQLBuilder = builder.append("insert into $s ").let { this }

        /**
         * Concat "value" and a string
         * @param list a string list after value and in ()
         * */
        fun value(list: List<String>): SQLBuilder = builder.append("value(${list.joinToString(",")}) ").let { this }

        /**
         * Concat "values" and a string
         * @param list a string list after values and in ()
         * */
        fun values(list: List<String>): SQLBuilder = builder.append("values(${list.joinToString(",")}) ").let { this }

        /**
         * Put a list into ()
         * @param list a string list
         * */
        fun parentheses(list: List<String>): SQLBuilder = builder.append("(${list.joinToString(",")}) ").let { this }

        /**
         * Concat "value" and a string
         * @param sequence a string sequence after value and in ()
         * */
        fun value(sequence: Sequence<String>): SQLBuilder = builder.append("value(${sequence.joinToString(",")}) ").let { this }

        /**
         * Concat "values" and a string
         * @param sequence a string sequence a string list after values and in ()
         * */
        fun values(sequence: Sequence<String>): SQLBuilder = builder.append("values(${sequence.joinToString(",")}) ").let { this }

        /**
         * Put a list into ()
         * @param sequence a string sequence
         * */
        fun parentheses(sequence: Sequence<String>): SQLBuilder = builder.append("(${sequence.joinToString(",")}) ").let { this }

        /**
         * Concat "update" and a string
         * @param s a string after update
         * */
        fun update(s: String): SQLBuilder = builder.append("update $s ").let { this }

        /**
         * Concat "set" and a string
         * @param s a string after set
         * */
        fun set(s: String): SQLBuilder = builder.append("set $s ").let { this }

        /**
         * Concat "equals" and a string
         * @param s a string after equals
         * */
        fun eql(s: String): SQLBuilder = builder.append("=$s ").let { this }

        /**
         * Concat "inner join" and a string
         * @param s a string after inner join
         * */
        fun inner_join(s: String): SQLBuilder = builder.append("inner join $s ").let { this }

        /**
         * Concat "left join" and a string
         * @param s a string after left join
         * */
        fun left_join(s: String): SQLBuilder = builder.append("left join $s ").let { this }

        /**
         * Concat "right join" and a string
         * @param s a string after right join
         * */
        fun right_join(s: String): SQLBuilder = builder.append("right join $s ").let { this }

        /**
         * Build SQL with StringBuilder
         * */
        fun build(): SQL = SQL(builder)

        /**
         * Concat a string
         * @param s a string
         * */
        fun concat(s: String): SQLBuilder = builder.append("$s ").let { this }

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